public class Pawn extends ChessPiece {
    private boolean hasMoved = false;

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public void markAsMoved() {
        this.hasMoved = true;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }

        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        int direction = getColor().equals("White") ? 1 : -1;

        if (toLine == line + direction && column == toColumn) {
            return chessBoard.board[toLine][toColumn] == null;
        }

        if (!hasMoved && toLine == line + 2 * direction && column == toColumn) {
            if (chessBoard.board[line + direction][column] == null && chessBoard.board[toLine][toColumn] == null) {
                markAsMoved();
                return true;
            }
        }

        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece != null && !targetPiece.getColor().equals(this.getColor())) {
                return true;
            }
        }

        return false;
    }
}
