package artifacts.client.render.model.trinket;

public class AquaDashersModel extends ShoesModel {

    private static final float DELTA = 1.25F;

    public AquaDashersModel() {
        super(DELTA);
        leftLeg.texOffs(0, 16);
        leftLeg.addBox(2 + DELTA, 0, -2 + 3 + DELTA * 3 / 2, 0, 12, 4, 0, DELTA, DELTA);
        rightLeg.texOffs(16, 16);
        rightLeg.addBox(-2 - DELTA, 0, -2 + 3 + DELTA * 3 / 2, 0, 12, 4, 0, DELTA, DELTA);
    }
}
