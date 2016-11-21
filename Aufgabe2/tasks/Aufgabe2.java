import java.util.Deque;
import java.util.LinkedList;

/*
    Aufgabe 2) Deque - Klammerung

    Implementieren Sie in Aufgabe2 eine statische Methode check, die einen
    String als Parameter hat und genau dann true zurückgibt, wenn der String
    einen korrekt geklammerten Ausdruck enthält. Ein Ausdruck ist dann korrekt
    geklammert, wenn es zu jeder öffnenden Klammer '(', '[' oder '{' eine
    entsprechende schließende Klammer ')', ']' oder '}' gibt. Zwischen diesen
    Zeichen können beliebige andere Zeichen vorkommen.

    Beispiele für korrekt geklammerte Ausdrücke:
    "", "a*[a+12]", "a+(b)-c", "a+{b+8+(b+c)}/a"
    Beispiele für nicht korrekt geklammerte Ausdrücke:
    "[", "(}", "a)[]", "([)]", "]["

    Verwenden Sie einen Stack zur Überprüfung der Klammerung: Durchlaufen Sie
    die Zeichen im String von vorne nach hinten und legen Sie jede öffnende
    Klammer, die Sie dabei finden, auf den Stack. Wenn Sie auf eine schließende
    Klammer stoßen, nehmen Sie das oberste Element vom Stack; bei korrekter
    Klammerung muss die schließende Klammer mit der Klammer vom Stack
    zusammenpassen. Andere Zeichen werden einfach ignoriert. Bei korrekter
    Klammerung muss der Stack am Ende leer sein.

    Hinweis: Sie können als Stack z.B. ein Objekt des Typs Deque<String>
    verwenden.

    Zusatzfragen:
    1. Was unterscheidet Queue von Map?
    1. Was ist der Unteschied zwischen LIFO und FIFO Prinzip?
    2. Wie könnte man diese Aufgabe auch mit einem Array statt einem Stack
       lösen? Welche Nachteile würden sich daraus ergeben?
*/
public class Aufgabe2{

    public static boolean check(String term) {
        Deque<String> stack = new LinkedList<>();
        for (int i = 0; i < term.length(); i++) {
            switch (term.charAt(i)) {
                case '(':
                    stack.addFirst(")");
                    break;
                case '[':
                    stack.addFirst("]");
                    break;
                case '{': stack.addFirst("}");
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.size() == 0) return false;
                    String s = term.substring(i, i +1);
                    if (stack.peekFirst().equals(s)) {
                        stack.removeFirst();
                    }
                    break;
            }
        }
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }
    //TODO Implementieren Sie hier die Methode "check"
    
    public static void main(String[] args) {

    }
}

