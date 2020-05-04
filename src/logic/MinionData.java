package logic;

public class MinionData {
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
