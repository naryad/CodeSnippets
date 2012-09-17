package yn.designpatterns;

public class SingletonPattern {

}
class SingletonBillPugh {
    // Private constructor prevents instantiation from other classes
    private SingletonBillPugh() { }

    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            public static final SingletonBillPugh instance = new SingletonBillPugh();
    }

    public static SingletonBillPugh getInstance() {
            return SingletonHolder.instance;
    }
}

class SingletonTraditional {
    private static final SingletonTraditional instance = new SingletonTraditional();

    // Private constructor prevents instantiation from other classes
    private SingletonTraditional() { }

    public static SingletonTraditional getInstance() {
            return instance;
    }
}

class SingletonLazyInit {
    private static SingletonLazyInit _instance;

    private SingletonLazyInit() {  }

    public static synchronized SingletonLazyInit getInstance() {
            if (null == _instance) {
                    _instance = new SingletonLazyInit();
            }
            return _instance;
    }
}

enum Singleton {
    INSTANCE;
}