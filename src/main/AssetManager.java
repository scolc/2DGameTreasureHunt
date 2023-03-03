package main;

import object.*;

public class AssetManager {

    private GamePanel gp;

    public AssetManager(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        // Keys
        gp.getObject()[0] = new ObjectKey(gp);
        gp.getObject()[0].setWorldX(13 * gp.getTileSize());
        gp.getObject()[0].setWorldY(44 * gp.getTileSize());

        gp.getObject()[1] = new ObjectKey(gp);
        gp.getObject()[1].setWorldX(14 * gp.getTileSize());
        gp.getObject()[1].setWorldY(30 * gp.getTileSize());

        gp.getObject()[2] = new ObjectKey(gp);
        gp.getObject()[2].setWorldX(25 * gp.getTileSize());
        gp.getObject()[2].setWorldY(35 * gp.getTileSize());

        gp.getObject()[3] = new ObjectKey(gp);
        gp.getObject()[3].setWorldX(12 * gp.getTileSize());
        gp.getObject()[3].setWorldY(8 * gp.getTileSize());

        gp.getObject()[4] = new ObjectKey(gp);
        gp.getObject()[4].setWorldX(42 * gp.getTileSize());
        gp.getObject()[4].setWorldY(6 * gp.getTileSize());

        gp.getObject()[5] = new ObjectKey(gp);
        gp.getObject()[5].setWorldX(44 * gp.getTileSize());
        gp.getObject()[5].setWorldY(12 * gp.getTileSize());

        gp.getObject()[6] = new ObjectKey(gp);
        gp.getObject()[6].setWorldX(48 * gp.getTileSize());
        gp.getObject()[6].setWorldY(19 * gp.getTileSize());

        gp.getObject()[7] = new ObjectKey(gp);
        gp.getObject()[7].setWorldX(27 * gp.getTileSize());
        gp.getObject()[7].setWorldY(30 * gp.getTileSize());

        gp.getObject()[8] = new ObjectKey(gp);
        gp.getObject()[8].setWorldX(47 * gp.getTileSize());
        gp.getObject()[8].setWorldY(35 * gp.getTileSize());

        gp.getObject()[9] = new ObjectKey(gp);
        gp.getObject()[9].setWorldX(50 * gp.getTileSize());
        gp.getObject()[9].setWorldY(48 * gp.getTileSize());


        // Doors
        gp.getObject()[10] = new ObjectDoor(gp);
        gp.getObject()[10].setWorldX(10 * gp.getTileSize());
        gp.getObject()[10].setWorldY(40 * gp.getTileSize());

        gp.getObject()[11] = new ObjectDoor(gp);
        gp.getObject()[11].setWorldX(10 * gp.getTileSize());
        gp.getObject()[11].setWorldY(21 * gp.getTileSize());

        gp.getObject()[12] = new ObjectDoor(gp);
        gp.getObject()[12].setWorldX(13 * gp.getTileSize());
        gp.getObject()[12].setWorldY(18 * gp.getTileSize());

        gp.getObject()[13] = new ObjectDoor(gp);
        gp.getObject()[13].setWorldX(24 * gp.getTileSize());
        gp.getObject()[13].setWorldY(7 * gp.getTileSize());

        gp.getObject()[14] = new ObjectDoor(gp);
        gp.getObject()[14].setWorldX(36 * gp.getTileSize());
        gp.getObject()[14].setWorldY(19 * gp.getTileSize());

        gp.getObject()[15] = new ObjectDoor(gp);
        gp.getObject()[15].setWorldX(36 * gp.getTileSize());
        gp.getObject()[15].setWorldY(34 * gp.getTileSize());

        gp.getObject()[16] = new ObjectDoor(gp);
        gp.getObject()[16].setWorldX(22 * gp.getTileSize());
        gp.getObject()[16].setWorldY(44 * gp.getTileSize());

        gp.getObject()[17] = new ObjectDoor(gp);
        gp.getObject()[17].setWorldX(12 * gp.getTileSize());
        gp.getObject()[17].setWorldY(48 * gp.getTileSize());

        gp.getObject()[18] = new ObjectDoor(gp);
        gp.getObject()[18].setWorldX(49 * gp.getTileSize());
        gp.getObject()[18].setWorldY(26 * gp.getTileSize());

        gp.getObject()[19] = new ObjectDoor(gp);
        gp.getObject()[19].setWorldX(49 * gp.getTileSize());
        gp.getObject()[19].setWorldY(7 * gp.getTileSize());


        // Chest
        gp.getObject()[20] = new ObjectChest(gp);
        gp.getObject()[20].setWorldX(50 * gp.getTileSize());
        gp.getObject()[20].setWorldY(7 * gp.getTileSize());


        // Boot
        gp.getObject()[21] = new ObjectBoot(gp);
        gp.getObject()[21].setWorldX(8 * gp.getTileSize());
        gp.getObject()[21].setWorldY(39 * gp.getTileSize());
    }
}
