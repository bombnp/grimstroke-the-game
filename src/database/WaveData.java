package database;

import java.util.stream.Stream;

public class WaveData {
    public int[] minionsCount;

    public WaveData(String[] data) {
        minionsCount = Stream.of(data).map(Integer::parseInt).mapToInt(x -> x).toArray();
    }
}
