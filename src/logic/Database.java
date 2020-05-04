package logic;

import application.Utility;

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
