package clock;

import javafx.animation.Timeline;
import javafx.scene.Node;

public interface NodeLoader extends Manipulable {
	public Node getNode();
	public Timeline getTimeline();
}
