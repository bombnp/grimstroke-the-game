package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import entity.particle.CannonBullet;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.Invoker;
import logic.Vector2;

public class CannonTower extends Tower {
    private CellImage[] particles;

    public CannonTower(BoardCell cell, int level) {
        super(cell, Database.Cannon[level-1], level);
        this.turretImage.setTranslateY(-5);

        setupFiringParticle(level);
    }

    private void setupFiringParticle(int level) {
        switch(level) {
            case 1:
                particles = new CellImage[]{new CellImage(Sprite.CANNON_FIRE)};
                break;
            case 2:
                particles = new CellImage[]{new CellImage(Sprite.CANNON_FIRE), new CellImage(Sprite.CANNON_FIRE)};
                particles[0].setTranslateX(-4.5);
                particles[1].setTranslateX(4.5);
                break;
        }
        for (CellImage particle : particles) {
            particle.setTranslateY(-35);
            particle.setScaleX(0.7);
            particle.setScaleY(0.7);
            particle.setVisible(false);
            this.turret.getChildren().add(particle);
        }
    }

    @Override
    public void attack(Minion target) {
        for (CellImage particle : particles) {
            particle.setVisible(true);
        }
        new Invoker(() -> {
            for (CellImage particle : particles) {
                particle.setVisible(false);
            }
        }).startIn(100);

        new CannonBullet(this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(35, this.turret.getRotate())), this.getRotate(), target, this.getDamage());
    }
}
