package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class number_of_players_window implements ActionListener{
	JButton submit_button;
	JTextField text_field;
	JFrame frame;
	number_of_players_window(){
   	 //when this constructor is called
   	 //a window will pop up asking how many players are playing which should be 2-4
	//creating a label which can be a space for a text or image
	 JLabel label=new JLabel();
	 label.setBounds(0, 0, 300, 200);//setting the bound and size of the label.setBounds(x,y,width,height)
	 
	 ImageIcon image=new ImageIcon("src\\monopoly.png");//saving the image as an ImageIcon object
	 Image img = image.getImage();
	 Image scaledImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);//scale the image or changing the size
	 ImageIcon scaledIcon = new ImageIcon(scaledImg);//saving the scalled image as another ImageIcon object
	 label.setIcon(scaledIcon);//setting the image in the label
	 //creating a button and format it like the size,background,font
	 //also adding an actionListener
	 submit_button=new JButton("Submit");
	 submit_button.setBounds(0, 200, 100, 40);
	 submit_button.setFocusable(false);
	 submit_button.setBackground(Color.BLUE);
	 submit_button.setFont(new Font("Times Roman",Font.BOLD,18));
	 submit_button.setForeground(new Color(0xFFFFFF));
	 submit_button.addActionListener(this);
	 //creating a textfield that accepts an input as a String which is the number of players(2-4)
	 text_field=new JTextField();
	 text_field.setBounds(100,200,200,40);
	 text_field.setText("Enter the number of players(2-4)");
	 text_field.setForeground(new Color(0x00FF00));
	 text_field.setBackground(Color.black);
	 text_field.setCaretColor(Color.white);
     //creating a JFrame object,frame and adding the text_field and submit_button
	 frame=new JFrame();
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(300,280);
     frame.setLayout(null);
     frame.add(label);
     frame.add(submit_button);
     frame.add(text_field);
     frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//when the button in the first window is clicked
		if(e.getSource()==submit_button) {
			//there is a static int variable in game class which holds the number of players still alive
			//the value from the textfield in the first window is saved in that variable
			game.number_of_players=Integer.parseInt(text_field.getText());
			//the first window will disappear
			frame.dispose();
			//it will call the constructor in the name_of_players_window class
			new name_of_players_window();
		}
		
	}
}
