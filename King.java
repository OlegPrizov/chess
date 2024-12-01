// Класс King, наследующий от ChessPiece
public class King extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public King(String color) {
        super(color); // Вызов конструктора родительского класса
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "K"; // Символ для короля
    }

    // Метод для проверки возможности движения
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель не совпадает с текущей позицией
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что перемещение находится в пределах шахматной доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, что король движется на одну клетку в любом направлении
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);
        if (deltaX > 1 || deltaY > 1) {
            return false; // Король может двигаться только на одну клетку
        }

        // Проверка, что целевая клетка не занята союзной фигурой
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false; // Нельзя двигаться на клетку, занятую своей фигурой
        }

        // Если все проверки пройдены, движение возможно
        return true;
    }

    // Метод для проверки, находится ли клетка под атакой
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.getColor()) && piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                    return true; // Если другая фигура может атаковать данную клетку
                }
            }
        }
        return false; // Клетка не под атакой
    }
}
