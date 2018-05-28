
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Result 
{
	static boolean playAgain;

    public static boolean display(String title, String message)
    {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        

        Label label = new Label();
        label.setText(message);
        Button playButton = new Button("Play Again");
        Button exitButton = new Button("Exit");
        
        exitButton.setOnAction(e -> 
        {
        	replayGame(false);
        	window.close();
        });
        playButton.setOnAction(e -> 
        {
        	replayGame(true);
        	window.close();
        });
        
        HBox layout = new HBox(10);
        layout.getChildren().addAll(label , playButton , exitButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return playAgain;
    }
    
    private static void replayGame(boolean replay)
    {
    	playAgain = replay;
    }

}