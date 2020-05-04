package logic;

import application.Utility;

class TowerData {
    public int spriteIndex;
    public String name;
    public int minDamage;
    public int maxDamage;
    public double rateOfFire; // shots/second
    public int range;

    public TowerData(String[] data) {
        spriteIndex = Integer.parseInt(data[0]);
        name = data[1];
        minDamage = Integer.parseInt(data[2]);
        maxDamage = Integer.parseInt(data[3]);
        rateOfFire = Double.parseDouble(data[4]);
        range = Integer.parseInt(data[5]);
    }
}

class MinionData {
    public int spriteIndex;
    public String name;
    public String description;
    public int reward;
    public double speed; // pixels/second
    public double resist_MG;
    public double resist_Rocket;
    public double resist_Cannon;

    public MinionData(String[] data) {
        spriteIndex = Integer.parseInt(data[0]);
        name = data[1];
        description = data[2];
        reward = Integer.parseInt(data[3]);
        speed = Double.parseDouble(data[4]);
        resist_MG = Double.parseDouble(data[5]);
        resist_Rocket = Double.parseDouble(data[6]);
        resist_Cannon = Double.parseDouble(data[7]);
    }
}

public class Database {
    public static TowerData MG, R1, R2, C1, C2;
    public static MinionData conscript, armoredConscript, infantry, combatMedic, lightTank, heavyTank, lightPlane, fighterPlane;

    public static void initialize() {
        String[][] towerData = Utility.readCSV("data/TowerData.csv");
        String[][] minionData = Utility.readCSV("data/MinionData.csv");

        MG = new TowerData(towerData[0]);
        R1 = new TowerData(towerData[1]);
        R2 = new TowerData(towerData[2]);
        C1 = new TowerData(towerData[3]);
        C2 = new TowerData(towerData[4]);

        conscript = new MinionData(minionData[0]);
        armoredConscript = new MinionData(minionData[1]);
        infantry = new MinionData(minionData[2]);
        combatMedic = new MinionData(minionData[3]);
        lightTank = new MinionData(minionData[4]);
        heavyTank = new MinionData(minionData[5]);
        lightPlane = new MinionData(minionData[6]);
        fighterPlane = new MinionData(minionData[7]);
    }
}
