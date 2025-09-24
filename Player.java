import java.util.Objects;
import java.util.UUID;

public final class Player {
    private final UUID id = UUID.randomUUID();
    private final String name;
    private final PlayerType type;
    private int position = 0;     // 0 = not on board
    private boolean started = false;

    private Player(String name, PlayerType type) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
    }

    public static Player human(String name) { return new Player(name, PlayerType.HUMAN); }
    public static Player bot(String name)    { return new Player(name, PlayerType.BOT); }

    public UUID id() { return id; }
    public String name() { return name; }
    public PlayerType type() { return type; }
    public int position() { return position; }
    public boolean started() { return started; }

    void markStarted() { this.started = true; }
    void setPosition(int pos) { this.position = pos; }
}