package database;

public class TowerData {
    public int spriteIndex;
    public String name;
    public double minDamage;
    public double maxDamage;
    public double rate; // shots/second
    public double range;
    public int cost;

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
