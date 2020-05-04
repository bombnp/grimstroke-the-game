package logic;

public class TowerData {
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