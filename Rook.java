// Класс Rook, наследующий от ChessPiece
public class Rook extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Rook(String color) {
        super(color); // Вызов конструктора родительского класса
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "R"; // Символ для ладьи
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

        // Проверка, что ладья перемещается по прямой (либо по вертикали, либо по горизонтали)
        if (line != toLine && column != toColumn) {
            return false; // Ладья может двигаться только по одной линии
        }

        // Проверка на наличие других фигур на пути
        if (line == toLine) { // Горизонтальное движение
            int step = (toColumn - column) > 0 ? 1 : -1; // Определяем направление по оси Y
            for (int j = column + step; j != toColumn; j += step) {
                if (chessBoard.board[line][j] != null) {
                    return false; // Если есть фигура на пути, движение невозможно
                }
            }
            // Дополнительно проверяем, не занята ли целевая клетка союзной фигурой
            ChessPiece targetPiece = chessBoard.board[line][toColumn];
            if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
                return false; // Нельзя двигаться на клетку, занятую своей фигурой
            }
        } else { // Вертикальное движение
            int step = (toLine - line) > 0 ? 1 : -1; // Определяем направление по оси X
            for (int i = line + step; i != toLine; i += step) {
                if (chessBoard.board[i][column] != null) {
                    return false; // Если есть фигура на пути, движение невозможно
                }
            }
        }
        // Дополнительно проверяем, не занята ли целевая клетка союзной фигурой
        ChessPiece targetPiece = chessBoard.board[toLine][column];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false; // Нельзя двигаться на клетку, занятую своей фигурой
        }
        // Если все проверки пройдены, движение возможно
        return true;
    }
}
