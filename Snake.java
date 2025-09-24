public final class Snake implements BoardEntity {
    private final int head;
    private final int tail;

    public Snake(int from, int to) {
        if (to >= from) throw new IllegalArgumentException("Snake must go down");
        this.head = from; this.tail = to;
    }
    public int start() { return head; }
    public int end() { return tail; }
}
