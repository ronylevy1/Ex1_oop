import java.util.ArrayList;

public class GameLogic implements PlayableLogic {
    private ConcretePiece[][] board; // Declares a board of type Concrete Piece
    private final ConcretePlayer firstPlayer; // Declares the first player to be a Concrete Player
    private final ConcretePlayer secondPlayer; // Declares the second player to be a Concrete Player
    private Position kingPos; // Declare of the current position of the king
    private boolean isFirstPlayerTurn; // Declare the turn of the first player
    private boolean gameFinish;
    private ArrayList<ConcretePiece> nameListArr = new ArrayList<>(); // The name list
    int[][][] tempArr = new int[11][11][37];
     private boolean blueWon;

    public GameLogic() {
        board = new ConcretePiece[11][11];
        firstPlayer = new ConcretePlayer(true, 0);
        secondPlayer = new ConcretePlayer(false, 0);
        kingPos = new Position(5, 5);
        initialize();

    }

    public void initialize() {
        isFirstPlayerTurn = false;
        blueWon = false;
        // Put the king
        board[5][5] = new King(firstPlayer);
        // Put the blue player
        board[5][3] = new Pawn(firstPlayer, 5);
        board[5][4] = new Pawn(firstPlayer, 6);
        board[5][6] = new Pawn(firstPlayer, 8);
        board[5][7] = new Pawn(firstPlayer, 9);
        board[4][4] = new Pawn(firstPlayer, 2);
        board[6][4] = new Pawn(firstPlayer, 10);
        board[7][5] = new Pawn(firstPlayer, 13);
        board[6][5] = new Pawn(firstPlayer, 11);
        board[4][5] = new Pawn(firstPlayer, 3);
        board[3][5] = new Pawn(firstPlayer, 1);
        board[6][6] = new Pawn(firstPlayer, 12);
        board[4][6] = new Pawn(firstPlayer, 4);
        // Put the red player
        board[3][0] = new Pawn(secondPlayer, 7);
        board[4][0] = new Pawn(secondPlayer, 9);
        board[5][0] = new Pawn(secondPlayer, 11);
        board[6][0] = new Pawn(secondPlayer, 15);
        board[7][0] = new Pawn(secondPlayer, 17);
        board[5][1] = new Pawn(secondPlayer, 12);
        board[10][3] = new Pawn(secondPlayer, 20);
        board[10][4] = new Pawn(secondPlayer, 21);
        board[10][5] = new Pawn(secondPlayer, 22);
        board[10][6] = new Pawn(secondPlayer, 23);
        board[10][7] = new Pawn(secondPlayer, 24);
        board[9][5] = new Pawn(secondPlayer, 19);
        board[3][10] = new Pawn(secondPlayer, 8);
        board[4][10] = new Pawn(secondPlayer, 10);
        board[5][10] = new Pawn(secondPlayer, 14);
        board[6][10] = new Pawn(secondPlayer, 16);
        board[7][10] = new Pawn(secondPlayer, 18);
        board[5][9] = new Pawn(secondPlayer, 13);
        board[0][3] = new Pawn(secondPlayer, 1);
        board[0][4] = new Pawn(secondPlayer, 2);
        board[0][5] = new Pawn(secondPlayer, 3);
        board[0][6] = new Pawn(secondPlayer, 4);
        board[0][7] = new Pawn(secondPlayer, 5);
        board[1][5] = new Pawn(secondPlayer, 6);


        board[5][5].stepsArr.add(new Position(5, 5));
        board[3][5].stepsArr.add(new Position(5, 3));
        board[4][5].stepsArr.add(new Position(5, 4));
        board[6][5].stepsArr.add(new Position(5, 6));
        board[7][5].stepsArr.add(new Position(5, 7));
        board[4][4].stepsArr.add(new Position(4, 4));
        board[4][6].stepsArr.add(new Position(6, 4));
        board[5][7].stepsArr.add(new Position(7, 5));
        board[5][6].stepsArr.add(new Position(6, 5));
        board[5][4].stepsArr.add(new Position(4, 5));
        board[5][3].stepsArr.add(new Position(3, 5));
        board[6][6].stepsArr.add(new Position(6, 6));
        board[6][4].stepsArr.add(new Position(4, 6));
        board[0][3].stepsArr.add(new Position(3, 0));
        board[0][4].stepsArr.add(new Position(4, 0));
        board[0][5].stepsArr.add(new Position(5, 0));
        board[0][6].stepsArr.add(new Position(6, 0));
        board[0][7].stepsArr.add(new Position(7, 0));
        board[1][5].stepsArr.add(new Position(5, 1));
        board[3][10].stepsArr.add(new Position(10, 3));
        board[4][10].stepsArr.add(new Position(10, 4));
        board[5][10].stepsArr.add(new Position(10, 5));
        board[6][10].stepsArr.add(new Position(10, 6));
        board[7][10].stepsArr.add(new Position(10, 7));
        board[5][9].stepsArr.add(new Position(9, 5));
        board[10][3].stepsArr.add(new Position(3, 10));
        board[10][4].stepsArr.add(new Position(4, 10));
        board[10][5].stepsArr.add(new Position(5, 10));
        board[10][6].stepsArr.add(new Position(6, 10));
        board[10][7].stepsArr.add(new Position(7, 10));
        board[9][5].stepsArr.add(new Position(5, 9));
        board[3][0].stepsArr.add(new Position(0, 3));
        board[4][0].stepsArr.add(new Position(0, 4));
        board[5][0].stepsArr.add(new Position(0, 5));
        board[6][0].stepsArr.add(new Position(0, 6));
        board[7][0].stepsArr.add(new Position(0, 7));
        board[5][1].stepsArr.add(new Position(1, 5));


        // Add to the nameArrList the name of every piece
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (board[i][j] != null) {
                    Position temp = new Position(i, j);
                    nameListArr.add(getPieceAtPosition(temp));
                }
            }
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                for (int t = 0; t < 37; t++) {
                    if (board[j][i] != null) {
                        if (board[j][i].getOwner().isPlayerOne()) {
                            tempArr[j][i][board[j][i].tag - 1] = 1;
                        } else {
                            tempArr[j][i][board[j][i].tag + 12] = 1;
                        }
                    }
                }
            }
        }

    }

    // This function connection to the first Constructor
    public void printUp() {
        nameListArr.sort(new Comparator1());
        // Blue is win
        if (kingInCorner()) {
            for (int i = 0; i < nameListArr.size(); i++) {
                if (nameListArr.get(i).getOwner() == firstPlayer && nameListArr.get(i).stepsArr.size() > 1) {
                    System.out.println(nameListArr.get(i) + nameListArr.get(i).stepsArr.toString());
                }
            }
            for (int i = 0; i < nameListArr.size(); i++) {
                if (nameListArr.get(i).getOwner() == secondPlayer && nameListArr.get(i).stepsArr.size() > 1)
                    System.out.println(nameListArr.get(i) + nameListArr.get(i).stepsArr.toString());
            }
        } else {
            for (int i = 0; i < nameListArr.size(); i++) {
                if (nameListArr.get(i).getOwner() == secondPlayer && nameListArr.get(i).stepsArr.size() > 1) {
                    System.out.println(nameListArr.get(i) + nameListArr.get(i).stepsArr.toString());
                }
            }
            for (int i = 0; i < nameListArr.size(); i++) {
                if (nameListArr.get(i).getOwner() == firstPlayer && nameListArr.get(i).stepsArr.size() > 1)
                    System.out.println(nameListArr.get(i) + nameListArr.get(i).stepsArr.toString());
            }
        }
        boolean flag = false;
        for (int i = 0; !flag && i < 11; i++) {
            for (int j = 0; !flag && j < 11; j++) {
                if (board[i][j] != null && board[i][j].getOwner() == secondPlayer) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            for (int i = 0; i < nameListArr.size(); i++) {
                if (nameListArr.get(i).getOwner() == secondPlayer) {
                    System.out.println(nameListArr.get(i) + nameListArr.get(i).stepsArr.toString());
                }
            }
            for (int i = 0; i < nameListArr.size(); i++) {
                if (nameListArr.get(i).getOwner() == firstPlayer)
                    System.out.println(nameListArr.get(i) + nameListArr.get(i).stepsArr.toString());
            }
        }
        printStar();
    }

    //This function connection to the second Constructor
    public void printKills() {
        nameListArr.sort(new Comparator2(blueWon));

        for (int i = 0; i < nameListArr.size(); i++) {
            if (nameListArr.get(i).getKills() > 0) {
                System.out.println(nameListArr.get(i).toString() + nameListArr.get(i).getKills() + " kills");
            }
        }
        printStar();
    }

    // This function connection to the third Constructor
    public void printSteps() {
        nameListArr.sort(new Comparator3(blueWon));

        for (int i = 0; i < nameListArr.size(); i++) {
            if (nameListArr.get(i).getSquares() > 0) {
                System.out.println(nameListArr.get(i).toString() + nameListArr.get(i).getSquares() + " squares");
            }
        }
        printStar();
    }

    // This unction connection to the forth Constructor
    public void printPieces() {
        // nameListArr.sort(new Comparator4(kingInCorner()));
        ArrayList<Position> tempArrPos = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                int counter = 0;
                for (int t = 0; t < 37; t++) {
                    if (tempArr[j][i][t] != 0) {
                        counter++;
                    }
                }
                if (counter > 1) {
                    tempArrPos.add(new Position(i, j));
                }
            }
        }
        tempArrPos.sort(new Comparator4(tempArr));
        for (int i = 0; i < tempArrPos.size(); i++) {
            int counter = 0;
            for (int t = 0; t < 37; t++) {
                if (tempArr[tempArrPos.get(i).getY()][tempArrPos.get(i).getX()][t] != 0) {
                    counter++;
                }
            }
            System.out.println("" + tempArrPos.get(i) + counter + " pieces");
        }

        printStar();
    }

    public void printStar() {
        System.out.println("***************************************************************************");
    }


    @Override
    public boolean move(Position a, Position b) {
        Piece currentPiece = board[a.getY()][a.getX()];
        ConcretePlayer currentPlayer;

        // Checking whose turn right now
        if (isFirstPlayerTurn) {
            currentPlayer = firstPlayer;
        } else {
            currentPlayer = secondPlayer;
        }

        // Check diagonal path
        if (a.getX() != b.getX() && a.getY() != b.getY()) {
            return false;
        }

        // Check same position
        if (a.getX() == b.getX() && a.getY() == b.getY()) {
            return false;
        }


        // If there is pawn or a king at the current piece, then we cant move there
        if (getPieceAtPosition(b) != null) {
            return false;
        }

        if (currentPiece == null) {
            return false;
        }

        // check if he tries to move piece in his own color
        if (currentPiece.getOwner() != currentPlayer) {
            return false;
        }

        // Don't let the Pawn go to the corner
        if (board[a.getY()][a.getX()] instanceof Pawn) {
            if (b.equals(new Position(0, 0)) || b.equals(new Position(10, 0)) || b.equals(new Position(0, 10)) || b.equals(new Position(10, 10))) {
                return false;
            }
        }

        //
        if (a.getY() > b.getY()) {
            for (int i = a.getY() - 1; i >= b.getY(); i--) {
                Position temp = new Position(a.getX(), i);
                if (getPieceAtPosition(temp) != null && !temp.equals(b)) {
                    return false;
                }
            }
        }
        //
        else if (a.getY() < b.getY()) {
            for (int i = a.getY() + 1; i <= b.getY(); i++) {
                Position temp = new Position(a.getX(), i);
                if (getPieceAtPosition(temp) != null && !temp.equals(b)) {
                    return false;
                }
            }
        }
        //
        if (a.getX() > b.getX()) {
            for (int i = a.getX() - 1; i >= b.getX(); i--) {
                Position temp = new Position(i, a.getY());
                if (getPieceAtPosition(temp) != null && !temp.equals(b)) {
                    return false;
                }
            }
        }
        //
        else if (a.getX() < b.getX()) {
            for (int i = a.getX() + 1; i <= b.getX(); i++) {
                Position temp = new Position(i, a.getY());
                if (getPieceAtPosition(temp) != null && !temp.equals(b)) {
                    return false;
                }
            }
        }

        Piece player1 = getPieceAtPosition(a);
        board[b.getY()][b.getX()] = (ConcretePiece) player1;
        if (currentPlayer.isPlayerOne()) {
            tempArr[b.getY()][b.getX()][board[b.getY()][b.getX()].tag - 1]++;
        } else {
            tempArr[b.getY()][b.getX()][board[b.getY()][b.getX()].tag + 12]++;
        }
        board[a.getY()][a.getX()] = null;
        ((ConcretePiece) currentPiece).stepsArr.add(b);

        if (player1 instanceof King) {
            kingPos = b;
        }


        eatPawn(b);
        gameFinish = eatKing(b);

        isFirstPlayerTurn = !isFirstPlayerTurn;

        getPieceAtPosition(b).setSquares(getPieceAtPosition(b).getSquares() + Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY()));

        isGameFinished();
        return true;
    }


    public void eatPawn(Position b) {
        if (getPieceAtPosition(b) == null || getPieceAtPosition(b) instanceof King) { // There is a pawn/king on the piece
            return;
        }
        // Check if the player can eat the other player with the corner
        Position cornerUpR = new Position(10, 0);
        Position cornerUpL = new Position(0, 0);
        Position cornerDownR = new Position(10, 10);
        Position cornerDownL = new Position(0, 10);


        // This is the line that the value of Y is 10
        if (b.getY() == 10) {
            if (b.getX() == 8) {
                if (getPieceAtPosition(new Position(b.getX() + 1, b.getY())) != null && getPieceAtPosition(new Position(b.getX() + 1, b.getY())) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX() + 1, b.getY())).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX() + 2, b.getY())).equals(cornerDownR)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() + 1] = null;
                    }
                }
            }
            if (b.getX() == 2) {
                if (getPieceAtPosition(new Position(b.getX() - 1, b.getY())) != null && getPieceAtPosition(new Position(b.getX() - 1, b.getY())) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX() - 1, b.getY())).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX() - 2, b.getY())).equals(cornerDownL)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() - 1] = null;
                    }
                }
            }
        }
        //   This is the line that the value of Y is 0
        if (b.getY() == 0) {
            if (b.getX() == 8) {
                if (getPieceAtPosition(new Position(b.getX() + 1, b.getY())) != null && getPieceAtPosition(new Position(b.getX() + 1, b.getY())) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX() + 1, b.getY())).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX() + 2, b.getY())).equals(cornerUpR)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() + 1] = null;
                    }
                }
            }
            if (b.getX() == 2) {
                if (getPieceAtPosition(new Position(b.getX() - 1, b.getY())) != null && getPieceAtPosition(new Position(b.getX() - 1, b.getY())) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX() - 1, b.getY())).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX() - 2, b.getY())).equals(cornerUpL)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() - 1] = null;
                    }
                }
            }
        }
        // This is the line that the X value is 10
        if (b.getX() == 10) {
            if (b.getY() == 8) {
                if (getPieceAtPosition(new Position(b.getX(), b.getY() + 1)) != null && getPieceAtPosition(new Position(b.getX(), b.getY() + 1)) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX(), b.getY() + 1)).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX(), b.getY() + 2)).equals(cornerDownR)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() + 1][b.getX()] = null;
                    }
                }
            }
            if (b.getY() == 2) {
                if (getPieceAtPosition(new Position(b.getX(), b.getY() - 1)) != null && getPieceAtPosition(new Position(b.getX(), b.getY() - 1)) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX(), b.getY() - 1)).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX(), b.getY() - 2)).equals(cornerUpR)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() - 1][b.getX()] = null;
                    }
                }
            }
        }
        // This is the line that the X value is 0
        if (b.getX() == 0) {
            if (b.getY() == 8) {
                if (getPieceAtPosition(new Position(b.getX(), b.getY() + 1)) != null && getPieceAtPosition(new Position(b.getX(), b.getY() + 1)) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX(), b.getY() + 1)).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX(), b.getY() + 2)).equals(cornerDownL)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() + 1][b.getX()] = null;
                    }
                }
            }
            if (b.getY() == 2) {
                if (getPieceAtPosition(new Position(b.getX(), b.getY() - 1)) != null && getPieceAtPosition(new Position(b.getX(), b.getY() - 1)) instanceof Pawn) {
                    if (getPieceAtPosition(new Position(b.getX(), b.getY() - 1)).getOwner() != getPieceAtPosition(b).getOwner() && (new Position(b.getX(), b.getY() - 2)).equals(cornerUpL)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() - 1][b.getX()] = null;
                    }
                }
            }
        }


        // Remove the down player
        if (b.getY() + 1 <= 10 && board[b.getY() + 1][b.getX()] != null) {
            if (getPieceAtPosition(b).getOwner() != board[b.getY() + 1][b.getX()].getOwner() && !(board[b.getY() + 1][b.getX()] instanceof King)) {
                if (b.getY() + 1 == 10) {
                    if (!(board[b.getY() + 1][b.getX()] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() + 1][b.getX()] = null;
                    }
                } else if (board[b.getY() + 2][b.getX()] != null && board[b.getY() + 2][b.getX()] instanceof Pawn)
                    if (getPieceAtPosition(b).getOwner() == board[b.getY() + 2][b.getX()].getOwner() && !(board[b.getY() + 1][b.getX()] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() + 1][b.getX()] = null;
                    }
            }
        }
        // Remove the right player
        if (b.getX() + 1 <= 10 && board[b.getY()][b.getX() + 1] != null) {
            if (getPieceAtPosition(b).getOwner() != board[b.getY()][b.getX() + 1].getOwner()) {
                if (b.getX() + 1 == 10) {
                    if (!(board[b.getY()][b.getX() + 1] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() + 1] = null;
                    }
                } else if (board[b.getY()][b.getX() + 2] != null && board[b.getY()][b.getX() + 2] instanceof Pawn) {
                    if (getPieceAtPosition(b).getOwner() == board[b.getY()][b.getX() + 2].getOwner() && !(board[b.getY()][b.getX() + 1] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() + 1] = null;
                    }
                }
            }

        }
        // Remove the up player
        if (b.getY() - 1 >= 0 && board[b.getY() - 1][b.getX()] != null) {
            if (getPieceAtPosition(b).getOwner() != board[b.getY() - 1][b.getX()].getOwner()) {
                if (b.getY() - 1 == 0) {
                    if (!(board[b.getY() - 1][b.getX()] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() - 1][b.getX()] = null;
                    }
                } else if (board[b.getY() - 2][b.getX()] != null && board[b.getY() - 2][b.getX()] instanceof Pawn) {
                    if (getPieceAtPosition(b).getOwner() == board[b.getY() - 2][b.getX()].getOwner() && !(board[b.getY() - 1][b.getX()] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY() - 1][b.getX()] = null;
                    }
                }
            }
        }
        // Remove the left player
        if (b.getX() - 1 >= 0 && board[b.getY()][b.getX() - 1] != null) {
            if (getPieceAtPosition(b).getOwner() != board[b.getY()][b.getX() - 1].getOwner()) {
                if (b.getX() - 1 == 0) {
                    if (!(board[b.getY()][b.getX() - 1] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() - 1] = null;
                    }
                } else if (board[b.getY()][b.getX() - 2] != null && board[b.getY()][b.getX() - 2] instanceof Pawn) {
                    if (getPieceAtPosition(b).getOwner() == board[b.getY()][b.getX() - 2].getOwner() && !(board[b.getY()][b.getX() - 1] instanceof King)) {
                        getPieceAtPosition(b).setKills();
                        board[b.getY()][b.getX() - 1] = null;
                    }
                }
            }
        }
    }


    public boolean eatKing(Position b) {
        if (getPieceAtPosition(b) == null) {
            return false;
        }

        Piece up = getPieceAtPosition(new Position(kingPos.getX(), kingPos.getY() - 1));
        Piece down = getPieceAtPosition(new Position(kingPos.getX(), kingPos.getY() + 1));
        Piece right = getPieceAtPosition(new Position(kingPos.getX() + 1, kingPos.getY()));
        Piece left = getPieceAtPosition(new Position(kingPos.getX() - 1, kingPos.getY()));

        if (kingPos.getX() == 10 && up != null && up.getOwner() == secondPlayer && left != null && left.getOwner() == secondPlayer && down != null && down.getOwner() == secondPlayer) {
            return true;

        } else if (kingPos.getX() == 0 && up != null && up.getOwner() == secondPlayer && right != null && right.getOwner() == secondPlayer && down != null && down.getOwner() == secondPlayer) {
            return true;

        } else if (kingPos.getY() == 10 && up != null && up.getOwner() == secondPlayer && left != null && left.getOwner() == secondPlayer && right != null && right.getOwner() == secondPlayer) {
            return true;
        } else if (kingPos.getY() == 0 && right != null && right.getOwner() == secondPlayer && left != null && left.getOwner() == secondPlayer && down != null && down.getOwner() == secondPlayer) {
            return true;
        } else if (right != null && right.getOwner() == secondPlayer && up != null && up.getOwner() == secondPlayer && left != null && left.getOwner() == secondPlayer && down != null && down.getOwner() == secondPlayer) {
            return true;
        }
        return false;
    }


    @Override
    public ConcretePiece getPieceAtPosition(Position position) {
        if (position.getY() < 0 || position.getY() >= 11 || position.getX() < 0 || position.getX() >= 11)
            return null;
        return board[position.getY()][position.getX()];
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public boolean kingInCorner() {
        if (getPieceAtPosition(kingPos) == (board[0][0]) || getPieceAtPosition(kingPos) == board[10][0] || getPieceAtPosition(kingPos) == board[10][10] || getPieceAtPosition(kingPos) == board[0][10]) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameFinished() {
        if (kingInCorner()) {
            blueWon = true;
            printUp();
            printKills();
            printSteps();
            printPieces();
            return true;
        }
        if (gameFinish) {
            printUp();
            printKills();
            printSteps();
            printPieces();
            return true;
        }
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !isFirstPlayerTurn;
    }

    @Override
    public void reset() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                board[i][j] = null;
            }
        }
        initialize();
    }

    @Override
    public void undoLastMove() {
// בונוס
    }

    @Override
    public int getBoardSize() {
        return 11;
    }
}
