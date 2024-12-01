// Класс Bishop, наследующий от ChessPiece
public class Bishop extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Bishop(String color) {
        super(color); // Вызов конструктора родительского класса
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "B"; // Символ для слона
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

        // Проверка, что слон перемещается по диагонали
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);
        if (deltaX != deltaY) {
            return false; // Слон может двигаться только по диагонали
        }

        // Проверка на наличие других фигур на пути
        int stepX = (toLine - line) > 0 ? 1 : -1; // Определяем направление по оси X
        int stepY = (toColumn - column) > 0 ? 1 : -1; // Определяем направление по оси Y

        // Проходим по всем клеткам на пути слона
        for (int i = 1; i < deltaX; i++) {
            int intermediateLine = line + i * stepX;
            int intermediateColumn = column + i * stepY;

            // Проверяем, занята ли клетка
            if (chessBoard.board[intermediateLine][intermediateColumn] != null) {
                return false; // Если есть фигура на пути, движение невозможно
            }
        }

        // Проверка, что целевая клетка не занята союзной фигурой
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false; // Нельзя двигаться на клетку, занятую своей фигурой
        }

        // Если все проверки пройдены, движение возможно
        return true;
    }
}


