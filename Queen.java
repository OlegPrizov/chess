// Класс Queen, наследующий от ChessPiece
public class Queen extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Queen(String color) {
        super(color); // Вызов конструктора родительского класса
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "Q"; // Символ для ферзя
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

        // Проверка, что ферзь перемещается по прямой или по диагонали
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        if (line != toLine && column != toColumn && deltaX != deltaY) {
            return false; // Ферзь может двигаться только по прямой или по диагонали
        }

        // Проверка на наличие других фигур на пути
        if (line == toLine) { // Горизонтальное движение
            int step = (toColumn - column) > 0 ? 1 : -1; // Определяем направление по оси Y
            for (int j = column + step; j != toColumn; j += step) {
                if (chessBoard.board[line][j] != null) {
                    return false; // Если есть фигура на пути, движение невозможно
                }
            }
        } else if (column == toColumn) { // Вертикальное движение
            int step = (toLine - line) > 0 ? 1 : -1; // Определяем направление по оси X
            for (int i = line + step; i != toLine; i += step) {
                if (chessBoard.board[i][column] != null) {
                    return false; // Если есть фигура на пути, движение невозможно
                }
            }
        } else { // Диагональное движение
            int stepX = (toLine - line) > 0 ? 1 : -1; // Определяем направление по оси X
            int stepY = (toColumn - column) > 0 ? 1 : -1; // Определяем направление по оси Y
            for (int i = 1; i < deltaX; i++) {
                int intermediateLine = line + i * stepX;
                int intermediateColumn = column + i * stepY;
                if (chessBoard.board[intermediateLine][intermediateColumn] != null) {
                    return false; // Если есть фигура на пути, движение невозможно
                }
            }
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false; // Нельзя двигаться на клетку, занятую своей фигурой
        }

        // Если все проверки пройдены, движение возможно
        return true;
    }
}
