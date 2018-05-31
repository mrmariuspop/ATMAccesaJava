
package login;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.jms.JMSException;

import org.apache.commons.io.FileUtils;

import com.sun.prism.Image;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PinOk extends Stage {

//    public static void main(String[] args) {
//        launch(args);
//    }

	public PinOk(){
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




        Button btn = new Button("Withdraw");
        btn.setTranslateX(-310);
        btn.setMinWidth(150);
        btn.setTranslateY(-65);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 4);

        
        Button btn1 = new Button("Balance");
        btn1.setTranslateX(260);
        btn1.setMinWidth(150);
        btn1.setTranslateY(-65);
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 0, 4);
        
        Button btn2 = new Button("Change PIN");
        btn2.setTranslateX(280);
        btn2.setTranslateY(30);
        btn2.setMinWidth(140);
   
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 4);
        
        
        Button btnExit = new Button("EXIT");
        btnExit.setTranslateX(-20);
        btnExit.setTranslateY(0);
        HBox hbtnExit = new HBox(10);
        hbtnExit.setAlignment(Pos.BOTTOM_RIGHT);
        hbtnExit.getChildren().add(btnExit);
        grid.add(hbtnExit, 1, 2);
        
        Button btnBack = new Button("Back");
        btnBack.setTranslateX(-20);
        btnBack.setTranslateY(0);
        HBox hbtnBack = new HBox(10);
        hbtnBack.setAlignment(Pos.BOTTOM_RIGHT);
        hbtnBack.getChildren().add(btnBack);
        grid.add(hbtnBack, 2, 3);
        

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btnBack.setOnAction(new EventHandler<ActionEvent>()
        {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new CardOk();
				PinOk.this.hide();
			}
        	
        });
        
        btnExit.setOnAction(new EventHandler<ActionEvent>()
        {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("EXIT pressed");
				new Login();
				PinOk.this.hide();
			}
        	
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	System.out.println("Widhdraw button pressed");
            	new Widhraw();
				PinOk.this.hide();

            }
        });
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	System.out.println("Balance button pressed");
            	
            	String signal = "";
           	    JMS.sendQueueMessage("balance", signal);

           	 try {
       		  
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
				
       		Label userName = new Label("You currently have " + file1Str + " RON");
            userName.setTranslateX(-25);
            userName.setTextFill(Color.GREEN);
            grid.add(userName, 0, 1);
       		  
//       			  actiontarget.setFill(Color.GREEN);
//	                    actiontarget.setText("You currently have " + file1Str + " RON");
//	                    actiontarget.setTranslateX(-15); 
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
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	System.out.println("Change PIN button pressed");
            	new ChangePin();
            	PinOk.this.hide();
            }
        });

        Scene scene = new Scene(grid, 1350, 650);
        this.setScene(scene);
        this.show();
    }
}