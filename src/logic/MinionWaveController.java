package logic;

import application.Utility;
import entity.minion.AirUnit;
import entity.minion.GroundUnit;
import javafx.application.Platform;

public class MinionWaveController{
	private final String[][] waveData;
	private int waveNumber;
	private final int TIME_BETWEEN_SPAWN = 500; // ms

	public MinionWaveController() {
		waveNumber = 0;
		waveData = Utility.readCSV("data/WaveData.csv");
	}
	public void generateNextWave() {
		waveNumber++;

		new Thread(() -> {
			System.out.println("WAVE : "+ waveNumber);
			for(int i = 0 ; i < 8 ; i++) {
				System.out.println(waveData[0][i]+" : "+ waveData[waveNumber][i]);
				for(int j = 0; j < Integer.parseInt(waveData[waveNumber][i]) ; j++) {
					MinionData minionData = Database.minionList.get(i);

					Platform.runLater(() -> {
						boolean isFlying = minionData.isFlying;
						if (isFlying) {
							new AirUnit(minionData);
						} else {
							new GroundUnit(minionData);
						}
					});

					try {
						//noinspection BusyWait
						Thread.sleep(TIME_BETWEEN_SPAWN);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
