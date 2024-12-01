public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }

        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if (line != toLine && column != toColumn && deltaX != deltaY) {
            return false;
        }

        if (line == toLine) {
            int step = (toColumn - column) > 0 ? 1 : -1;
            for (int j = column + step; j != toColumn; j += step) {
                if (chessBoard.board[line][j] != null) {
                    return false;
                }
            }
        } else if (column == toColumn) {
            int step = (toLine - line) > 0 ? 1 : -1;
            for (int i = line + step; i != toLine; i += step) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }
        } else {
            int stepX = (toLine - line) > 0 ? 1 : -1;
            int stepY = (toColumn - column) > 0 ? 1 : -1;
            for (int i = 1; i < deltaX; i++) {
                int intermediateLine = line + i * stepX;
                int intermediateColumn = column + i * stepY;
                if (chessBoard.board[intermediateLine][intermediateColumn] != null) {
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
