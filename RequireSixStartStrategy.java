public final class RequireSixStartStrategy implements StartStrategy {
    @Override public boolean canStart(int roll) { return roll == 6; }
}