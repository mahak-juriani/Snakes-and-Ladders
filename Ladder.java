public final class Ladder implements BoardEntity {
    private final int bottom;
    private final int top;

    public Ladder(int from, int to) {
        if (to <= from) throw new IllegalArgumentException("Ladder must go up");
        this.bottom = from; this.top = to;
    }
    public int start() { return bottom; }
    public int end() { return top; }
}