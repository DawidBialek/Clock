package clock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*TODO:
 * - todo-buttons implementation
 * 		- stopwatch button implementation
 * 		- timer button implementation
 * - fix clock refresh lag after switching to clock view
 * - fix milisecond display on stopwatch (without substr)
 * - change time format in stopwatch to HH:mm:ss:SS
*/

public class App extends Application{
	
	private int i=6; //because Arial==6, temp solution
	private Button clockButton,
				   timerButton,
				   stopwatchButton, 
				   playButton, 
				   stopButton, 
				   pauseButton;
	private Text timeDisplay;
	private HBox hbox;
	ClockBorderPane border;
	Scene scene;
	
	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void start(Stage stage) throws Exception{
		initTimeDisplay();
		initButtons();
		initHBox();
		initBorderPane();
		initScene();
		initStage(stage);
		activate();
	}
	
	private void initTimeDisplay() {
		timeDisplay = new Text();
		timeDisplay.setX(20);
		timeDisplay.setY(100);
		timeDisplay.setFont(new Font("Arial",100));
		timeDisplay.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
	}
	
	private void initButtons() {
		initClockButton();
		initStopwatchButton();
		initTimerButton();
		initPlayButton();
		initStopButton();
		initPauseButton();
	}
	
	private Button initButtonFramework(String text, Text textDisplay, EventHandler<ActionEvent> onFinished, long duration, AppState localAppState) {
		textDisplay.setX(20);
		textDisplay.setY(100);
		textDisplay.setFont(new Font("Arial",100));
		textDisplay.setText(text);
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(duration), onFinished));
		//TODO: Implementation in relation to appstate!
		NodeLoader nodeLoader = NodeLoaderFactory.create(localAppState, textDisplay, timeline);
		ButtonToNodeLoaderMapper.add(localAppState, nodeLoader);
		
		Button button = new Button(localAppState.getText());
		button.setOnAction(e->{
			border.switchCenter(ButtonToNodeLoaderMapper.get(localAppState));
		});
		return button;
	}
	
	private void initClockButton() {
		Text textDisplay = new Text();
		clockButton = initButtonFramework(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString(),
										  textDisplay,
										  e -> textDisplay.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString()),
										  1000,
										  AppState.CLOCK);
	}
	
	private void initStopwatchButton() {
		Text textDisplay = new Text();
		
		stopwatchButton = initButtonFramework("00:00:00",
											  textDisplay,
											  e -> {},
											  10,
											  AppState.STOPWATCH);
	}
	//#TODO - switches state to timer
	private void initTimerButton() {
		timerButton = new Button("Timer");
		timerButton.setOnAction(e->{
			//appState = AppState.TIMER;
		});
	}
	
	//#TODO - resumes the timer or stopwatch
	private void initPlayButton() {
		playButton = new Button("Play");
		//TODO make it use objects from ButtonToNodeLoaderMapper
		
		/*Stopwatch stopwatch = new Stopwatch();
		playButton.setOnAction(e->{
			stopwatch.setBaseValue(System.currentTimeMillis());
			if(ButtonToNodeLoaderMapper.get(AppState.STOPWATCH).getNode() instanceof Text) {
				Text textDisplay = (Text)ButtonToNodeLoaderMapper.get(AppState.STOPWATCH).getNode();
				Timeline timeline = ButtonToNodeLoaderMapper.get(AppState.STOPWATCH).getTimeline();
				timeline.getKeyFrames().removeAll();
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), f -> textDisplay.setText(stopwatch.update())));
			}
		});*/
	}
	
	//#TODO - stops the timer or stopwatch
	private void initStopButton() {
		stopButton = new Button("Stop");
		stopButton.setOnAction(e->{
			//timeline.stop();
		});
	}
	
	//#TODO - pauses or resumes timer or stopwatch
	private void initPauseButton() {
		pauseButton = new Button("Pause");
	}
	
	private void initHBox() {
		hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		System.out.println("clockButton: " + clockButton);
		System.out.println("stopwatchButton: " + stopwatchButton);
		System.out.println("timerButton: " + timerButton);
		System.out.println("playButton: " + playButton);
		System.out.println("stopButton: " + stopButton);
		System.out.println("pauseButton: " + pauseButton);
		hbox.getChildren().addAll(clockButton, stopwatchButton, timerButton, new Separator(Orientation.VERTICAL), playButton, stopButton, pauseButton);
		hbox.setAlignment(Pos.CENTER);
	}
	
	private void initBorderPane() {
		System.out.println(ButtonToNodeLoaderMapper.get(AppState.CLOCK));
		border = new ClockBorderPane(ButtonToNodeLoaderMapper.get(AppState.CLOCK));
		border.setCenter(timeDisplay);
		border.setBottom(hbox);
	}
	
	private void initScene() {
		scene =  new Scene(border, 420,180);
	}
	
	private void initStage(Stage stage) {
		stage.setTitle("Clock");
		stage.setScene(scene);
		stage.show();
	}
	
	private void activate() {
		border.switchCenter(ButtonToNodeLoaderMapper.get(AppState.CLOCK));
	}
}
