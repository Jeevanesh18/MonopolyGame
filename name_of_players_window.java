package defaultpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class name_of_players_window implements ActionListener{
	JFrame frame;
	JButton submit_button;
	JTextField text_field1;
	JTextField text_field2;
	JTextField text_field3;
	JTextField text_field4;
     name_of_players_window(){
    	 submit_button=new JButton("Submit");//creating a JButton object(button) written 'submit'
    	 submit_button.setFocusable(false);
    	 submit_button.setBackground(Color.BLUE);//setting the background color in the button of the button(blue)
    	 submit_button.setFont(new Font("Times Roman",Font.BOLD,18));//setting the format of the font in the button
    	 submit_button.setForeground(new Color(0xFFFFFF));//setting the font color in the button of the button(black)
    	 submit_button.addActionListener(this);//adding actionlistener which is a method that will tell what do if the button is clicked
    	 
    	 frame=new JFrame();//creating a small window to put the button,textfield and image
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(300,280);//setting the size of the window
         frame.setLayout(null);//setting the layout manager of the frame to null. so need to tell x and y coordinates of the elements in it
         
         //creating four JLabel for each player but not adding first. just creating
         JLabel player1=new JLabel();//JLabel is a space where u can write some text
         player1.setText("Player 1 name:");
         player1.setBounds(0,0,200,40);
         JLabel player2=new JLabel();
         player2.setText("Player 2 name:");
         player2.setBounds(0,40,200,40);
         JLabel player3=new JLabel();
         player3.setText("Player 3 name:");
         player3.setBounds(0,80,200,40);
         JLabel player4=new JLabel();
         player4.setText("Player 4 name:");
         player4.setBounds(0,120,200,40);
         
         //then we see how many players are in the game by checking the static number_of_players in game class 
         //which was entered by the user in the first window
    	 switch(game.number_of_players) {
    	 //if it was 2, two text_field is added the get the both player's name
    	 //and beside the 2 text_field the 2 Jlabels with the text 'player1' and 'player2' is added
     	case 2:
     		text_field1=new JTextField();
     		text_field2=new JTextField();
     		text_field1.setBounds(100,0,200,40);
     		text_field2.setBounds(100,40,200,40);
       	    submit_button.setBounds(100, 80, 100, 40);
     		frame.add(text_field1);
     		frame.add(text_field2);
     		frame.add(player1);
     		frame.add(player2);
     		frame.setSize(300,160);
     		break;
     		//if it was 3, 3 text_field is added the get the three player's name
       	 //and beside the 3 text_field the 3 Jlabels with the text 'player1','player2' and 'player3' is added
     	case 3:
     		text_field1=new JTextField();
     		text_field2=new JTextField();
     		text_field3=new JTextField();
     		text_field1.setBounds(100,0,200,40);
     		text_field2.setBounds(100,40,200,40);
     		text_field3.setBounds(100,80,200,40);
            submit_button.setBounds(100, 120, 100, 40);
     		frame.add(text_field1);
     		frame.add(text_field2);
     		frame.add(text_field3);
     		frame.add(player1);
     		frame.add(player2);
     		frame.add(player3);
     		frame.setSize(300,200);
     		break;
     	case 4:
     		text_field1=new JTextField();
     		text_field2=new JTextField();
     		text_field3=new JTextField();
     		text_field4=new JTextField();
     		text_field1.setBounds(100,0,200,40);
     		text_field2.setBounds(100,40,200,40);
     		text_field3.setBounds(100,80,200,40);
     		text_field4.setBounds(100,120,200,40);
       	    submit_button.setBounds(100, 160, 100, 40);
     		frame.add(text_field1);
     		frame.add(text_field2);
     		frame.add(text_field3);
     		frame.add(text_field4);
     		frame.add(player1);
     		frame.add(player2);
     		frame.add(player3);
     		frame.add(player4);
     		frame.setSize(300,240);
     		break;
     	default:
             System.out.println("Incorrect");
     	}
  		frame.add(submit_button);
    	 frame.setVisible(true);
     }
	@Override
	public void actionPerformed(ActionEvent e) {
		//if the submit button in the second window is clicked:
		if(e.getSource()==submit_button) {
			//get the name input of the user 
			//before accessing it check how many players are in the game
			switch(game.number_of_players) {
			case 2://if it was 2 players, get the input from the text_field1 and text_field2
				game.player1=new players(text_field1.getText());
				game.player2=new players(text_field2.getText());
				break;
			case 3://and so on
				game.player1=new players(text_field1.getText());
				game.player2=new players(text_field2.getText());
				game.player3=new players(text_field3.getText());
				break;
			case 4:
				game.player1=new players(text_field1.getText());
				game.player2=new players(text_field2.getText());
				game.player3=new players(text_field3.getText());
				game.player4=new players(text_field4.getText());
				break;
			}
			frame.dispose();//then the second window disappears
			game.sort_players();//calling the static method sort_players() from class 
			//there is ArrayList object in game class to store the player objects
			//ArrayList<> is used as in the game, when a player lose, the player is kicked out from the ArrayList
			//regular list cant do that as the size is fixed
			//when sort_players() is called, the players will be sorted(who is first,second,..) in the ArrayList
			game.start_game();
			//then start_game() is called which is also a static method in game
		}
	}
     
}
