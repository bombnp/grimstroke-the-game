package logic;

import application.Utility;
import entity.minion.AirUnit;
import entity.minion.GroundUnit;

public class MinionWaveController{
	private final String[][] minionsData;
	private final String[][] waveData;
	private int waveNumber;
	public MinionWaveController() {
		waveNumber = 0;
		minionsData = Utility.readCSV("data/MinionData.csv");
		waveData = Utility.readCSV("data/WaveData.csv");
	}
	public void generateNextWave() {
		waveNumber++;

		System.out.println("WAVE : "+ waveNumber);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println(waveData[0][i]+" : "+ waveData[waveNumber][i]);
			for(int j = 0; j < Integer.parseInt(waveData[waveNumber][i]) ; j++) {
				boolean isGroundUnit = Boolean.parseBoolean(minionsData[i][3]);
				int spriteID = Integer.parseInt(minionsData[i][8]);
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
		return minionsData[type][0];
	}
	public String getDesc(int type) {
		return minionsData[type][1];
	}
	public int getReward(int type) {
		return Integer.parseInt(minionsData[type][2]);
	}
	public boolean getType(int type) {
		return Boolean.parseBoolean(minionsData[type][3]);
	}
	public float getSpeed(int type) {
		return Float.parseFloat(minionsData[type][4]);
	}
	public float[] getResistance(int type){
		return new float[] {Float.parseFloat(minionsData[type][5]),
							Float.parseFloat(minionsData[type][6]),
							Float.parseFloat(minionsData[type][7])};
	}
}
