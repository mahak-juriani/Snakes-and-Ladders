public final class MoveOutcome {
    private final int newPos;
    private final boolean hasWon;

    public MoveOutcome(int newPos, boolean hasWon) {
        this.newPos = newPos;
        this.hasWon = hasWon;
    }
    public int newPos() { return newPos; }
    public boolean hasWon() { return hasWon; }
}