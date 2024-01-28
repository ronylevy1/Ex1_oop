import java.util.Comparator;

public class Comparator2 implements Comparator<ConcretePiece> {
    private boolean blueWin;

    public Comparator2(boolean blueWin) {
        this.blueWin = blueWin;
    }

    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o2.getKills() != o1.getKills()) {
            return o2.getKills() - o1.getKills();
        }

        if (o2.getTag() != o1.getTag()) {
            return o1.getTag() - o2.getTag();
        }

        if (blueWin) {
            if (o1.getOwner().isPlayerOne()) {
                return -1;
            }
            return 1;
        } else {
            if (!o1.getOwner().isPlayerOne()) {
                return -1;
            }
            return 1;
        }
    }
}
