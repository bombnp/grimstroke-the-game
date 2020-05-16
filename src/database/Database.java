package database;

import application.Utility;
import entity.minion.Minion;

/**
 * The Database class stores all information of {@link entity.tower.base.Tower Towers},
 * {@link Minion Minions}, and {@link WaveData Waves}. <br>
 * <br>
 * The {@link #initialize()} method is called at the start of the application
 * and loads all types of data into each respective fields. The class stores tower data in three
 * arrays of {@link TowerData TowerDatas}, one for each {@link entity.tower.base.Tower Tower} type
 * (Machine Gun, Rocket, Cannon). For {@link Minion Minion}, the data is stored in an array of 8,
 * one for each type of {@link Minion Minions}. Lastly, the {@link WaveData WaveData} is stored
 * as an array of each wave.
 *
 * @see TowerData
 * @see MinionData
 * @see WaveData
 */
public class Database {
    /**
     * Data of {@link entity.tower.MachineGunTower Machine Gun Tower},
     * such as name, damage, rate of fire, range, etc.
     *
     * @see TowerData
     * @see entity.tower.base.Tower Tower
     * @see entity.tower.MachineGunTower MachineGunTower
     */
    public static TowerData[] MG;

    /**
     * Data of {@link entity.tower.RocketTower Rocket Tower},
     * such as name, damage, rate of fire, range, etc.
     *
     * @see TowerData
     * @see entity.tower.base.Tower Tower
     * @see entity.tower.RocketTower RocketTower
     */
    public static TowerData[] Rocket;

    /**
     * Data of {@link entity.tower.CannonTower Cannon Tower},
     * such as name, damage, rate of fire, range, etc.
     *
     * @see TowerData
     * @see entity.tower.base.Tower Tower
     * @see entity.tower.CannonTower CannonTower
     */
    public static TowerData[] Cannon;

    /**
     * Data of all {@link Minion Minions},
     * such as name, health, speed, resistances, etc. Data is stored
     * as an array of {@link MinionData data} of each {@link Minion Minion}.
     *
     * @see MinionData
     * @see Minion Minion
     */
    public static MinionData[] minions;

    /**
     * Stores all {@link WaveData Wave} information, such as minion counts in each wave.
     * Data is stored as an array of {@link WaveData Waves}.
     *
     * @see WaveData
     * @see logic.MinionWaveController MinionWaveController
     */
    public static WaveData[] waves;

    /**
     * Initializes the database. This method reads from the data files and load them into public fields for access.
     */
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
        waves = new WaveData[waveData.length];
        for (int i = 1; i < waves.length; i++) {
            waves[i] = new WaveData(waveData[i]);
        }
    }
}
