/*
    Aufgabe 3, 4, 5) Zweidimensionale Arrays -- Vier Gewinnt

    Fortsetzung des "Vier gewinnt" Spiels aus Aufgabenblatt 4. Kopieren Sie
    sich dazu die Methoden spielfeld(), spielstand(), zug(), und sieg() aus
    Aufgabenblatt 4, um die weiteren Funktionalitäten in diesem Aufgabenblatt
    zu lösen.

    Auch auf diese Aufgaben werden spätere Aufgabenblätter aufbauen, Sie sollten
    sie daher unbedingt lösen. In diesem Aufgabenblatt deckt das Spiel
    "Vier gewinnt" 3 Aufgaben ab. Bitte kreuzen Sie diese separat in TUWEL an.
    
    Hinweis: Sie können Hilfsmethoden implementieren.
    
    *****************************  Aufgabe 3  **********************************
    Für Aufgabe 3 schreiben Sie folgende statische Methoden:

    1) public static int wert1(int[][] f, int spieler)
    
    Diese Methode nimmt eine naive* Stellungsbewertung der Position von Spieler
    "spieler" vor: Es zählt die Zweier-Reihen, Dreier-Reihen, und Vierer-Reihen
    aus Steinen des Spielers "spieler". Der zurückgegebene Wert ist
    1*z+100*d+10000*v, wobei z die Zahl der Zweier-Reihen, d die Zahl der
    Dreier-Reihen, und v die Zahl der Vierer-Reihen ist.
    
    *) Eine einigermassen gute Stellungsbewertung würde den Rahmen der
    Übung sprengen.
    
    Für die Stellung
    
    |       |
    |       |
    |       |
    |  o    |
    |  xo   |
    |  oxoxx|
    +-------+
    
    ist die Bewertung für Spieler 1 (x) 1*2=2 und fuer Spieler 2 (o)
    1*3+100*1=103.  Eine Dreier-Reihe zählt also auch noch als
    zwei Zweier-Reihen.

    2) public static int wert(int[][] f, int spieler)
    
    Die Methode bezieht den wert1() des Gegners in die Bewertung mit ein: Vier
    gewinnt ist (wie die meisten Brettspiele) ein Null-Summen-Spiel
    (Spieler 1 gewinnt, wenn Spieler 2 verliert, und umgekehrt), daher
    soll wert() die Differenz von wert1() des Spielers und wert1() des
    Gegners zurückgeben, im obigen Beispiel also -101 für Spieler 1 (oder
    101 für Spieler 2).
    ****************************************************************************
    
    *****************************  Aufgabe 4  **********************************
    Für Aufgabe 4 schreiben Sie folgende statische Methoden:

    1) public static int negamax(int[][] f, int spieler, int tiefe)
    
    Eine bessere Stellungsbewertung kann man aus wert() ableiten, indem
    man einige Halbzüge vorausschaut. Bei einem Halbzug Vorausschau
    bewertet man die Stellung, die sich bei jedem der 7 möglichen Züge
    ergibt, wie folgt: Der Spieler, der am Zug ist, wird den für ihn
    besten Zug auswählen, der Wert der ursprünglichen Stellung ist also
    das Maximum der Werte der sieben möglichen Folgestellungen. Wenn man
    das für mehrere Halbzüge verallgemeinert, muss man nach jedem Halbzug
    die Seite wechseln, und für den Spieler, der dann am Zug ist, das
    Maximum berechnen. Um diesen Wert dann als Bewertung fuer den anderen
    (vorherigen) Spieler zu verwenden, muß man ihn negieren. Dieser
    Algorithmus heißt "Negamax".

    Diese rekursive Methode "negamax" führt eine Stellungsbewertung für Spieler
    "spieler" mit "tiefe" Zügen Vorausschau durch. Bei 0 Zügen Vorausschau soll
    die Bewertung wert() verwendet werden.
    
    Für diese Methode benötigen Sie eine Möglichkeit, Züge zu probieren,
    ohne sich darauf festzulegen, entweder indem Sie den alten Wert von f
    erhalten, oder indem Sie nach dem Probieren den Zug wieder
    zurücknehmen.  Wenn nötig, modifizieren Sie existierende Methoden
    und/oder implementieren Sie Hilfsmethoden, um das zu erreichen.

    2) public static int bester(int[][] f, int spieler, int tiefe)

    Diese Methode wählt den besten Zug aus. Alle 7 möglichen Züge
    werden durchprobiert, und die sich dadurch ergebende Stellung f1 wird mit
    Hilfe von negamax() bewertet (beachten Sie, welcher Spieler am Zug
    ist). Der Rückgabewert ist ein Zug mit maximaler Bewertung.
    
    Zusatzfragen:
    1. Was sind die Vor- und Nachteile der von Ihnen gewählten
       Art, nach dem Probieren wieder zum vorherigen Zug zu kommen?
    2. Ermitteln Sie durch Ausprobieren und ungefähre Zeitmessung, wie der
       Zeitaufwand von bester() mit der Tiefe zusammenhängt.
    3. Wieviele Aufrufe von wert() werden höchstens ausgeführt, wenn man
       bester() mit Tiefe 0, 1, 2, 8 und n aufruft?
    ****************************************************************************

    *****************************  Aufgabe 5  **********************************
    Für Aufgabe 5 schreiben Sie folgende statische Methode:

    1) public static void spiel1(int tiefe)

    Diese Methode führt ein Vier-Gewinnt-Spiel Spieler gegen Computer durch:
    Zunächst sucht sich der Spieler aus, ob er beginnt oder der Computer. Wenn
    der Computer am Zug ist ist, wählt er den nächsten Zug mit bester(...,
    tiefe) aus und führt ihn durch.  Abgesehen davon macht spiel1() das
    gleiche wie spiel(). Probieren Sie verschiedene Werte für tiefe aus,
    und wählen Sie einen, bei dem der Computer im Normalfall zwischen 0.1s
    und 1s zur Auswahl des besten Zugs braucht. Testen Sie die Methode,
    indem Sie gegen den Computer spielen.
    ****************************************************************************
*/

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
public class Aufgabe3 {
    public static final int r = 6;
   public static final int s = 7;
    private static final int victoryLimit = 4;
    private static boolean unerlaubterZug = false;
    private static final int[] VALUE = {0,0,1,100,10000};

    //***************************  Aufgabe 3  **********************************
    public static int wert1(int[][] f, int spieler){
        Points punkte = new Points();
        testAll(f, spieler, punkte);

        return punkte.getValue(); //diese Anweisung ändern oder löschen.
    }
    
    public static int wert(int[][] f, int spieler){
        if (spieler == 1) {
            return wert1(f, spieler) - wert1(f, 2);
        } else {
            return wert1(f, spieler) - wert1(f, 1);
        }


    }
    //**************************************************************************
    
    
    //***************************  Aufgabe 4  **********************************
    private static Move besterZug(int[][] f, int spieler) {
        Move best = new Move();
        int ranking = 0;
        boolean firstRound = true;
        for (int i = 0; i < 6; i++) {
            int [][] fictionalMove = deepCopy(f);
            zug(fictionalMove, spieler, i);
            ranking = wert(fictionalMove,spieler);
            if (ranking > best.getRank() || firstRound == true) {
                best.setMove(i);
                best.setRank(ranking);
                firstRound = false;
            }
        }
        return best; //di
    }
    public static int negamax(int[][] f, int spieler, int tiefe) {
        if (tiefe == 0) {
            return wert(f, spieler);
        }
        int bestValue = -199999;
        for (int i = 0; i < s; i++) {
            int[][] nodeLine = deepCopy(f);
            zug(nodeLine, spieler, i);
           // spielstand(nodeLine);
            int value =  -negamax(nodeLine,toggleSpieler(spieler), tiefe -1);
           // System.out.println("Negmax Value: " + value + " für Spieler " + spieler);
            bestValue = Math.max(bestValue, value);
           // System.out.println("Best Value: " + bestValue);
        }
        return bestValue;




    }

    private static int[][] deepCopy(int[][] array) {
        int[][] copy = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            copy[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return copy;
    }
    
    public static int bester(int[][] f, int spieler, int tiefe){
        Move optimal = new Move();
        for (int i = 0; i < s; i++) {
            int[][] f1 = deepCopy(f);
            zug(f1,spieler,i);
            //spielstand(f1);
            int value = negamax(f1, spieler, tiefe);
            //System.out.println("Zug negmax wert: " + value);
            if (!optimal.isInitialised()) {
                optimal.setRank(value);
            } else if (value >= optimal.getRank()){
                optimal.setRank(value);
                optimal.setMove(i);
            }
            //System.out.println("Bester Zug: " + optimal.getMove());
        }
        return optimal.getMove();
    }
    //**************************************************************************
    
    
    //***************************  Aufgabe 5  **********************************
    public static void spiel1(int tiefe){
        int[][] ai_spielfeld = spielfeld();
        Visual optisch = new Visual(ai_spielfeld);
        boolean gameOver = false;
        int computer = 1;
        int currentPlayer = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Möchtest du beginnen? [y/n]");
        if (scanner.next().equals("y")) {
            computer = 2;
        }
        do {
            spielstand(ai_spielfeld);
                if (currentPlayer == computer) {
                    int findMove = bester(ai_spielfeld, currentPlayer, tiefe);
                    zug(ai_spielfeld,currentPlayer, findMove);

                } else {
                    do {
                        int spalte = getInputSpalte(scanner, currentPlayer);
                        zug(ai_spielfeld, currentPlayer, spalte);
                        if (unerlaubterZug) System.out.println("Hier kannst du keinen Stein platzieren!");
                    } while (unerlaubterZug);
                }
            optisch.move(ai_spielfeld);
            gameOver = (gameFull(ai_spielfeld) || sieg(ai_spielfeld, currentPlayer));
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        } while(!gameOver);
        System.out.println("Das spiel ist vorbei!");
    }
//**************************************************************************
    
    public static void main(String[] args) {
        spiel1(5);
    }
    public static int[][] spielfeld(){
        int ia_spielfeld[][] = new int[r][s];

        return ia_spielfeld; //diese Anweisung ändern oder löschen.
    }
    private static int toggleSpieler(int spieler) {
        switch (spieler) {
            case 1:
                return 2;
            case 2:
                return 1;
            default:
                return -1;
        }
    }
    public static void spielstand(int[][] f){
        for (int i = r -1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < s; j++) {
                switch (f[i][j]) {
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("x");
                        break;
                    case 2:
                        System.out.print("o");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.print("+");
        for (int k = 0; k < s ; k++) {
            System.out.print("-");
        }
        System.out.println("+");

    }
    //**************************************************************************


    //***************************  Aufgabe 4  **********************************
    public static int[][] zug(int[][] f, int spieler, int spalte){
        if (spalte < 0 || spalte > 6) return null;
        for (int i = 0 ; i < r; i++) {
            if (f[i][spalte] == 0) {
                f[i][spalte] = spieler;
                unerlaubterZug = false;
                return f;
            }
        }
        unerlaubterZug = true;
        return null; //diese Anweisung ändern oder löschen.
    }
    private static boolean testStraight(int[][] f, int spieler, int loop1, int loop2, boolean inverse) {
        for (int i = 0;i <loop1; i++ ) {
            int count = 0;
            for (int j = 0; j < loop2; j++) {
                int x = i,y = j;
                if (!inverse) {
                    x = j;
                    y = i;
                }
                if (f[x][y] == spieler) {
                    if (++count > victoryLimit -1) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return  false;
    }
    private static boolean testDiagonal(int [][] f, int spieler, int loop1Start, int loop1End,  boolean leftToRight) {
        for (int i = loop1Start; i < loop1End; i++ ){
            for (int j = 0; j < s-victoryLimit+1; j++) {
                int count = 0;
                for (int k = 0; k < victoryLimit; k++) {
                    int x = k, y = k;
                    if (!leftToRight) {
                        x = -k;
                    }
                    if (f[i + x][j + y] == spieler) {
                        if (++count > victoryLimit - 1) {
                            return true;
                        }
                    }
                }

            }
        }//Diagonal 1: l1s = 0, l2s = 0, l1e = r - vL, l2e = s - vL, lT = true
        return  false;
    }
    private static void testAll(int [][] f, int spieler, Points punkte) {
       // spielstand(f);
        // /
        testDiagonal(f, spieler, true, punkte);
        //System.out.println(punkte.getValue());
        // \
        testDiagonal(f,spieler,false, punkte);
       // System.out.println(punkte.getValue());
        //Horizontal
        testStraight(f,spieler,r,s, true, punkte);
       // System.out.println(punkte.getValue());
        //Vertikal
        testStraight(f,spieler,s,r, false, punkte);
        //System.out.println(punkte.getValue());
    }
    private static void testDiagonal(int [][] f, int spieler,  boolean leftToRight, Points punkte) {
        for (int i = 0; i < r; i++ ){
            for (int j = 0; j < s; j++) {
                int count = 0;
                for (int k = 0; k < victoryLimit; k++) {
                    int x = k, y = k;
                    if (!leftToRight) {
                        x = -k;
                    }
                   //System.out.println(i + "/" + j + "/" + k + " - " + x + "  :  " + count);
                    if (j + y < s && i + x < r && x + i >= 0) {
                        if (f[i + x][j + y] == spieler) {
                            ++count;
                            punkte.add(VALUE[count]);
                           // if (count >= 0) System.out.println(i + "/" + j + "/" + k + " - " + x + "  :  " + count);
                        }  else {
                            break;
                        }
                    } else {
                       // System.out.println("B " + i + "/" + j + "/" + k + " - " + x + "  :  " + count);
                        break;
                    }

                }
            }
        }
    }
    private static void testStraight(int[][] f, int spieler, int loop1, int loop2, boolean inverse, Points punkte) {
        for (int i = 0;i <loop1; i++ ) {
            int count = 0;
            for (int j = 0; j < loop2; j++) {
                int x = i,y = j;
                if (!inverse) {
                    x = j;
                    y = i;
                }
                if (f[x][y] == spieler) {
                    if (++count > victoryLimit -1) {
                        punkte.add(VALUE[count]);
                        count = 0;
                    }
                } else {
                    punkte.add(VALUE[count]);
                    count = 0;
                }
            }
            punkte.add(VALUE[count]);
        }
    }
    public static boolean sieg(int[][] f, int spieler) {

        //Horizontal
        return testStraight(f,spieler,r,s, true) || testStraight(f,spieler,s,r, false) || testDiagonal(f, spieler,0,r-victoryLimit, true) || testDiagonal(f,spieler,victoryLimit -1, r,false);

    }
    public static boolean gameFull(int[][] f) {
        for (int i = 0; i < s; i++) {
            if (f[0][i] == 0) {
                return false;
            }
        }
        return true;
    }
    //**************************************************************************
    private static int getInputSpalte(final Scanner scanner, int spieler) {
        System.out.printf("Sie sind an der Reihe, Spieler %d%n", spieler);
        System.out.printf("In welche Reihe möchten Sie Ihren Stein werfen? [1-7]%n");
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                if (selection > 0 && selection < 8) {
                    return selection -1;
                } else {
                    System.out.printf("Upps, du hast dich wohl vertippt. Die Zahl %d passt nicht ins Feld ;)%n", selection);
                }
            } else {
                System.out.printf("Hm, das verstehe ich leider nicht. Ich benötige bitte eine Zahl und nicht %s%n", scanner.next());
            }
        }
        return 0;
    }

    //***************************  Aufgabe 5  **********************************

}
class Points {
    public Points() {
    }

    public int getValue() {
        return m_value;
    }

    public void setValue(int value) {
        this.m_value = value;
    }
    public void add(int punkte) {
        m_value += punkte;
    }
    private int m_value = 0;
}
class Move {
    int rank;
    int move;
    boolean initialised;

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
        initialised = true;
    }

    public void setRank(int rank) {
        this.rank = rank;
        initialised = true;
    }



    public int getRank() {
        return rank;
    }

    public Move() {
        this.move = 0;
        this.rank = 0;
    }

    public Move(int move, int rank) {
        this.move = move;
        this.rank = rank;
        initialised = true;
    }
    public boolean isInitialised() { return initialised;}
}
class Visual extends JFrame {
    public static final int spaltenBreite = 20;
    public static final int zeilenHoehe = 20;
    private Field panel;
    public Visual(int[][] startGrid) {
        this.setTitle("Spielfeld");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(spaltenBreite * Aufgabe3.s + 2 * Field.offsetX + 50, zeilenHoehe * Aufgabe3.r + + 2 * Field.offsetY + 50);
        panel = new Field(startGrid);
        this.add(panel);
        this.setVisible(true);
    }
    public void move(int[][] f) {
        panel.makeMove(f);
    }

}
class Field extends JPanel {
    public static final int offsetX = 20;
   public static final int offsetY = 20;
    private int[][] spielfeld;
    public void makeMove(int[][] newField) {
        spielfeld = newField;
        this.repaint();
    }
    public Field(int[][] startGrid) {
        spielfeld = startGrid;
    }
    public void paintComponent(Graphics g) {
        for (int i = 0; i < Aufgabe3.s + 1; i++) {
            g.drawLine(offsetX + (i * Visual.spaltenBreite), offsetY,offsetX + (i * Visual.spaltenBreite), offsetY + (Aufgabe3.r + 1) * Visual.zeilenHoehe);
        }
        for (int i = 0; i < Aufgabe3.r + 1; i++) {
            g.drawLine(offsetX , offsetY  + ((i+1) * Visual.zeilenHoehe),offsetX + (Aufgabe3.s) * Visual.zeilenHoehe, offsetY  + ((i+1) * Visual.zeilenHoehe));
        }
        for (int i = Aufgabe3.r - 1; i >= 0;i--) {
            for (int j = 0; j < Aufgabe3.s; j++) {
                switch (spielfeld[i][j]) {
                    case 1:
                        g.setColor(new Color(255,20,20));
                        g.fillOval(offsetX + (j * Visual.spaltenBreite), offsetY + ( (Aufgabe3.r - i) * Visual.zeilenHoehe), Visual.spaltenBreite, Visual.zeilenHoehe );
                        break;
                    case 2:
                        g.setColor(new Color(20,255, 20));
                        g.fillOval(offsetX + (j * Visual.spaltenBreite), offsetY + ((Aufgabe3.r - i) * Visual.zeilenHoehe), Visual.spaltenBreite, Visual.zeilenHoehe );
                        break;
                }

            }
        }
    }
}
