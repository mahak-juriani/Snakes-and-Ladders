import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Board {
    private final int size;
    private final Map<Integer, BoardEntity> byStart = new HashMap<>();

    public Board(int size, List<BoardEntity> entities) {
        this.size = size;
        for (BoardEntity e : entities) {
            byStart.put(e.start(), e);
        }
    }

    public int size() { return size; }

    public int applyEntityIfAny(int pos) {
        BoardEntity e = byStart.get(pos);
        return (e == null) ? pos : e.apply(pos);
    }
}