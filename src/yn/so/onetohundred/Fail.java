package yn.so.onetohundred;

public class Fail {

    public void thisFails(int x){
        System.out.println(x);
        Integer[] bigArray = new Integer[9450];
        thisFails(x+1);
    }

    public static void main(String[] args) {
        Fail failure = new Fail();
        failure.thisFails(1);
    }
}
