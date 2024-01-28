public class ConcretePlayer implements Player{
    private final boolean isPlayerOne;
    private final int getWins;
    public ConcretePlayer(boolean isPlayerOne, int getWins){
        this.isPlayerOne = isPlayerOne;
        this.getWins = getWins;
    }
    @Override
    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    @Override
    public int getWins() {
        return getWins;
    }
}
