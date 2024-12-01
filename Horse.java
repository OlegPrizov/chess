public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что координаты не выходят за пределы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверка, что конечная позиция не совпадает с текущей
        if (line == toLine && column == toColumn) {
            return false; // Нельзя оставаться на месте
        }

        // Проверка, что ход коня соответствует правилам
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        // Конь может двигаться "буквой Г"
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            // Проверка, чтобы не занять клетку с союзной фигурой
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
                return false; // Нельзя двигаться на клетку, занятую своей фигурой
            }
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H"; // Символ для коня
    }

}
