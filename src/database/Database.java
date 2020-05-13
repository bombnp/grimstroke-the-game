package database;

import application.Utility;

public class Database {
    public static TowerData[] MG, Rocket, Cannon;
    public static MinionData[] minions;
    public static WaveData[] waves;

    public static void initialize() {
        String[][] towerData = Utility.readCSV("data/TowerData.csv");
        String[][] minionData = Utility.readCSV("data/MinionData.csv");
        String[][] waveData = Utility.readCSV("data/WaveData.csv");

        MG = new TowerData[]{new TowerData(towerData[0]), new TowerData(towerData[1])};
        Rocket = new TowerData[]{new TowerData(towerData[2]), new TowerData(towerData[3])};
        Cannon = new TowerData[]{new TowerData(towerData[4]), new TowerData(towerData[5])};

        minions = new MinionData[]{
                new MinionData(minionData[0]),
                new MinionData(minionData[1]),
                new MinionData(minionData[2]),
                new MinionData(minionData[3]),
                new MinionData(minionData[4]),
                new MinionData(minionData[5]),
                new MinionData(minionData[6]),
                new MinionData(minionData[7])
        };

        waves = new WaveData[waveData.length-1];
        for (int i = 1; i < waves.length; i++) {
            waves[i] = new WaveData(waveData[i]);
        }
    }
}
