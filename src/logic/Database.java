package logic;

import application.Utility;

import java.util.ArrayList;

public class Database {
    public static TowerData MG, R1, R2, C1, C2;
    public static MinionData conscript, armoredConscript, infantry, combatMedic, lightTank, heavyTank, lightPlane, fighterPlane;
    public static ArrayList<MinionData> minionList = new ArrayList<>();

    public static void initialize() {
        String[][] towerData = Utility.readCSV("data/TowerData.csv");
        String[][] minionData = Utility.readCSV("data/MinionData.csv");

        MG = new TowerData(towerData[0]);
        R1 = new TowerData(towerData[1]);
        R2 = new TowerData(towerData[2]);
        C1 = new TowerData(towerData[3]);
        C2 = new TowerData(towerData[4]);

        minionList.add(conscript = new MinionData(minionData[0]));
        minionList.add(armoredConscript = new MinionData(minionData[1]));
        minionList.add(infantry = new MinionData(minionData[2]));
        minionList.add(combatMedic = new MinionData(minionData[3]));
        minionList.add(lightTank = new MinionData(minionData[4]));
        minionList.add(heavyTank = new MinionData(minionData[5]));
        minionList.add(lightPlane = new MinionData(minionData[6]));
        minionList.add(fighterPlane = new MinionData(minionData[7]));
    }
}
