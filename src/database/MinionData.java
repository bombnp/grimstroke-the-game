package database;

import entity.minion.Minion;

/**
 * The MinionData class stores information of each {@link Minion Minion}.
 * The data can be accessed publicly from the {@link Database} class.
 */
public class MinionData {
    /**
     * The index of the sprite that represents this minion.
     */
    public int spriteIndex;

    /**
     * The name of this minion.
     */
    public String name;

    /**
     * A short description of this minion.
     */
    public String description;

    /**
     * The amount of gold the player receives when this minion is killed.
     */
    public int reward;

    /**
     * The amount of health this unit has.
     */
    public double health;

    /**
     * The speed in which this minion travels, measured in cells/second.
     */
    public double speed;

    /**
     * The minion's resistance to {@link entity.tower.MachineGunTower Machine Gun Towers}.
     */
    public double resist_MG;

    /**
     * The minion's resistance to {@link entity.tower.RocketTower Rocket Towers}.
     */
    public double resist_Rocket;

    /**
     * The minion's resistance to {@link entity.tower.CannonTower Cannon Towers}.
     */
    public double resist_Cannon;

    /**
     * Specifies whether this minion is flying or not.
     * Flying units can only be targeted by {@link entity.tower.MachineGunTower Machine Gun Towers}.
     */
    public boolean isFlying;

    /**
     * The amount of health loss if the minion reaches the end of the path.
     */
    public int penalty;

    /**
     * Constructor for the {@link MinionData} class. This constructor receives an array of data
     * and deconstruct them into fields.
     *
     * @param data The data of this minion.
     * @see Minion Minion.
     */
    public MinionData(String[] data) {
        spriteIndex = Integer.parseInt(data[0]);
        name = data[1];
        description = data[2];
        reward = Integer.parseInt(data[3]);
        health = Double.parseDouble(data[4]);
        speed = Double.parseDouble(data[5]);
        resist_MG = Double.parseDouble(data[6]);
        resist_Rocket = Double.parseDouble(data[7]);
        resist_Cannon = Double.parseDouble(data[8]);
        isFlying = Boolean.parseBoolean(data[9]);
        penalty = Integer.parseInt(data[10]);
    }
}
