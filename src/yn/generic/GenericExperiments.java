package yn.generic;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericExperiments {
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.getClass().getTypeParameters());
		List<?> l = new ArrayList<String>();
		l.get(1); //works
		//but l.add("fasd") won't work
	}
	
	public static <T> T create(final String className) {
	    try {
	        final Class<?> clazz = Class.forName(className);

	        //WARNING: Type safety: Unchecked cast from capture#2-of ? to T
	        return (T) create(clazz); 
	    }
	    catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public static <T> T create(final Class<T> classToCreate) {
	    final Constructor<T> constructor;
	    try {
	        constructor = classToCreate.getDeclaredConstructor();
	        final T result = constructor.newInstance();
	        return result;
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static <T> T create(final Class<T> superType, final String className) {
		try {
			final Class<?> clazz = Class.forName(className);
			final Object object = clazz.newInstance();
			if (ArrayList.class.isInstance(object)) {
				return (T) object; // safe cast
			}
			return null; // or other error
		} catch (Exception ex) {
			return null;
		}
	}

}

class C {
	void m(Class<?> c1, Class<?> c2) {
	}

	<S, U extends S> void m(S s, U u) {
	}
}

class x {
	{
		final Class<Integer> cInteger = Integer.class;
		final Class<?> cSomething = null;
		final C c = new C();
		c.m(cInteger, cInteger);
		c.m(cSomething, cSomething); // *
	}
}