import java.util. * ;

public class Practice4 {
    public static boolean hasfirstTurn = false;
    public static char[][] map;
    public static int size;
    public static int dot_to_win;
    public static final char symbol_X = 'X';
    public static final char symbol_O = 'O';
    public static final char symbol_DOT = '*';


    public static void main(String args[]) {
        boolean  gameOver = false;
        int newGame;
        gameField();
        printMap();
        do {  gamerTurn();
            if (!whoWin()) {
                machineTurn();
                printMap(); }
            else {
                System.out.println("Сыграть еще? (1-да, 0-нет)");
                Scanner input = new Scanner(System.in);
                newGame = input.nextInt();
                if (newGame == 1) {gameOver =false; gameField(); printMap();}
                if (newGame == 0) {gameOver =true;}
            }

        } while (!gameOver);
    }
    public static void gameField() {
        boolean correct =false;
        System.out.println("Здравсвуйте, введите пожалуйста размер игрового поля");
        do {
            Scanner input = new Scanner(System.in);
            size = input.nextInt();
            if (size > 2) {
                map = new char[size + 1][size + 1];
                System.out.println("Получишееся игровое поле:");
                for (int i = 1; i < map.length; i++) {
                    for (int j = 1; j < map.length; j++) {
                        map[i][j] = symbol_DOT;
                    }
                }
                correct = true; }
            else { System.out.println("Должно быть больше 2");}
        } while (!correct);
    }
    public static void printMap() {

        for (int k = 0; k < map.length; k ++){
            map[k][0] = (char) (k + '0');
            for (int y = 0; y < map.length; y++){
                map[0][y] = (char) (y + '0');
                System.out.print(map[k][y] + " ");
            }
            System.out.println();
        }
    }

    public static void gamerTurn(){
        int x = 0;
        int y = 0;
        boolean hasCorrect = false;
        boolean hasChisloX = false;
        boolean hasChisloY = false;
        System.out.println("Введите коардинаты 'x' и 'y'");
        do {
            Scanner input = new Scanner(System.in);
            if (input.hasNextInt()) {y = input.nextInt(); hasChisloX = true; input.nextLine();}
            if (input.hasNextInt() && hasChisloX) { x = input.nextInt(); hasChisloY = true;}
            if (x > 0 && y > 0 && x <= size && y <= size && hasChisloY) {
                map[x][y] = symbol_X; hasCorrect = true;
            }
            else {
                hasCorrect = false; hasChisloX = false; hasChisloY = false;
                System.out.println("Вы не корректно ввели коардинаты, повторите попытку:");
            }
        } while (!hasCorrect);
    }

    public static void machineTurn() {
        Random random = new Random();
        int x; int y;
        int a = 0;
        int b = 0;
        boolean hasCorrect = false;
        boolean hasStop = false;
        do {
            if (!hasfirstTurn){
                x = 1 + random.nextInt(size-1);
                y = 1 + random.nextInt(size-1);
                if (map[y][x] == symbol_DOT) { map[y][x] = symbol_O; hasCorrect = true;
                    System.out.println("1Противник сходил в точку " + x + " " + y); // hasfirstTurn = true;
                } }
       /*     if (hasfirstTurn) {
                for (int i = 1; i < map.length; i++) {
                    for (int j = 1; j < map.length; j++) {
                        if (map[i][j] == symbol_X) {  // совпадение 1
                            for (int k = i - 1; k < (i + 1); k++) {
                                for (int l = j - 1; l < (j + 1); l++) {
                                    if (map[i][j] == map[k][l] && i + j != k + l) { // совпадение 2
                                        a = k - i;
                                        b = l - j;
                                        if (map[k + a][l + b] == symbol_DOT && !hasStop) {
                                            map[k + a][l + b] = symbol_O;
                                            hasCorrect = true; hasStop =true;
                                            System.out.println("2Противник сходил в точку " );}
                                        if (map[k - a - a][l - b - b] == symbol_DOT && !hasStop) {
                                            map[k - a - a][l - b - b] = symbol_O;
                                            hasCorrect = true; hasStop =true;
                                            System.out.println("3Противник сходил в точку ");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }



*/


        } while (!hasCorrect);

    }
    public static boolean hasWin(char symd){
        int counter = 0;
        int a = 0;
        int b = 0;
        for (int i = 1; i < map.length; i ++){
            for (int j = 1; j < map.length; j++) {
                if (map[i][j] == symd) {  // совпадение 1
                    for (int k = i - 1; k < (i+1); k++) {
                        for (int l = j - 1; l < (j+1); l++){
                            if (map[i][j] == map[k][l] && i+j != k+l){ // совпадение 2
                                a=k-i; b=l-j;
                                if (map[i][j] == map[k+a][l+b]) {  if (size <= 3) {return (true);}// совпадение 3
                                    if (map[i][j] == map[k+(a*2)][l+(b*2)]) {; return (true);}} // совпадение 4
                            }
                        }
                    }
                }
            }
        }
        return (false);
    }

    public static boolean whoWin () {
        boolean  hasWinnerGamer = false;
        boolean  hasWinnerMachine = false;
        boolean  hasDrawGame = false;
        if (hasWin(symbol_X)) {System.out.println("Игрок победил"); return (true);}
        if (hasWin(symbol_O)) {System.out.println("ИИ победил"); return (true);}
        for (int i=0; i < map.length; i ++){
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == symbol_DOT) { hasDrawGame = true;}
            }
        }
        if ((hasWin(symbol_X) && hasWin(symbol_O)) || !hasDrawGame) {System.out.println("Ничья"); return (true);}
        else {return (false);}
    }



}
