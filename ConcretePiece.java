import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {
    public final ConcretePlayer owner;
    protected ArrayList<Position> stepsArr = new ArrayList<>();
    protected int tag;
    protected int kills;
    protected int squares;
    protected int pieces;

    public ConcretePiece(ConcretePlayer owner, int tag) {
        this.owner = owner;
        this.tag = tag;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    public int getTag() {
        return tag;
    }

    public int getKills() {
        return kills;
    }

    public void setKills() {
        this.kills ++;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces() {
        this.pieces ++;
    }

    public int getSquares() {
        return squares;
    }
    public void setSquares(int squares) {
        this.squares = squares;
    }
}
