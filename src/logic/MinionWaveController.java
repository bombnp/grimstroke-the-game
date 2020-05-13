package logic;

import database.Database;
import database.MinionData;
import database.WaveData;
import entity.minion.AirUnit;
import entity.minion.GroundUnit;
import gui.WinGamePane;
import javafx.application.Platform;
import javafx.scene.chart.PieChart.Data;

public class MinionWaveController{
	public static int waveNumber;
	private final int TIME_BETWEEN_SPAWN = 500; // ms

	public MinionWaveController() {
		waveNumber = 0;
	}
	public void generateNextWave() {
		if(waveNumber+1 <= Database.waves.length-1) {
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
		}else {
			new WinGamePane();
		}
	}
}
