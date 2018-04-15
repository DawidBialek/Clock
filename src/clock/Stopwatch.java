package clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*#TODO
 * - change formating from using substring to only formatter
 */

import javafx.animation.Timeline;
import javafx.scene.Node;

public class Stopwatch extends AbstractTimeWorker {
	private Long baseValue;
	private Long timeDifference;
	DateFormat formatter = new SimpleDateFormat("mm:ss:SS");
	
	public Stopwatch(Node node, Timeline timeline) {
		super(node,timeline);
	}
	
	public String update() {
		return formatter.format(System.currentTimeMillis() - baseValue).substring(0, 8);
	}
	
	public void setBaseValue(long value) {
		baseValue = value;
	}
	
	@Override
	public void onPause() {
		timeDifference = System.currentTimeMillis() - baseValue;
	}
	
	@Override
	public void onStart() {
		baseValue = System.currentTimeMillis();
		if(timeDifference != null) {
			baseValue -= timeDifference;
		}
	}
	
	@Override
	public void onStop() {
		baseValue = null;
		timeDifference = null;
	}
	
}
