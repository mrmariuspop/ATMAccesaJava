package login;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.jms.JMSException;

import org.apache.commons.io.FileUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChangePin extends Stage {

	String linie = "";
//    public static void main(String[] args) {
//        launch(args);
//    }

	public ChangePin()
	{
		start();
	}
	
	
    public void start() {
        this.setTitle("JavaFX Welcome");
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-image: url('https://cdn.newsday.com/polopoly_fs/1.8442887.1402698582!/httpImage/image.JPG_gen/derivatives/landscape_1280/image.JPG')");

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Label userName = new Label("New PIN");
        userName.setTranslateX(-25);
        userName.setTextFill(Color.WHITE);
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setTranslateX(-29);
        grid.add(userTextField, 1, 1);
        
        Button btn = new Button("Change");
        btn.setTranslateX(50);
        btn.setTranslateY(80);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 4);
        
        Button btnExit = new Button("EXIT");
        btnExit.setTranslateX(-20);
        btnExit.setTranslateY(0);
        HBox hbtnExit = new HBox(10);
        hbtnExit.setAlignment(Pos.BOTTOM_RIGHT);
        hbtnExit.getChildren().add(btnExit);
        grid.add(hbtnExit, 1, 2);
        
       

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        
        btnExit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Login();
				ChangePin.this.hide();
			}
        	
        });
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("widhraw pressed");
				
				
				
				
				 try {
					 
					 String newPin = userTextField.getText();
						JMS.sendQueueMessage("newpin", Encde.encode(newPin));
						
						
           		  try {
							@SuppressWarnings("unused")
							ConsumeMessageAmount a = new ConsumeMessageAmount();
						} catch (JMSException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
           		  
           		  
					  Thread.sleep(500);

           		  File file1 = new File("data.txt");
           		  String file1Str = FileUtils.readFileToString(file1);
					
					  if (file1Str.contains("pinchanged")) 
		            	{
		            		actiontarget.setFill(Color.GREEN);
		                    actiontarget.setText("PinChanged");
		                    actiontarget.setTranslateX(-15);
		                    
		            	} 
					  }
				 catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
        	
        });


        Scene scene = new Scene(grid, 1350, 650);
        this.setScene(scene);
        this.show();
    }
}