package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.DamageType;

public class MachineGunTower extends Tower {
    private CellImage[] particles;

    public MachineGunTower(BoardCell cell, int level) {
        super(cell, Database.MG[level-1]);
        this.turretImage.setTranslateY(-5);

        setupFiringParticle(level);
    }

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
            particle.disable();
            this.turret.getChildren().add(particle);
        }
    }

    @Override
    public void attack(Minion target) {
        Thread enableParticle = new Thread(() -> {
            for (CellImage particle : particles) {
                particle.enable();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (CellImage particle : particles) {
                particle.disable();
            }
        });
        enableParticle.start();

        target.takeDamage(randomizeDamage(), DamageType.MG);
    }
}
