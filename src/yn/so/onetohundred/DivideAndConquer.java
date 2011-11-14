package yn.so.onetohundred;

public class DivideAndConquer {

    public static void main(String[] args) {
        Print64Numbers();
        Print32Numbers();
        Print4Numbers();
    }

    private static int currentNumber = 0;

    private static void Print1Number() { System.out.println(++currentNumber); }
    private static void Print2Numbers() { Print1Number(); Print1Number(); }
    private static void Print4Numbers() { Print2Numbers(); Print2Numbers(); }
    private static void Print8Numbers() { Print4Numbers(); Print4Numbers(); }
    private static void Print16Numbers() { Print8Numbers(); Print8Numbers(); }
    private static void Print32Numbers() { Print16Numbers(); Print16Numbers(); }
    private static void Print64Numbers() { Print32Numbers(); Print32Numbers(); }
}
