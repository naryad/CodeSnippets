package yn.experiments;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class GetResourceUsingClassLoader {
	 /**
	   * This method will search for <code>resource</code> in different
	   * places. The search order is as follows:
	   * <ol>
	   * <p><li>Search for <code>resource</code> using the thread context
	   * class loader under Java2.
	   * <p><li>Try one last time with
	   * <code>ClassLoader.getSystemResource(resource)</code>, that is is
	   * using the system class loader in JDK 1.2 and virtual machine's
	   * built-in class loader in JDK 1.1.
	   * </ol>
	   * <p/>
	   *
	   * @param resource
	   * @return TODO
	   */
	  public static URL getResource(String resource) {
	      ClassLoader classLoader = null;
	      URL url = null;
	      try {
	          classLoader = getTCL();
	          if (classLoader != null) {
	              url = classLoader.getResource(resource);
	              if (url != null) {
	                  return url;
	              }
	          }
	      } catch (Throwable t) {

	      }
	  
	      // Last ditch attempt: get the resource from the class path. It
	      // may be the case that clazz was loaded by the Extension class
	      // loader which the parent of the system class loader. Hence the
	      // code below.
	   
	      return ClassLoader.getSystemResource(resource);
	  }
	  /**
	   * Get the Thread context class loader.
	   * <p/>
	   *
	   * @return the Thread context class loader
	   * @throws IllegalAccessException
	   * @throws InvocationTargetException
	   */
	  public static ClassLoader getTCL() throws IllegalAccessException, InvocationTargetException {
	       return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
	          public Object run() {
	              return Thread.currentThread().getContextClassLoader();
	          }
	       });
	  }
}
