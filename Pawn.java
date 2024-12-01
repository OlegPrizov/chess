// Класс Pawn, наследующий от ChessPiece
public class Pawn extends ChessPiece {
    // Хранит, была ли пешка уже перемещена
    private boolean hasMoved = false;

    // Конструктор, принимающий цвет фигуры
    public Pawn(String color) {
        super(color); // Вызов конструктора родительского класса
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "P"; // Символ для пешки
    }

    // Метод для отметки, что пешка сделала ход
    public void markAsMoved() {
        this.hasMoved = true; // Устанавливаем, что пешка сделала ход
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

        // Перемещение пешки вперед
        int direction = getColor().equals("White") ? 1 : -1; // Направление движения в зависимости от цвета

        // Проверяем обычное движение на 1 клетку вперед
        if (toLine == line + direction && column == toColumn) {
            // Проверяем, что целевая клетка пуста
            return chessBoard.board[toLine][toColumn] == null;
        }

        // Первый ход пешки может быть на 2 клетки вперед
        if (!hasMoved && toLine == line + 2 * direction && column == toColumn) {
            // Проверяем, что клетки на пути не заняты
            if (chessBoard.board[line + direction][column] == null && chessBoard.board[toLine][toColumn] == null) {
                markAsMoved(); // Отметим, что пешка сделала свой первый ход
                return true; // Пешка может переместиться на 2 клетки
            }
        }

        return false; // В остальных случаях возвращаем false
    }
}
