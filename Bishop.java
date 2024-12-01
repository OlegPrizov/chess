public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "B";
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
        if (deltaX != deltaY) {
            return false;
        }
        int stepX = (toLine - line) > 0 ? 1 : -1;
        int stepY = (toColumn - column) > 0 ? 1 : -1;
        for (int i = 1; i < deltaX; i++) {
            int intermediateLine = line + i * stepX;
            int intermediateColumn = column + i * stepY;
            if (chessBoard.board[intermediateLine][intermediateColumn] != null) {
                return false;
            }
        }
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }
        return true;
    }
}


