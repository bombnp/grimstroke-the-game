package logic;

import application.Utility;
import entity.minions.AirUnit;
import entity.minions.GroundUnit;

public class MinionWaveController{
	private static String[][] MinionsData;
	private static String[][] WaveData;
	private static int WaveNumber;
	public MinionWaveController() {
		WaveNumber = 0;
		MinionsData = Utility.readCSV("data/MinionsData.csv");
		WaveData = Utility.readCSV("data/WaveData.csv");
	}
	public void nextWave() {
		WaveNumber++;
		getWaveMinions();
	}
	public void getWaveMinions() {
		System.out.println("WAVE : "+ WaveNumber);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println(WaveData[0][i]+" : "+WaveData[WaveNumber][i]);
			for(int j = 0 ; j < Integer.parseInt(WaveData[WaveNumber][i]) ; j++) {
				boolean isGroundUnit = Boolean.parseBoolean(MinionsData[i][3]);
				int spriteID = Integer.parseInt(MinionsData[i][8]);
				if(isGroundUnit) {
					spawnGroundUnit(spriteID, i);
				}else {
					spawnAirUnit(spriteID, i);
				}
			}
		}
	}
	public void spawnAirUnit(int spriteId,int type) {
		AirUnit newUnit = new AirUnit(spriteId, type);
		newUnit.setName(getName(type));
		newUnit.setDesc(getDesc(type));
		newUnit.setReward(getReward(type));
		newUnit.setType(getType(type));
		newUnit.setSpeed(getSpeed(type));
		newUnit.setResistance(getResistance(type));
	}
	public void spawnGroundUnit(int spriteId,int type) {
		GroundUnit newUnit = new GroundUnit(spriteId, type);
		newUnit.setName(getName(type));
		newUnit.setDesc(getDesc(type));
		newUnit.setReward(getReward(type));
		newUnit.setType(getType(type));
		newUnit.setSpeed(getSpeed(type));
		newUnit.setResistance(getResistance(type));
	}
	public String getName(int type) {
		return MinionsData[type][0];
	}
	public String getDesc(int type) {
		return MinionsData[type][1];
	}
	public int getReward(int type) {
		return Integer.parseInt(MinionsData[type][2]);
	}
	public boolean getType(int type) {
		return Boolean.parseBoolean(MinionsData[type][3]);
	}
	public float getSpeed(int type) {
		return Float.parseFloat(MinionsData[type][4]);
	}
	public float[] getResistance(int type){
		return new float[] {Float.parseFloat(MinionsData[type][5]),
							Float.parseFloat(MinionsData[type][6]),
							Float.parseFloat(MinionsData[type][7])};
	}
}
