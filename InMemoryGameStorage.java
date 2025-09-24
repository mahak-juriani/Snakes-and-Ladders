import java.util.UUID;

public final class InMemoryGameStorage implements GameStorage {
    @Override
    public void recordMove(String gameId, UUID playerId, String playerName, int from, int roll, int to) {
    }
    @Override
    public void recordWinner(String gameId, UUID playerId, String playerName, int totalTurns) {
    }
}