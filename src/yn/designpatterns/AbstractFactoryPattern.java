package yn.designpatterns;

/* GUIFactory example -- */

interface GUIFactory {
    public Button createButton();
}
 
class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }
}
class OSXFactory implements GUIFactory {
    public Button createButton() {
        return new OSXButton();
    }
}
 
 
interface Button {
    public void paint();
}
 
class WinButton implements Button {
    public void paint() {
        System.out.println("I'm a WinButton");
    }
}
class OSXButton implements Button {
    public void paint() {
        System.out.println("I'm an OSXButton");
    }
}
 
 
class Application {
    public Application(GUIFactory factory) {
        Button button = factory.createButton();
        button.paint();
    }
}
 
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        new Application(createOsSpecificFactory());
    }
 
    public static GUIFactory createOsSpecificFactory() {
        int sys = readFromConfigFile("OS_TYPE");
        if (sys == 0) {
            return new WinFactory();
        } else {
            return new OSXFactory();
        }
    }

	private static int readFromConfigFile(String string) {
		return 0;
	}
}