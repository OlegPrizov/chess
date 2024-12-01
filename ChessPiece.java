public abstract class ChessPiece {
    public String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    abstract public boolean canMoveToPosition(
            ChessBoard chessBoard,
            int line,
            int column,
            int toLine,
            int toColumn);

    public abstract String getSymbol();
}
