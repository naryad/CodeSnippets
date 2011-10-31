package yn.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

interface Command {
    void runCommand();
}

public class HashMapCommand {

    public static void main(String[] args) throws Exception {
        Map<Character, Command> methodMap = new HashMap<Character, Command>();

        methodMap.put('h', new Command() {
            public void runCommand() { System.out.println("help"); };
        });

        methodMap.put('t', new Command() {
            public void runCommand() { System.out.println("teleport"); };
        });

        char cmd = 'h';
        methodMap.get(cmd).runCommand();  // prints "Help"

        cmd = 't';
        methodMap.get(cmd).runCommand();  // prints "teleport"

    }
}

class HashMapCommandUsingReflection {

    public static void main(String[] args) throws Exception {
        Map<Character, Method> methodMap = new HashMap<Character, Method>();

        methodMap.put('h', HashMapCommandUsingReflection.class.getMethod("showHelp"));
        methodMap.put('t', HashMapCommandUsingReflection.class.getMethod("teleport"));

        char cmd = 'h';
        methodMap.get(cmd).invoke(null);  // prints "Help"

        cmd = 't';
        methodMap.get(cmd).invoke(null);  // prints "teleport"

    }

    public static void showHelp() {
        System.out.println("Help");
    }

    public static void teleport() {
        System.out.println("teleport");
    }
}