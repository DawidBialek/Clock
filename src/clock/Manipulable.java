package clock;

public interface Manipulable{
	
	default public void onPause() {};
	default public void onStart() {};
	default public void onStop() {};
	
}
