import java.util.Comparator;

public class Comparator1 implements Comparator<ConcretePiece> {

    @Override
    public int compare(ConcretePiece c1, ConcretePiece c2) {
        if (c1.stepsArr.size() != c2.stepsArr.size()) {
            return c1.stepsArr.size() - c2.stepsArr.size();
        }
        // האורך של הצעדים שלהם זהה - תמיין לפי המספר שלו
        return c1.getTag() - c2.getTag();
    }
}
