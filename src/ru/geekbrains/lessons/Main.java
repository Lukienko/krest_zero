package ru.geekbrains.lessons;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String USER_SIGN = "X";
    public static final String USER_SIGN_SECOND = "O";
    public static final String AI_SIGN = "O";
    public static final String NOT_SIGN = "*";
    public static int aiLevel = 0;
    public static final int SIZE = 5;
    public static String[][] field = new String[SIZE][SIZE];

    public static void main(String[] args) {
        modeAgainstAI();
    }


    public static void modeAgainstAI() {
        int count = 0;
        initField();
        while (true) {
            printField();
            userShot(USER_SIGN, 0);
            count++;
            if (checkWin(USER_SIGN)) {
                System.out.println("Игрок победил! Поздравляю!");
                printField();
                break;
            }
            aiShot();
            count++;
            if (checkWin(AI_SIGN)) {
                System.out.println("Компьютер выиграл, попробуй еще раз!");
                printField();
                break;
            }
            if (count == Math.pow(SIZE, 2)) {
                printField();
                break;
            }
        }
    }
    public static void initField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = NOT_SIGN;
            }
        }
    }

    public static void printField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void userShot(String sign, int i) {
        int x = -1;
        int y = -1;
        do {
            if (i == 0) {
                System.out.println("Введите координаты x y (1 - " + SIZE + "): ");
            } else {
                System.out.println("Игрок " + i + ". Введите координаты x y (1 - " + SIZE+ "): ");
            }
            Scanner sc = new Scanner(System.in);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (isCellBusy(x, y));
        field[x][y] = sign;
    }

    public static void aiShot() {
        int x = -1;
        int y = -1;
        boolean ai_win = false;
        boolean user_win = false;
        // Находим выигрышный ход
        if (true) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (!isCellBusy(i, j)) {
                        field[i][j] = AI_SIGN;
                        if (checkWin(AI_SIGN)) {
                            x = i;
                            y = j;
                            ai_win = true;
                        }
                        field[i][j] = NOT_SIGN;
                    }
                }
            }
        }
        // Блокировка хода пользователя, если он побеждает на следующем ходу
        if (true) {
            if (!ai_win) {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (!isCellBusy(i, j)) {
                            field[i][j] = USER_SIGN;
                            if (checkWin(USER_SIGN)) {
                                x = i;
                                y = j;
                                user_win = true;
                            }
                            field[i][j] = NOT_SIGN;
                        }
                    }
                }
            }
        }
        if (!ai_win && !user_win) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(SIZE);
                y = rnd.nextInt(SIZE);
            }
            while (isCellBusy(x, y));
        }
        field[x][y] = AI_SIGN;
    }
    public static boolean isCellBusy(int x, int y) {
        if (x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1) {
            return false;
        }
        return field[x][y] != NOT_SIGN;
    }

    public static boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        for (int i = 0; i < SIZE; i++) {
            if (field[start_x + i * dx][start_y + i * dy] != sign)
                return false;
        }
        return true;
    }

    public static boolean checkWin(String symb) {
        for (int i = 0; i <SIZE; i++) {
            // проверяем строки
            if (checkLine(i, 0, 0, 1, symb)) return true;
            // проверяем столбцы
            if (checkLine(0, i, 1, 0, symb)) return true;
        }
        // проверяем диагонали
        if (checkLine(0, 0, 1, 1, symb)) return true;
        if (checkLine(0, SIZE - 1, 1, -1, symb)) return true;
        return false;
    }


    public static boolean checkWin2(String sign) {
        {
            for (int i = 0; i < SIZE; i++) {
                if (field[i][0] == sign && field[i][1] == sign && field[i][2] == sign) {
                    return true;
                }
            }
        }
        {
            for (int j = 0; j < SIZE; j++) {
                if (field[0][j] == sign && field[1][j] == sign && field[2][j] == sign) {
                    return true;
                }
            }
        }
        {
            if (field[0][0] == sign && field[1][1] == sign && field[2][2] == sign) {
                return true;
            }
            if (field[0][2] == sign && field[1][1] == sign && field[2][0] == sign) {
                return true;
            }
        }
        return false;
    }
}