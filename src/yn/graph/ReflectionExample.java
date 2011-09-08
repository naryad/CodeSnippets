package yn.graph;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class ReflectionExample {
	private static void createArray() {
		Class c = int.class;
		System.out.println(c.getName());
		int[][] ints2 = (int[][]) Array.newInstance(int.class, new int[] { 10,
				20 });
	}

	/**
	   * @param type the type to check.
	   *
	   * @return Returns <code>true</code> if <code>type</code> is a iterable type, <code>false</code> otherwise.
	   */
	  public static boolean isIterable(Type type) {
	    if ( type instanceof Class && isIterableClass( ( Class ) type ) ) {
	      return true;
	    }
	    if ( type instanceof ParameterizedType ) {
	      return isIterable( ( ( ParameterizedType ) type ).getRawType() );
	    }
	    if ( type instanceof WildcardType ) {
	      Type[] upperBounds = ( ( WildcardType ) type ).getUpperBounds();
	      return upperBounds.length != 0 && isIterable( upperBounds[0] );
	    }
	    return false;
	  }

	  /**
	   * Checks whether the specified class parameter is an instance of a collection class.
	   *
	   * @param clazz <code>Class</code> to check.
	   *
	   * @return <code>true</code> is <code>clazz</code> is instance of a collection class, <code>false</code> otherwise.
	   */
	  private static boolean isIterableClass(Class<?> clazz) {
	    List<Class<?>> classes = new ArrayList<Class<?>>();
	    computeClassHierarchy( clazz, classes );
	    return classes.contains( Iterable.class );
	  }

	  /**
	   * Get all superclasses and interfaces recursively.
	   *
	   * @param clazz The class to start the search with.
	   * @param classes List of classes to which to add all found super classes and interfaces.
	   */
	  private static void computeClassHierarchy(Class<?> clazz, List<Class<?>> classes) {
	    for ( Class current = clazz; current != null; current = current.getSuperclass() ) {
	      if ( classes.contains( current ) ) {
	        return;
	      }
	      classes.add( current );
	      for ( Class currentInterface : current.getInterfaces() ) {
	        computeClassHierarchy( currentInterface, classes );
	      }
	    }
	  }
	  
	public static void main(String[] argv) throws Exception {
		createArray();
	}
}