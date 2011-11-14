package yn.core;

public class ImportantUtil {
	public static void main(String[] args) {
		System.out.println(humanReadableByteCount(2232331, false));
		System.out.println(humanReadableByteCount(2232331, true));
	}
	
	public static String humanReadableByteCount(long bytes, boolean si) {

/*        		0:        0 B        0 B
		       27:       27 B       27 B
		      999:      999 B      999 B
		     1000:     1.0 kB     1000 B
		     1023:     1.0 kB     1023 B
		     1024:     1.0 kB    1.0 KiB
		     1728:     1.7 kB    1.7 KiB
		   110592:   110.6 kB  108.0 KiB
		  7077888:     7.1 kB    6.8 MiB
		452984832:   453.0 MB  432.0 MiB
		28991029248:    29.0 GB   27.0 GiB
		1855425871872:     1.9 TB    1.7 TiB
		9223372036854775807:     9.2 EB    8.0 EiB   (Long.MAX_VALUE)
*/
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
}
