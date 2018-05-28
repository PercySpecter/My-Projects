
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
        
        BorderPane border = new BorderPane();

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
        
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(playButton , exitButton);
        buttons.setAlignment(Pos.CENTER);

        border.setTop(label);
        border.setBottom(buttons);
        
        Scene scene = new Scene(border);
        window.setScene(scene);
        window.showAndWait();
        
        return playAgain;
    }
    
    private static void replayGame(boolean replay)
    {
    	playAgain = replay;
    }

}