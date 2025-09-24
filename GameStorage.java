import java.util.UUID;

public interface GameStorage {
    void recordMove(String gameId, UUID playerId, String playerName, int from, int roll, int to);
    void recordWinner(String gameId, UUID playerId, String playerName, int totalTurns);
}