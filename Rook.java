public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }

        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        if (line != toLine && column != toColumn) {
            return false;
        }

        if (line == toLine) {
            int step = (toColumn - column) > 0 ? 1 : -1;
            for (int j = column + step; j != toColumn; j += step) {
                if (chessBoard.board[line][j] != null) {
                    return false;
                }
            }
        } else {
            int step = (toLine - line) > 0 ? 1 : -1;
            for (int i = line + step; i != toLine; i += step) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }
        }
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }
        return true;
    }
}
