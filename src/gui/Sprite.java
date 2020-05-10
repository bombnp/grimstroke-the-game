package gui;

import java.util.Random;

public class Sprite {
    public static Random random = new Random();

    public static final int BUILD_SPOT = 16;
    public static final int[] TOWER_BASE = {181, 182};
    public static final int MG_FIRE = 295;
    public static final int CANNON_FIRE = 296;
    public static final int[] ROCKET_TURRET = {229, 228};
    public static final int[] ROCKET = {252, 251};

    public static int getRandomTowerBase() {
        return TOWER_BASE[random.nextInt(2)];
    }
}