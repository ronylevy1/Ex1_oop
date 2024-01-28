public class King extends ConcretePiece {
    private static final String k = "♔";

    public King(ConcretePlayer owner) {
        super(owner, 7);
    }

    @Override
    public String getType() {
        return k;
    }

    @Override
    public String toString() {
        return "K7: " ;
    }
}
