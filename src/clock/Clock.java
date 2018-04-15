package clock;

import javafx.animation.Timeline;
import javafx.scene.Node;

public class Clock extends AbstractTimeWorker{
	public Clock(Node node, Timeline timeline) {
		super(node,timeline);
	}
}
