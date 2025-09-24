import java.util.*;

public final class GameConfig {
    private final int boardSize;
    private final List<BoardEntity> entities;
    private final StartStrategy startStrategy;
    private final WinningStrategy winningStrategy;
    private final int diceSides;
    private final int numDice;
    private final Long seed;

    private GameConfig(
            int boardSize,
            List<BoardEntity> entities,
            StartStrategy startStrategy,
            WinningStrategy winningStrategy,
            int diceSides,
            int numDice,
            Long seed
    ) {
        this.boardSize = boardSize;
        this.entities = Collections.unmodifiableList(new ArrayList<>(entities));
        this.startStrategy = startStrategy;
        this.winningStrategy = winningStrategy;
        this.diceSides = diceSides;
        this.numDice = numDice;
        this.seed = seed;
    }

    public int getBoardSize() { return boardSize; }
    public List<BoardEntity> getEntities() { return entities; }
    public StartStrategy getStartStrategy() { return startStrategy; }
    public WinningStrategy getWinningStrategy() { return winningStrategy; }
    public int getDiceSides() { return diceSides; }
    public int getNumDice() { return numDice; }
    public Optional<Long> getSeed() { return Optional.ofNullable(seed); }

    public static class Builder {
        private int boardSize = 100;
        private final List<BoardEntity> entities = new ArrayList<>();
        private StartStrategy startStrategy = new FreeStartStrategy();
        private WinningStrategy winningStrategy = new ExactLandingWinningStrategy();
        private int diceSides = 6;
        private int numDice = 1;
        private Long seed = null;

        public Builder boardSize(int boardSize) {
            if (boardSize < 10) throw new IllegalArgumentException("boardSize too small");
            this.boardSize = boardSize;
            return this;
        }

        public Builder addEntity(BoardEntity entity) {
            entities.add(entity);
            return this;
        }

        public Builder addSnakes(Map<Integer,Integer> fromTo) {
            fromTo.forEach((from,to) -> entities.add(new Snake(from,to)));
            return this;
        }

        public Builder addLadders(Map<Integer,Integer> fromTo) {
            fromTo.forEach((from,to) -> entities.add(new Ladder(from,to)));
            return this;
        }

        public Builder startStrategy(StartStrategy s) { this.startStrategy = s; return this; }
        public Builder winningStrategy(WinningStrategy s) { this.winningStrategy = s; return this; }
        public Builder diceSides(int sides) {
            if (sides < 2) throw new IllegalArgumentException("dice sides >= 2");
            this.diceSides = sides; return this;
        }
        public Builder numDice(int n) {
            if (n < 1) throw new IllegalArgumentException("numDice >= 1");
            this.numDice = n; return this;
        }
        public Builder seed(Long seed) { this.seed = seed; return this; }

        public GameConfig build() {
            for (BoardEntity e : entities) e.validateWithin(boardSize);
            return new GameConfig(boardSize, entities, startStrategy, winningStrategy, diceSides, numDice, seed);
        }
    }
}
