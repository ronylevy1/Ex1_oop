import java.awt.*;

public class Pawn extends ConcretePiece {
    private int eat; // How much player the specific player eat
    private static final String p_one = "♙";
    private static final String p_two = "♟";
    private String type;


    public Pawn(ConcretePlayer owner, int tag) {
        super(owner, tag);
        if (owner.isPlayerOne()) {
            type = p_one;
        } else {
            type = p_two;
        }
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (getOwner().isPlayerOne()) {
            return "D" + tag + ": ";
        }
        return "A" + tag + ": ";
    }
}
