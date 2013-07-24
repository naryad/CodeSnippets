package yn.memcached;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MemcachedExperiments {
	public static void main(String[] args) {
		try {

			System.out.print("Enter the new key : ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String key = null;
			key = reader.readLine();
			
			System.out.print("Enter the new value : ");
			String value = null;
			value = reader.readLine();

			MemcachedClient cache = new MemcachedClient(
					AddrUtil.getAddresses("127.0.0.1:11211"));

			// read the object from memory
			System.out.println("Get Object before set :" + cache.get(key));

			// set a new object
			cache.set(key, 0, value);

			System.out.println("Get Object after set :" + cache.get(key));

		} catch (IOException ex) {
			Logger.getLogger(MemcachedExperiments.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(0);
		}

		System.exit(0);

	}
}
