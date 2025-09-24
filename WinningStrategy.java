public interface WinningStrategy {
    MoveOutcome applyMove(int currentPos, int roll, int lastSquare);
}