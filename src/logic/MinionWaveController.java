package logic;

import application.Utility;
import entity.minion.AirUnit;
import entity.minion.GroundUnit;

public class MinionWaveController{
	private final String[][] waveData;
	private int waveNumber;
	public MinionWaveController() {
		waveNumber = 0;
		waveData = Utility.readCSV("data/WaveData.csv");
	}
	public void generateNextWave() {
		waveNumber++;

		System.out.println("WAVE : "+ waveNumber);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println(waveData[0][i]+" : "+ waveData[waveNumber][i]);
			for(int j = 0; j < Integer.parseInt(waveData[waveNumber][i]) ; j++) {
				boolean isFlying = Database.minionList.get(i).isFlying;
				if (isFlying) {
					new AirUnit(Database.minionList.get(i));
				} else {
					new GroundUnit(Database.minionList.get(i));
				}
			}
		}
	}
}
