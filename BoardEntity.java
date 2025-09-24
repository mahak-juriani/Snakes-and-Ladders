public interface BoardEntity {
    int start();
    int end();

    default int apply(int currentPos) { return end(); }

    default void validateWithin(int boardSize) {
        if (start() <= 1 || start() >= boardSize) {
            throw new IllegalArgumentException(getClass().getSimpleName()+": start must be in (1, boardSize)");
        }
        if (end() <= 1 || end() >= boardSize) {
            throw new IllegalArgumentException(getClass().getSimpleName()+": end must be in (1, boardSize)");
        }
        if (start() == end()) {
            throw new IllegalArgumentException(getClass().getSimpleName()+": start cannot equal end");
        }
    }
}