package defaultpackage;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class board_skeleton {
	static JFrame frame;
	 static ArrayList<labels_tiles> tiles;
	 Border border;
	 
    board_skeleton(){
    	//by creating an instance of board_skeleton:
   	 border=BorderFactory.createLineBorder(Color.green,3);
   	 frame=new JFrame();
   	 tiles=new ArrayList<labels_tiles>();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,670);
        frame.setResizable(false);
        frame.setLayout(null);
        //creating 40 tiles which are objects of labels_tiles
        for(int i=0;i<41;i++) {
       	 tiles.add(new labels_tiles());
        }
        //set the tiles around the edge
        int x=550;
        int y=550;
        for(int i=1;i<=40;i++) {
       	 tiles.get(i).setBounds(x,y,70,70);
       	 if(i<=10) 
       	     x-=55;
       	 else if(i<=20) 
       	     y-=55;
       	 else if(i<=30)
       	     x+=55;
       	 else if(i<41) 
       	     y+=55;
       	 tiles.get(i).setBorder(border);
       	 tiles.get(i).setBackground(Color.black);
       	 tiles.get(i).setOpaque(true);
       	 frame.add(tiles.get(i));
        }
            frame.setVisible(true);
        }
      

}
