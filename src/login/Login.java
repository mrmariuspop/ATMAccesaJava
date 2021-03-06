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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Stage {

	String linie = "";
//    public static void main(String[] args) {
//        launch(args);
//    }

	public Login()
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

        Text scenetitle = new Text("Welcome");
        scenetitle.setFill(Color.WHITE);
        scenetitle.setTranslateX(60);
        scenetitle.setTranslateY(0);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Card Number");
        userName.setTranslateX(-25);
        userName.setTextFill(Color.WHITE);
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setTranslateX(-29);
        grid.add(userTextField, 1, 1);
        
        Button btn = new Button("Sign in");
        btn.setTranslateX(50);
        btn.setTranslateY(80);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 4);
        
        Button btnExit = new Button("Unblock");
        btnExit.setTranslateX(-20);
        btnExit.setTranslateY(0);
        HBox hbtnExit = new HBox(10);
        hbtnExit.setAlignment(Pos.BOTTOM_RIGHT);
        hbtnExit.getChildren().add(btnExit);
        grid.add(hbtnExit, 1, 2);
        
        
        btnExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String signal = "unblock card please";
           	 JMS.sendQueueMessage("block", signal);


			}
        	
        });
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
        	String line = "";
            @Override
            public void handle(ActionEvent e) {
            	
            	String card = userTextField.getText();

            	 JMS.sendQueueMessage("java", card);

            	 String read = null;
            	 
            	  try {
            		  
            		  try {
							@SuppressWarnings("unused")
							ConsumeMessageCardAndPin a = new ConsumeMessageCardAndPin();
						} catch (JMSException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
            		  
            		  
//					  Thread.sleep(500);
					  
					  new CardOk();
	                   Login.this.hide();

            		  File file1 = new File("data.txt");
            		  String file1Str = FileUtils.readFileToString(file1);
					
            		  if (file1Str.contains("blocked")) 
            		  {
            			  actiontarget.setFill(Color.FIREBRICK);
		                    actiontarget.setText("Card Blocked");
		                    actiontarget.setTranslateX(-15); 
            		  }
            		  else
            		  {
					  if (file1Str.contains("approved")) 
		            	{
		            		actiontarget.setFill(Color.GREEN);
		                    actiontarget.setText("Card correct");
		                    actiontarget.setTranslateX(-15);
		                    
		                    new CardOk();
		                    Login.this.hide();
		            	} 
		            	else
		            	{
		            		actiontarget.setFill(Color.FIREBRICK);
		                    actiontarget.setText("Card incorrect");
		                    actiontarget.setTranslateX(-15);
		            	};
            		  }
					  }
				 catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
//            	  catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} 
            	  
            	
               
            }
        });

        Scene scene = new Scene(grid, 1350, 650);
        this.setScene(scene);
        this.show();
    }
}