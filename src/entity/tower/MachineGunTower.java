package entity.tower;

import database.Database;
import entity.minion.Minion;
import entity.tower.base.Tower;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.DamageType;
import logic.Invoker;

/**
 * The MachineGunTower class represents the Machine Gun Towers. Machine Gun Towers are the only towers that
 * can target flying {@link Minion Minions}.
 */
public class MachineGunTower extends Tower {
    /**
     * The firing particles of the tower.
     */
    private CellImage[] particles;

    /**
     * The constructor of the MachineGunTower class. It corrects the position of the
     * turret image and sets up the firing particle.
     * @param cell The cell the tower is bound to.
     * @param level The level of the tower.
     */
    public MachineGunTower(BoardCell cell, int level) {
        super(cell, Database.MG[level-1], level);
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
                particles = new CellImage[]{new CellImage(Sprite.MG_FIRE), new CellImage(Sprite.MG_FIRE)};
                particles[0].setTranslateX(-5);
                particles[1].setTranslateX(5);
                break;
            case 2:
                particles = new CellImage[]{new CellImage(Sprite.MG_FIRE), new CellImage(Sprite.MG_FIRE), new CellImage(Sprite.MG_FIRE)};
                particles[0].setTranslateX(-7);
                particles[2].setTranslateX(7);
                break;
        }
        for (CellImage particle : particles) {
            particle.setTranslateY(-33);
            particle.setScaleX(0.7);
            particle.setScaleY(0.7);
            particle.setVisible(false);
            this.turret.getChildren().add(particle);
        }
    }

    /**
     * The implementation of the {@link Tower#attack(Minion) attack} method of the {@link Tower} class. It damages
     * the target, set the visibility of the particles to true and set it to false later using {@link Invoker}.
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

        target.takeDamage(getDamage(), DamageType.MG);
    }
}
