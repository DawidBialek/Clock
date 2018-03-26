package clock;

import javafx.scene.layout.BorderPane;

public class ClockBorderPane extends BorderPane{

	private NodeLoader activeNodeLoader;
	
	ClockBorderPane(NodeLoader nodeLoader){
		this.activeNodeLoader = nodeLoader;
		this.activeNodeLoader.getTimeline().playFromStart();
	}
	
	void switchCenter(NodeLoader nodeLoader) {
		this.activeNodeLoader.getTimeline().pause();
		setCenter(nodeLoader.getNode());
		this.activeNodeLoader = nodeLoader;
		this.activeNodeLoader.getTimeline().play();
	}
	
	public NodeLoader getActiveNodeLoader() {
		return activeNodeLoader;
	}
}
