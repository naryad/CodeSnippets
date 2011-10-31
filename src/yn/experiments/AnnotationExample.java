package yn.experiments;
import java.lang.annotation.*; 
import java.lang.reflect.*; 

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)  
@interface MyAnno { 
  String str() default "nonsense"; 
  int val() default 0; 
} 
 
public class AnnotationExample { 
 
  // Annotate a method. 
  @MyAnno(str = "Annotation Example", val = 100) 
  public static void myMeth() { 
    AnnotationExample ob = new AnnotationExample(); 
 
    // Obtain the annotation for this method 
    // and display the values of the members. 
    try { 
      // First, get a Class object that represents 
      // this class. 
      Class c = ob.getClass(); 
 
      // Now, get a Method object that represents 
      // this method. 
      Method m = c.getMethod("myMeth"); 
 
      // Next, get the annotation for this class. 
      MyAnno anno = m.getAnnotation(MyAnno.class); 
  
      // Finally, display the values. 
      System.out.println(anno.str() + " " + anno.val()); 
    } catch (NoSuchMethodException exc) { 
       System.out.println("Method Not Found."); 
    } 
  } 
 
  public static void main(String args[]) { 
    myMeth(); 
  } 
}