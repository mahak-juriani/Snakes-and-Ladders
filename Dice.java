import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Dice {
    private static volatile Dice INSTANCE;

    private final int sides;
    private final int numDice;
    private final Random rng;

    private Dice(int sides, int numDice, Long seed) {
        this.sides = sides;
        this.numDice = numDice;
        this.rng = (seed == null) ? ThreadLocalRandom.current() : new Random(seed);
    }

    public static Dice getInstance(int sides, int numDice, Long seed) {
        Dice local = INSTANCE;
        if (local == null) {
            synchronized (Dice.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Dice(sides, numDice, seed);
                }
                local = INSTANCE;
            }
        }
        return local;
    }

    public int roll() {
        int total = 0;
        for (int i = 0; i < numDice; i++) {
            total += 1 + rng.nextInt(sides);
        }
        return total;
    }
}
