package yn.graph;

import java.io.FilePermission;
import java.security.AccessController;

public class AccessControllerExample {

  public static void main(String args[]) throws Exception {
    FilePermission fp = new FilePermission("c:\\autoexec.bat", "read");
    AccessController.checkPermission(fp);
  }
}
