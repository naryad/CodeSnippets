package yn.generic;

import java.util.List;

public class ParametricClassArgument {

}
class A<T>
{
    public A(Class<T> c)
    {
    }

    public static void main(String [] args)
    {
        A<String> a1 = new A<String>(String.class);     // OK
        //Class<List<String>> clazz = (List<String>).getClass();
        //Class<List<String>> list = List<String>.class;

        //System.out.println((List<String>).class);
        
        //A<List<String>> a2 = new A<List<String>>(List<String>.class);  // error
        
        //A<List<String>> a3 = new A<List<String>>(Class<List<String>>);  // error
        SomeContainer<String> someString = new SomeContainer<String>();
        String string = someString.createContents(String.class);
        System.out.println("Created a string out of thin air --|" + string + "|--");
        
        //This shall throw a runtime exception
        SomeContainer<List> someList = new SomeContainer<List>();
        List newList = someList.createContents(List.class);
        System.out.println("Created a list out of thin air --|" + newList + "|--");
    }
}

class SomeContainer<E>
{
    public E createContents(Class<E> clazz)
    {
    	E e = null;
        try {
			e = clazz.newInstance();
		} catch (InstantiationException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return e;
    }
}
