package gui;

import java.util.Random;

public class Sprite {
    public static Random random = new Random();

    public static final int BUILD_SPOT = 16;
    public static final int DESTROY_TOOL = 86;
    public static final int[] TOWER_BASE = {181, 182};
    public static final int MACHINE_GUN_TURRET = 203;
    public static final int ROCKET_TURRET_1 = 205;
    public static final int ROCKET_TURRET_2 = 206;
    public static final int CANNON_TURRET_1 = 249;
    public static final int CANNON_TURRET_2 = 250;

    public static int getRandomTowerBase() {
        return TOWER_BASE[random.nextInt(2)];
    }
}