import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public final class Game {
    private final String gameId = "GAME-" + System.currentTimeMillis();
    private final GameConfig config;
    private final Board board;
    private final Queue<Player> turnOrder;
    private final Dice dice;
    private final GameStorage storage;
    private int turnCount = 0;

    public Game(GameConfig config, List<Player> players, GameStorage storage) {
        if (players == null || players.size() < 2)
            throw new IllegalArgumentException("Need at least 2 players");
        this.config = config;
        this.board = new Board(config.getBoardSize(), config.getEntities());
        this.turnOrder = new ArrayDeque<>(players);
        this.dice = Dice.getInstance(config.getDiceSides(), config.getNumDice(), config.getSeed().orElse(null));
        this.storage = storage;
    }

    public void play() {
        System.out.println("=== Snake & Ladder ===");
        System.out.println("Players: " + playerNames());
        System.out.println("Board last square: " + config.getBoardSize());
        System.out.println("Start rule: " + config.getStartStrategy().getClass().getSimpleName());
        System.out.println("Win rule:   " + config.getWinningStrategy().getClass().getSimpleName());
        System.out.println();

        boolean finished = false;
        while (!finished) {
            Player p = turnOrder.poll();
            finished = takeTurn(p);
            turnOrder.offer(p);
        }
    }

    private boolean takeTurn(Player p) {
        turnCount++;
        int roll = dice.roll();

        if (!p.started()) {
            if (config.getStartStrategy().canStart(roll)) {
                p.markStarted();
            } else {
                announce(p, roll, p.position(), p.position(), "(didn't start)");
                storage.recordMove(gameId, p.id(), p.name(), p.position(), roll, p.position());
                return false;
            }
        }

        int prev = p.position();
        MoveOutcome move = config.getWinningStrategy().applyMove(prev, roll, config.getBoardSize());
        int landed = move.newPos();

        if (landed > 0 && landed < board.size()) {
            landed = board.applyEntityIfAny(landed);
        }

        p.setPosition(landed);
        announce(p, roll, prev, landed, null);
        storage.recordMove(gameId, p.id(), p.name(), prev, roll, landed);

        if (move.hasWon() || landed == config.getBoardSize()) {
            System.out.printf("🏆 %s WINS after %d turns!%n", p.name(), turnCount);
            storage.recordWinner(gameId, p.id(), p.name(), turnCount);
            return true;
        }
        return false;
    }

    private void announce(Player p, int roll, int from, int to, String extra) {
        String delta = (from == to) ? "" : " -> " + to;
        String tail = (extra == null) ? "" : " " + extra;
        System.out.printf("%s (%s) rolled %d: %d%s%s%n",
                p.name(), p.type(), roll, from, delta, tail);
    }

    private String playerNames() {
        StringBuilder sb = new StringBuilder();
        for (Player p : turnOrder) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(p.name());
        }
        return sb.toString();
    }
}