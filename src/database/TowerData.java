package database;

/**
 * The TowerData class stores information of each {@link entity.tower.base.Tower Tower}.
 * The data can be accessed publicly from the {@link Database} class.
 */
public class TowerData {
    /**
     * The index of the sprite that represents the turret image of this tower.
     */
    public int spriteIndex;

    /**
     * The name of this tower.
     */
    public String name;

    /**
     * The minimum amount of damage this tower can deal.
     */
    public double minDamage;

    /**
     * The maximum amount of damage this tower can deal.
     */
    public double maxDamage;

    /**
     * The rate in which this tower fires, measured in shots/second.
     */
    public double rate;

    /**
     * The range of this tower, measured in pixels.
     */
    public double range;

    /**
     * The cost of constructing this tower.
     */
    public int cost;

    /**
     * Constructor for the {@link TowerData} class.
     * This constructor receives an array of data and deconstruct them into fields.
     *
     * @param data The data of this tower.
     * @see entity.tower.base.Tower Tower
     */
    public TowerData(String[] data) {
        spriteIndex = Integer.parseInt(data[0]);
        name = data[1];
        minDamage = Double.parseDouble(data[2]);
        maxDamage = Double.parseDouble(data[3]);
        rate = Double.parseDouble(data[4]);
        range = Double.parseDouble(data[5]);
        cost = Integer.parseInt(data[6]);
    }
}
