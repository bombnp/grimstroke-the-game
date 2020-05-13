package database;

import java.util.stream.Stream;

/**
 * The WaveData class stores information of each wave.
 * The data can be accessed publicly from the {@link Database} class.
 */
public class WaveData {
    /**
     * The number of minions for each type in this wave.
     */
    public int[] minionsCount;

    /**
     * Constructor for the {@link WaveData} class.
     * This constructor receives an array of data and deconstruct them into fields.
     *
     * @param data The data of this wave.
     */
    public WaveData(String[] data) {
        minionsCount = Stream.of(data).map(Integer::parseInt).mapToInt(x -> x).toArray();
    }
}
