package yn.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedClient;

public class MemcachedApp {
	public static void main(String[] args) throws IOException {
		MemcachedClient c = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		List<String> list = new ArrayList<String>(); 
		list.add("well memcached rocks");
		c.set("someKey", 3600, list);
		List<String> myObject=(List<String>) c.get("someKey");
		System.out.println("Value of someKey" + myObject);
		c.delete("someKey");
		myObject=(List<String>) c.get("someKey");
		System.out.println("Value of someKey" + myObject);
		c.shutdown();
	}
}
