public final class ExactLandingWinningStrategy implements WinningStrategy {
    @Override
    public MoveOutcome applyMove(int currentPos, int roll, int lastSquare) {
        int tentative = currentPos + roll;
        if (tentative == lastSquare) return new MoveOutcome(tentative, true);
        if (tentative > lastSquare)  return new MoveOutcome(currentPos, false);
        return new MoveOutcome(tentative, false);
    }
}