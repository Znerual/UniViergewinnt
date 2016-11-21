import java.util.TreeMap;

/*******************************************************************************

 AUFGABENBLATT 5 - Allgemeine Informationen

 Achten Sie bei der Implementierung auf folgende Punkte:

 - Ihr Programm sollte den dazugehörenden Test (z.B. enthält Aufgabe1Test den
 Test zu Aufgabe1) bestehen.

 - Bei jeder Aufgabe finden Sie Zusatzfragen. Diese Zusatzfragen beziehen sich
 thematisch auf das erstellte Programm.  Sie müssen diese Zusatzfragen in der
 Übung beantworten können.

 - Verwenden Sie bei allen Ausgaben immer System.out.println().

 - Verwenden Sie für die Lösung der Aufgaben keine speziellen Aufrufe aus der
 Java-API, die die Aufgaben verkürzen würden.

 Abgabe: Die Abgabe erfolgt in TUWEL. Bitte laden Sie Ihr IntelliJ-Projekt
 bis spätestens Montag 28.11.2016 08:00 Uhr in TUWEL hoch. Zusätzlich
 müssen Sie in TUWEL ankreuzen welche Aufgaben Sie gelöst haben und während
 der Übung präsentieren können.

 ******************************************************************************/
/*
    Aufgabe 1) Klasse, Objekte, Methoden, Map verwenden - Tagesablaufplan

    Die Bewohner der fernen Insel haben beschlossen, sich von der klassischen
    Uhrzeit zu verabschieden. Stattdessen führen sie eine neue Uhrzeit ein, bei
    der ein Tag in 8 Achtel, ein Achtel in 100 Zentiachtel und ein Zentiachtel
    in 100 Ticks geteilt wird.
    Auf der Insel unterliegt ein großer Teil des Tages einem fixen
    Ablauf, z.B. "Sonnenaufgang betrachten" von 1.95.0 bis 2.5.0,
    "Frühstück" von 2.15.0 bis 2.30.0, "Muschelsammeln" von 3.0.0 bis 3.50.0,
    "Sonnenhut tragen" von 2.0.0 bis 6.0.0 und so weiter.
    Ein elektronischer Plan des Tagesablaufs soll Touristen vorgeblich helfen,
    sich einfacher anzupassen. Tatsächlich machen sich die Einwohner einen Spaß
    daraus, Fremde beim Anpassungsversuch zu beobachten. Daher kann man im Plan
    nur durch Eingabe der genauen Tätigkeit (z.B. "Sonnenaufgang betrachten")
    den dazugehörigen Zeitraum finden. Für zusätzliche Verwirrung sorgen
    unterschiedliche Pläne für unterschiedliche Touristen.

    Objekte der Klasse Aufgabe1 sollen Tagesablaufpläne für Touristen
    darstellen. Folgende Methoden werden benötigt:

    - from: hat einen Parameter vom Typ String, der einer Tätigkeit entspricht,
            und gibt die Beginn-Zeit der Tätigkeit als String (enthält eine neue
            Zeit) zurück; das Ergebnis ist null falls die Tätigkeit nicht
            vorgesehen ist.

    - to:   hat einen Parameter vom Typ String, der einer Tätigkeit entspricht,
            und gibt die Ende-Zeit der Tätigkeit als String zurück; das Ergebnis
            ist null falls die Tätigkeit nicht vorgesehen ist.

    - add:  wird für den schrittweisen Aufbau des Tagesablaufplans benötigt; der
            erste Parameter vom Typ String gibt eine Tätigkeit an, der zweite
            Parameter vom Typ Period (siehe unten) den Zeitaum dieser Tätigkeit;
            als Ergebnis kommt false zurück wenn für diese Tätigkeit vor Aufruf
            von add noch kein Zeitraum vorgesehen war, true wenn schon ein
            Zeitraum vorgesehen war, der durch den Aufruf von add ersetzt wird.

    Es soll möglich sein, mehrere Objekte von Aufgabe1 zu erstellen. Die Anzahl
    der Tätigkeiten in einem Tagesablaufplan darf nicht beschränkt sein (außer
    durch die Größe des Computer-Speichers).

    Objekte der Klasse Period stellen einen Zeitraum innerhalb eines Tages dar
    und enthalten die Beginn- und die Ende-Zeit jeweils als Objekte vom Typ
    String. Erzeugt werden Objekte von Period nur über einen Konstruktor, und
    zugegriffen wird nur über die folgenden parameterlosen Methoden:

    - from: gibt die Beginn-Zeit zurück.
    - to:   gibt die Ende-Zeit zurück.

    Hinweis: Sie können den Tagesablaufplan als Map/TreeMap realisieren.

    Zusatzfragen:
    1. Wozu benötigt man in dieser Aufgabe eine Klasse wie Period?
    2. Wodurch unterscheidet sich ein Array von einer Map?
    4. Muss man in jeder Klasse einen Konstruktor schreiben? Wenn nein, welche
       Konsequenzen hat es, wenn man keinen Konstruktor schreibt?
*/
class Period {
    private String m_begin, m_end;
    public Period(String begin, String end) {
        m_begin = begin;
        m_end = end;

    }
    public String from() { return m_begin; }
    public String to() { return m_end; }
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Period)) return false;
        if (this.from().equals(((Period) other).from()) && this.to().equals(((Period) other).to())) return true;
        return false;
    }
    //TODO Implementieren Sie hier die Klasse "Period"
}

public class Aufgabe1 {
    private TreeMap<String, Period> aufgaben = new TreeMap<>();
    public boolean add(String taetigkeit, Period zeit) {
        if (aufgaben.containsKey(taetigkeit)) {
            aufgaben.put(taetigkeit, zeit);
            return true;
        }
        aufgaben.put(taetigkeit,zeit);
        return false;
    }
    public String from(String taetigkeit) {
        if (aufgaben.containsKey(taetigkeit)) {
            return aufgaben.get(taetigkeit).from();
        }
        return null;
    }
    public String to(String taetigkeit) {
        if (aufgaben.containsKey(taetigkeit)) {
            return aufgaben.get(taetigkeit).to();
        }
        return null;
    }
    public static void main(String[] args) {
        Aufgabe1 a1 = new Aufgabe1();
        System.out.println(a1.add("Bla", new Period("1","1")));
        System.out.println(a1.add("daf", new Period("1","1")));
    }
}


