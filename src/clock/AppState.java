package clock;

public enum AppState {
	CLOCK("Clock"),
	STOPWATCH("Stopwatch"),
	TIMER("Timer");
	
	private String text;
	
	AppState(String text){
		this.text = text;
	}
	
	String getText() {
		return text;
	}
}
