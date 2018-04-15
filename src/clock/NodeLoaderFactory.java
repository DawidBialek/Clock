package clock;

import javafx.animation.Timeline;
import javafx.scene.Node;

public class NodeLoaderFactory {
	public static NodeLoader create(AppState type, Node node, Timeline timeline) {
		switch(type) {
			case CLOCK:	
				return new Clock(node,timeline);
			case STOPWATCH:
				return new Stopwatch(node,timeline);
		case TIMER:
				System.out.println("Returns Timer");
				break;
			default:
				System.out.println("In default case");
		}
		return null;
	}

}
