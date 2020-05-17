package gui;

import java.util.Random;

/**
 * The Sprite class contains the sprite indices for various objects.
 */
public class Sprite {
    /**
     * A {@link Random randomizer}.
     */
    public static Random random = new Random();

    /**
     * Indicates whether the cell is buildable. ({@value})
     */
    public static final int BUILD_SPOT = 16;

    /**
     * The base of the {@link entity.tower.base.Tower Towers}. Has 2 variants. (181, 182)
     */
    public static final int[] TOWER_BASE = {181, 182};

    /**
     * The {@link entity.particle.CannonBullet Cannon Bullets}
     * fired from {@link entity.tower.CannonTower Cannon Towers}.
     */
    public static final int CANNON_BULLET = 272;

    /**
     * The {@link entity.tower.MachineGunTower Machine Gun Tower}'s firing particles.
     */
    public static final int MG_FIRE = 295;

    /**
     * The {@link entity.tower.CannonTower Cannon Tower}'s firing particles.
     */
    public static final int CANNON_FIRE = 296;

    /**
     * The turret sprites for both levels of {@link entity.tower.RocketTower Rocket Towers}.
     */
    public static final int[] ROCKET_TURRET = {229, 228};

    /**
     * The rocket images for both levels of {@link entity.particle.RocketBullet Rocket Bullets}.
     */
    public static final int[] ROCKET = {252, 251};

    /**
     * Randomizes a tower base sprite.
     * @return The randomized tower base sprite.
     */
    public static int getRandomTowerBase() {
        return TOWER_BASE[random.nextInt(2)];
    }
}