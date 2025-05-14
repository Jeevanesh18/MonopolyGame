package defaultpackage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class game {
    static int current_player;
    static Random random=new Random();
	static Scanner sc_int=new Scanner(System.in);
	static players player1;
	static players player2;
	static players player3;
	static players player4;
	static ArrayList<players> sorted_players=new ArrayList<>();
	static int number_of_players;
	
    public static void main(String[] args) {
    	//so starting i create an object in number_of_players_window class
    	new number_of_players_window();
    	//go to the number_of_players_window class's constructor
	}
	//just a method to return a random integer 1-6
    public static int roll_dice() {
		int num=(random.nextInt(6)+1);
		return num;
	}
    
    public static void sort_players() {
    	//first check how many players are there
    	switch(number_of_players) {
    	//if two players, roll the dice two times as there are two dice.
    	//in the players class each player have some attribute. One of it is totalDice which is the amount they rolled
    	//u set the variable to the number rolled
    	//then just add the players in the ArrayList<players> 
    	case 2:
    		player1.totalDice=roll_dice()+roll_dice();
    		player2.totalDice=roll_dice()+roll_dice();
    		sorted_players.add(player1);
    		sorted_players.add(player2);
    		break;
    	case 3:
    		player1.totalDice=roll_dice()+roll_dice();
    		player2.totalDice=roll_dice()+roll_dice();
    		player3.totalDice=roll_dice()+roll_dice();
    		sorted_players.add(player1);
    		sorted_players.add(player2);
    		sorted_players.add(player3);
    		break;
    	case 4:
    		player1.totalDice=roll_dice()+roll_dice();
    		player2.totalDice=roll_dice()+roll_dice();
    		player3.totalDice=roll_dice()+roll_dice();
    		player4.totalDice=roll_dice()+roll_dice();
    		sorted_players.add(player1);
    		sorted_players.add(player2);
    		sorted_players.add(player3);
    		sorted_players.add(player4);
    		break;
    	default:
            System.out.println("Incorrect");
    		
    	}//the sort the players in the ArrayList<players> in game class according the totalDice attribute
    	 for (int i = 0; i < sorted_players.size() - 1; i++) {
             for (int j = 0; j < sorted_players.size() - i - 1; j++) {
                 if (sorted_players.get(j).totalDice < sorted_players.get(j+1).totalDice) {
                	 players temp = sorted_players.get(j);
                	 sorted_players.set(j, sorted_players.get(j + 1));
                	 sorted_players.set(j + 1, temp);
                 }
             }
         }
    }
    public static void start_game() {
    	// create an instance in board_skeleton class which creates a window with just a board
    	//the board have 40 tiles like JLabels on the edge of the window
    	new board_skeleton();
    	//calling the show_tiles_with_name_and_players static method will show the name of the tiles and
    	//the position of the players on the tiles
    	labels_tiles.show_tiles_with_name_and_players();
    	//while there are atleast 2 players alive, the game goes on
    	while(sorted_players.size()>=2) {
			for(current_player=0;current_player<sorted_players.size();current_player++) {
				//each player can call a method ur_turn() which basically starts their turn
				sorted_players.get(current_player).ur_turn();
			}
		}
	    //pop up showing the winner's name which is the last remaining player in the sorted_players array
	    JOptionPane.showMessageDialog(null,"Winner is: "+sorted_players.get(0).name,"DuitRia",JOptionPane.PLAIN_MESSAGE);
    }

    
}
