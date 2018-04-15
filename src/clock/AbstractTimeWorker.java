package clock;

import javafx.animation.Timeline;
import javafx.scene.Node;

public abstract class AbstractTimeWorker implements NodeLoader{
	
	private Node node;
	private Timeline timeline;
	
	public AbstractTimeWorker(Node node, Timeline timeline){
		this.node = node;
		this.timeline = timeline;
	}
	
	@Override
	public Node getNode() {
		return node;
	}
	
	@Override
	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
