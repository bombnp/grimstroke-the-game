package logic;

import database.Database;
import database.MinionData;
import entity.minion.AirUnit;
import entity.minion.GroundUnit;
import javafx.application.Platform;

public class MinionWaveController{
	private int waveNumber;
	private final int TIME_BETWEEN_SPAWN = 500; // ms

	public MinionWaveController() {
		waveNumber = 0;
	}
	public void generateNextWave() {
		new Thread(() -> {
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0; j < Database.waves[waveNumber].minionsCount[i] ; j++) {
					MinionData minionData = Database.minions[i];

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

		waveNumber++;
	}
}
