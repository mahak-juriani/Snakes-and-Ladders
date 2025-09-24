
public final class PassOrEqualWinningStrategy implements WinningStrategy {
    @Override
    public MoveOutcome applyMove(int currentPos, int roll, int lastSquare) {
        int tentative = currentPos + roll;
        if (tentative >= lastSquare) return new MoveOutcome(lastSquare, true);
        return new MoveOutcome(tentative, false);
    }
}
