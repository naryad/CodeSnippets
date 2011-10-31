package yn.core;

public final class Singleton {

    private static Singleton instance;

    // we create a holder that lazy loads the class
    private static class Holder {
        static {
            System.out.println("instance created");
            instance = new Singleton();
        }
        
        /*
        public static Singleton getInstance() {
            return instance;
        }*/
        
        private static Singleton getInstance() {
            return instance;
        }
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        return Holder.getInstance();
    }

    public static String helloWorld() {
        return "hello";
    }

    public static void main(String... args) {
    	System.out.println(Singleton.helloWorld());

        Singleton.getInstance();
        Singleton.getInstance();
    }
}