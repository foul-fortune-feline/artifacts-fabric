package artifacts.common.item.curio.belt;

import artifacts.Artifacts;
import artifacts.common.capability.swimhandler.SwimHandlerCapability;
import artifacts.common.config.ModConfig;
import artifacts.common.init.ModSoundEvents;
import artifacts.common.item.curio.CurioItem;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class HeliumFlamingoItem extends CurioItem {

    public HeliumFlamingoItem() {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }

    @Nonnull
    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(ModSoundEvents.POP.get(), 1, 0.7F);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
        if (ModConfig.server != null && ModConfig.server.isCosmetic(this)) {
            super.addInformation(stack, world, tooltip, flags);
        } else if (ModConfig.client.showTooltips.get()) {
            tooltip.add(new TranslationTextComponent(getTranslationKey() + ".tooltip.0").mergeStyle(TextFormatting.GRAY));
            tooltip.add(new TranslationTextComponent(getTranslationKey() + ".tooltip.1", Minecraft.getInstance().gameSettings.keyBindSprint.func_238171_j_()).mergeStyle(TextFormatting.GRAY));
        }
    }

    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        event.player.getCapability(SwimHandlerCapability.INSTANCE).ifPresent(
                handler -> {
                    int maxFlightTime = ModConfig.server.heliumFlamingo.maxFlightTime.get();
                    int rechargeTime = ModConfig.server.heliumFlamingo.rechargeTime.get();

                    if (handler.isSwimming()) {

                        if (!isEquippedBy(event.player)
                                || handler.getSwimTime() > maxFlightTime
                                || event.player.isInWater() && !event.player.isSwimming() && !handler.isSinking()
                                || (!event.player.isInWater() || handler.isSinking()) && event.player.isOnGround()) {
                            handler.setSwimming(false);
                            if (!event.player.isOnGround() && !event.player.isInWater()) {
                                event.player.playSound(ModSoundEvents.POP.get(), 0.5F, 0.75F);
                            }
                        }

                        if (isEquippedBy(event.player) && !event.player.areEyesInFluid(FluidTags.WATER)) {
                            if (event.player.ticksExisted % 20 == 0) {
                                damageEquippedStacks(event.player);
                            }
                            if (!event.player.abilities.disableDamage && maxFlightTime > 0) {
                                handler.setSwimTime(handler.getSwimTime() + 1);
                            }
                        }
                    } else {
                        if (handler.getSwimTime() < 0) {

                            if (handler.getSwimTime() < -rechargeTime) {
                                handler.setSwimTime(-rechargeTime);
                            } else {
                                handler.setSwimTime(handler.getSwimTime() + 1);
                            }
                        }
                    }
                }
        );
    }

    private class ClientEventHandler {

        private final ResourceLocation location = new ResourceLocation(Artifacts.MODID, "textures/gui/icons.png");

        private boolean wasSprintKeyDown;
        private boolean wasSprintingOnGround;
        private boolean hasTouchedGround;

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public void onInputUpdate(InputUpdateEvent event) {
            if (ModConfig.server.isCosmetic(HeliumFlamingoItem.this)) {
                return;
            }

            PlayerEntity player = event.getPlayer();
            boolean isSprintKeyDown = Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown();

            player.getCapability(SwimHandlerCapability.INSTANCE).ifPresent(
                    handler -> {
                        if (!handler.isSwimming()) {
                            if (player.isOnGround()) {
                                hasTouchedGround = true;
                            } else if (!handler.isSwimming()
                                    && handler.getSwimTime() >= 0
                                    && isEquippedBy(player)
                                    && (player.isSwimming()
                                    || isSprintKeyDown
                                    && !wasSprintKeyDown
                                    && !wasSprintingOnGround
                                    && hasTouchedGround
                                    && !player.isOnGround()
                                    && (!player.isInWater() || handler.isSinking())
                                    && !player.isElytraFlying()
                                    && !player.abilities.isFlying
                                    && !player.isPassenger())) {
                                handler.setSwimming(true);
                                handler.syncSwimming();
                                hasTouchedGround = false;
                            }
                        } else if (player.abilities.isFlying) {
                            handler.setSwimming(false);
                            handler.syncSwimming();
                            hasTouchedGround = true;
                        }
                    }
            );

            wasSprintKeyDown = isSprintKeyDown;
            if (!isSprintKeyDown) {
                wasSprintingOnGround = false;
            } else if (player.isOnGround()) {
                wasSprintingOnGround = true;
            }
        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent(priority = EventPriority.LOW)
        public void render(RenderGameOverlayEvent.Post event) {
            Minecraft minecraft = Minecraft.getInstance();

            if (ModConfig.server.isCosmetic(HeliumFlamingoItem.this) || !(minecraft.getRenderViewEntity() instanceof LivingEntity)) {
                return;
            }

            LivingEntity player = (LivingEntity) minecraft.getRenderViewEntity();

            if (event.getType() != RenderGameOverlayEvent.ElementType.AIR || event.isCanceled() || !isEquippedBy(player)) {
                return;
            }

            player.getCapability(SwimHandlerCapability.INSTANCE).ifPresent(
                    handler -> {
                        int left = minecraft.getMainWindow().getScaledWidth() / 2 + 91;
                        int top = minecraft.getMainWindow().getScaledHeight() - ForgeIngameGui.right_height;

                        int swimTime = Math.abs(handler.getSwimTime());
                        int maxProgressTime;

                        if (swimTime == 0) {
                            return;
                        } else if (handler.getSwimTime() > 0) {
                            maxProgressTime = ModConfig.server.heliumFlamingo.maxFlightTime.get();
                        } else {
                            maxProgressTime = ModConfig.server.heliumFlamingo.rechargeTime.get();
                        }

                        float progress = 1 - swimTime / (float) maxProgressTime;

                        Minecraft.getInstance().getTextureManager().bindTexture(location);
                        RenderSystem.enableBlend();

                        int full = MathHelper.ceil((progress - 2D / maxProgressTime) * 10);
                        int partial = MathHelper.ceil(progress * 10) - full;

                        for (int i = 0; i < full + partial; ++i) {
                            ForgeIngameGui.blit(event.getMatrixStack(), left - i * 8 - 9, top, -90, (i < full ? 0 : 9), 0, 9, 9, 16, 32);
                        }
                        ForgeIngameGui.right_height += 10;

                        RenderSystem.disableBlend();
                    }
            );
        }
    }
}
