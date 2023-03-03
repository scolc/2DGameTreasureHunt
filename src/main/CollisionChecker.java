package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker (GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile (Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityTopRow][entityLeftCol];
                tileNum2 = gp.getTileM().getMapTileNum()[entityTopRow][entityRightCol];

                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityBottomRow][entityLeftCol];
                tileNum2 = gp.getTileM().getMapTileNum()[entityBottomRow][entityRightCol];

                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityTopRow][entityLeftCol];
                tileNum2 = gp.getTileM().getMapTileNum()[entityBottomRow][entityLeftCol];

                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityTopRow][entityRightCol];
                tileNum2 = gp.getTileM().getMapTileNum()[entityBottomRow][entityRightCol];

                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.getObject().length; i++) {
            if (gp.getObject()[i] != null) {
                // Get entity solid area position
                entity.getSolidArea().x += entity.getWorldX();
                entity.getSolidArea().y += entity.getWorldY();

                // Get object solid area position
                gp.getObject()[i].getSolidArea().x += gp.getObject()[i].getWorldX();
                gp.getObject()[i].getSolidArea().y += gp.getObject()[i].getWorldY();

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObject()[i].getSolidArea())) {
                            if (gp.getObject()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObject()[i].getSolidArea())) {
                            if (gp.getObject()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObject()[i].getSolidArea())) {
                            if (gp.getObject()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObject()[i].getSolidArea())) {
                            if (gp.getObject()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.getObject()[i].getSolidArea().x = gp.getObject()[i].getSolidAreaDefaultX();
                gp.getObject()[i].getSolidArea().y = gp.getObject()[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }
}
