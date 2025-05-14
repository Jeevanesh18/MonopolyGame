package defaultpackage;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class labels_tiles extends JPanel{
	//labels for the name of the players
	JLabel label1=new JLabel();
    JLabel label2=new JLabel();
    JLabel label3=new JLabel();
    JLabel label4=new JLabel();
    JLabel label5=new JLabel();
    //labels for the info of the player
    static JLabel player1_info=new JLabel();
    static JLabel player2_info=new JLabel();
    static JLabel player3_info=new JLabel();
    static JLabel player4_info=new JLabel();
    labels_tiles(){
    }
    public static void show_tiles_with_name_and_players(){
    	//we created five labels in each tiles
    	//each tiles act as a JPanel as this class inherits JPanel
    	//the fifth label is the name of the tiles
    	//the other four labels are like placeholders for each player
    	//if a player is on the tile, the particular player's lable will appear
    	for(int i=1;i<=40;i++) {
    		//setting the label5 to the name of the tile
    		//players.read_file(i)[1] is a static method from players which reads the the value
    		//from the first column and i row which should be the name of tile i. (btw its from the duitRia_tiles.csv)
    		board_skeleton.tiles.get(i).label5.setText(players.read_file(i)[1]);
    		board_skeleton.tiles.get(i).label5.setFont(new Font("Times Roman",Font.BOLD,7));//each player have a name in players class
    		board_skeleton.tiles.get(i).label5.setForeground(Color.white);
    		board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label5);
    		//first check how many players are there in the game then see if each players are in that 
    		//particular tile or not. If yes, their label will appear where the text will be set the name of the player
    		if(game.sorted_players.size()>=1 & game.sorted_players.get(0).current_tile==i) {
    			board_skeleton.tiles.get(i).label1.setText(game.sorted_players.get(0).name);
    	    	board_skeleton.tiles.get(i).label1.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label1.setForeground(Color.red);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label1);
    		}
    		if(game.sorted_players.size()>=2 ) {
    			if(game.sorted_players.get(1).current_tile==i) {
    			board_skeleton.tiles.get(i).label2.setText(game.sorted_players.get(1).name);
    	    	board_skeleton.tiles.get(i).label2.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label2.setForeground(Color.yellow);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label2);
    			}
    		}
    		if(game.sorted_players.size()>=3 ) {
    			if(game.sorted_players.get(2).current_tile==i) {
    			board_skeleton.tiles.get(i).label3.setText(game.sorted_players.get(2).name);
    	    	board_skeleton.tiles.get(i).label3.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label3.setForeground(Color.orange);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label3);
    			}
    		}if(game.sorted_players.size()>=4 ) {
    			if(game.sorted_players.get(3).current_tile==i) {
    			board_skeleton.tiles.get(i).label4.setText(game.sorted_players.get(3).name);
    	    	board_skeleton.tiles.get(i).label4.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label4.setForeground(Color.blue);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label4);
    			}
    		}
    				
    		
    	}
    	//this is to create the four labels in the middle of the board
    	Border border=BorderFactory.createLineBorder(Color.green,3);
    	//the label in the middle contains the players name, balance and houses owned
    	if(game.sorted_players.size()>=1) {
    	String text="<html>Places owned by "+game.sorted_players.get(0).name+":<br>";
    	//for every players, they have a arrayList<Integer> where the position of the tiles owned is stored
    	for(int i=0;i<game.sorted_players.get(0).tiles_owned.size();i++) {
    		text+=(game.sorted_players.get(0).tiles_owned.get(i)+" : "+
    	           players.read_file(game.sorted_players.get(0).tiles_owned.get(i))[1]+"("+//pass the position of the tile and then get their name
    			   game.sorted_players.get(0).houses_on_each_tile[game.sorted_players.get(0).tiles_owned.get(i)]+
    			   "<br>"+" house(s) )"+"<br>");   
    	}//houses_on_each_tile is an int[] for each players with size of 40, where the index represents the position of the tile and the value represents the number of houses 
    	//the player own
    	text+=("Current Tile is "+game.sorted_players.get(0).current_tile+"<br>"+"Balance: "+game.sorted_players.get(0).balance+"M<br></html>");
    	System.out.println(text);
    	//formatting the label
    	player1_info.setVerticalAlignment(JLabel.TOP);
    	player1_info.setHorizontalAlignment(JLabel.LEFT);
    	player1_info.setText(text);
    	player1_info.setBounds(70,80,120,400);
    	player1_info.setBorder(border);
    	player1_info.setBackground(Color.black);
    	player1_info.setForeground(Color.white);
    	player1_info.setOpaque(true);
       	board_skeleton.frame.add(player1_info);
    	}
    	//if there are more players add their info too in the middle of the board
    	if(game.sorted_players.size()>=2) {
    		String text="<html>Places owned by "+game.sorted_players.get(1).name+":<br>";
        	for(int i=0;i<game.sorted_players.get(1).tiles_owned.size();i++) {
        		text+=(game.sorted_players.get(1).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(1).tiles_owned.get(i))[1]+"("+
        				game.sorted_players.get(1).houses_on_each_tile[game.sorted_players.get(1).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
        	}
        	text+=("Current Tile is "+game.sorted_players.get(1).current_tile+"<br>"+"Balance: "+game.sorted_players.get(1).balance+"M<br></html>");
        	player2_info.setVerticalAlignment(JLabel.TOP);
        	player2_info.setHorizontalAlignment(JLabel.LEFT);
    	player2_info.setText(text);
    	player2_info.setBounds(190,80,120,400);
    	player2_info.setBorder(border);
    	player2_info.setBackground(Color.black);
    	player2_info.setForeground(Color.white);
    	player2_info.setOpaque(true);
        board_skeleton.frame.add(player2_info);
    	}
    	if(game.sorted_players.size()>=3) {
    		String text="<html>Places owned by "+game.sorted_players.get(2).name+":<br>";
        	for(int i=0;i<game.sorted_players.get(2).tiles_owned.size();i++) {
        		text+=(game.sorted_players.get(2).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(2).tiles_owned.get(i))[1]+"("+
        				game.sorted_players.get(2).houses_on_each_tile[game.sorted_players.get(2).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
        	}
        	text+=("Current Tile is "+game.sorted_players.get(2).current_tile+"<br>"+"Balance: "+game.sorted_players.get(2).balance+"M<br></html>");
        	player3_info.setVerticalAlignment(JLabel.TOP);
        	player3_info.setHorizontalAlignment(JLabel.LEFT);
    	player3_info.setText(text);
    	player3_info.setBounds(310,80,120,400);
    	player3_info.setBorder(border);
    	player3_info.setBackground(Color.black);
    	player3_info.setForeground(Color.white);
    	player3_info.setOpaque(true);
    	board_skeleton.frame.add(player3_info);
    	}
    	if(game.sorted_players.size()>=4) {
    		String text="<html>Places owned by "+game.sorted_players.get(3).name+":<br>";
        	for(int i=0;i<game.sorted_players.get(3).tiles_owned.size();i++) {
        		text+=(game.sorted_players.get(3).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(3).tiles_owned.get(i))[1]+"("+
        				game.sorted_players.get(3).houses_on_each_tile[game.sorted_players.get(3).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
        	}
        	text+=("Current Tile is "+game.sorted_players.get(3).current_tile+"<br>"+"Balance: "+game.sorted_players.get(3).balance+"M<br></html>");
        	player4_info.setVerticalAlignment(JLabel.TOP);
        	player4_info.setHorizontalAlignment(JLabel.LEFT);
    	player4_info.setText(text);
    	player4_info.setBounds(430,80,120,400);
    	player4_info.setBorder(border);
    	player4_info.setBackground(Color.black);
    	player4_info.setForeground(Color.white);
    	player4_info.setOpaque(true);
    	board_skeleton.frame.add(player4_info);
    	}
    	}

    public static void update_board() {
    	//this method is to update the board, where basically the position of the players is updated
    	for(int i=1;i<=40;i++) {
    		//first the label1-4 should be set to null again
    		board_skeleton.tiles.get(i).label1.setText("");
    		board_skeleton.tiles.get(i).label2.setText("");
    		board_skeleton.tiles.get(i).label3.setText("");
    		board_skeleton.tiles.get(i).label4.setText("");
    		//and then check how many players are there, and then see if they are in that particular tile
    		//if yes, their name should appear on that tile
    	if(game.sorted_players.size()>=1 & game.sorted_players.get(0).current_tile==i) {
    		board_skeleton.tiles.get(i).label1.setText(game.sorted_players.get(0).name);
	    	board_skeleton.tiles.get(i).label1.setFont(new Font("Times Roman",Font.BOLD,10));
	    	board_skeleton.tiles.get(i).label1.setForeground(Color.red);
	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label1);
		}
		if(game.sorted_players.size()>=2 ) {
			if(game.sorted_players.get(1).current_tile==i) {
				board_skeleton.tiles.get(i).label2.setText(game.sorted_players.get(1).name);
    	    	board_skeleton.tiles.get(i).label2.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label2.setForeground(Color.yellow);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label2);
			}
		}
		if(game.sorted_players.size()>=3 ) {
			if(game.sorted_players.get(2).current_tile==i) {
				board_skeleton.tiles.get(i).label3.setText(game.sorted_players.get(2).name);
    	    	board_skeleton.tiles.get(i).label3.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label3.setForeground(Color.orange);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label3);
			}
		}if(game.sorted_players.size()>=4 ) {
			if(game.sorted_players.get(3).current_tile==i) {
				board_skeleton.tiles.get(i).label4.setText(game.sorted_players.get(3).name);
    	    	board_skeleton.tiles.get(i).label4.setFont(new Font("Times Roman",Font.BOLD,10));
    	    	board_skeleton.tiles.get(i).label4.setForeground(Color.blue);
    	    	board_skeleton.tiles.get(i).add(board_skeleton.tiles.get(i).label4);
			}
		}}
    }
    public static void update_player_info() {
    	//this is to update the labels in the middle of the board
    	//first just set the text in the labels to null
    	//then again set the text with the newest info
    	player1_info.setText("");
    	player2_info.setText("");
    	player3_info.setText("");
    	player4_info.setText("");
    	if(game.sorted_players.size()>=1) {
        	String text="<html>Places owned by "+game.sorted_players.get(0).name+":<br>";
        	for(int i=0;i<game.sorted_players.get(0).tiles_owned.size();i++) {
        		text+=(game.sorted_players.get(0).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(0).tiles_owned.get(i))[1]+"("+
        				game.sorted_players.get(0).houses_on_each_tile[game.sorted_players.get(0).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
        	}
        	text+=("Current Tile is "+game.sorted_players.get(0).current_tile+"<br>"+"Balance: "+game.sorted_players.get(0).balance+"M<br></html>");
        	player1_info.setText(text);
           	board_skeleton.frame.add(player1_info);
        	}
        	if(game.sorted_players.size()>=2) {
        		String text="<html>Places owned by "+game.sorted_players.get(1).name+":<br>";
            	for(int i=0;i<game.sorted_players.get(1).tiles_owned.size();i++) {
            		text+=(game.sorted_players.get(1).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(1).tiles_owned.get(i))[1]+"("+
            				game.sorted_players.get(1).houses_on_each_tile[game.sorted_players.get(1).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
            	}
            	text+=("Current Tile is "+game.sorted_players.get(1).current_tile+"<br>"+"Balance: "+game.sorted_players.get(1).balance+"M<br></html>");
        	player2_info.setText(text);
            board_skeleton.frame.add(player2_info);
        	}
        	if(game.sorted_players.size()>=3) {
        		String text="<html>Places owned by "+game.sorted_players.get(2).name+":<br>";
            	for(int i=0;i<game.sorted_players.get(2).tiles_owned.size();i++) {
            		text+=(game.sorted_players.get(2).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(2).tiles_owned.get(i))[1]+"("+
            				game.sorted_players.get(2).houses_on_each_tile[game.sorted_players.get(2).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
            	}
            	text+=("Current Tile is "+game.sorted_players.get(2).current_tile+"<br>"+"Balance: "+game.sorted_players.get(2).balance+"M<br></html>");
           player3_info.setText(text);
           board_skeleton.frame.add(player3_info);
        	}
        	if(game.sorted_players.size()>=4) {
        		String text="<html>Places owned by "+game.sorted_players.get(3).name+":<br>";
            	for(int i=0;i<game.sorted_players.get(3).tiles_owned.size();i++) {
            		text+=(game.sorted_players.get(3).tiles_owned.get(i)+" : "+players.read_file(game.sorted_players.get(3).tiles_owned.get(i))[1]+"("+
            				game.sorted_players.get(3).houses_on_each_tile[game.sorted_players.get(3).tiles_owned.get(i)]+"<br>"+" house(s) )"+"<br>");   
            	}
            	text+=("Current Tile is "+game.sorted_players.get(3).current_tile+"<br>"+"Balance: "+game.sorted_players.get(3).balance+"M<br></html>");
            player4_info.setText(text);
            board_skeleton.frame.add(player4_info);
        	}
    }
}
