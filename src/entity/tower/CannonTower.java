package entity.tower;

import database.Database;
import entity.minion.Minion;
import entity.particle.CannonBullet;
import entity.tower.base.Tower;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.Invoker;
import logic.Vector2;

/**
 * The CannonTower class represents the Cannon Towers. They shoot {@link CannonBullet Cannon Bullets} to their targets.
 */
public class CannonTower extends Tower {
    /**
     * The firing particles of the tower.
     */
    private CellImage[] particles;

    /**
     * The constructor of the CannonTower class. It corrects the position of the
     * turret image and sets up the firing particle.
     * @param cell The cell the tower is bound to.
     * @param level The level of the tower.
     */
    public CannonTower(BoardCell cell, int level) {
        super(cell, Database.Cannon[level-1], level);
        this.turretImage.setTranslateY(-5);

        setupFiringParticle(level);
    }

    /**
     * Sets up the firing particles of the tower, based on the tower level.
     * @param level The level of the tower.
     */
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

    /**
     * The implementation of the {@link Tower#attack(Minion) attack} method of the {@link Tower} class. It creates
     * a {@link CannonBullet}, sets the visibility of the particles to true and sets it to false later using
     * {@link Invoker}.
     * @param target The target of the tower.
     */
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
