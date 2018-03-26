package clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*#TODO
 * - change formating from using substring to only formatter
 */

public class Stopwatch {
	private long initialValue;
	DateFormat formatter = new SimpleDateFormat("mm:ss:SS");
	
	public Stopwatch() {
		initialValue = System.currentTimeMillis();
	}
	
	public String update() {
		return formatter.format(System.currentTimeMillis() - initialValue).substring(0, 8);
	}

}
