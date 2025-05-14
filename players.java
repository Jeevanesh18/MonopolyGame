package defaultpackage;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.io.*;

public class players extends game{
	//constructor it accepts a name
players(String name){
	this.name=name;
}
String name;
double balance=0;
ArrayList<Integer> tiles_owned=new ArrayList<Integer>();
int current_tile=1;//starting time the current_tile of each player is 1
static final int jail_tile=11;
int[] houses_on_each_tile=new int[41];//one extra as i use GO as index 1 instead of 0
int dice1;//first dice roll
int dice2;//second dice roll
int totalDice;
boolean injail=false;
static int[] fate= {3,8,18,23,34,37};//which tile is fate
static int[] tax= {5,39};//which tile is tax
static final int parking_tile=21;
static final int prison_tile=31;
static int[] special_tiles= {6,16,26,36};//which tile is special like KLIA and train station
public void ur_turn(){
	//when a player is playing their turn
	//first check if their balance is more than 0, otherwise they lose
	if(this.balance<0) {
		this.quit_game();
		return;
	}
	int a=JOptionPane.showConfirmDialog(null, this.name+" do you wanna roll?","DuitRia",JOptionPane.YES_NO_OPTION);
	//ask if they wanna roll
	if(a==0) {//if yes, roll 2 times
		this.dice1=roll_dice();
		this.dice2=roll_dice();
		this.totalDice=dice1+dice2;
		JOptionPane.showMessageDialog(null,this.name+"You rolled: "+this.totalDice,"DuitRia",JOptionPane.PLAIN_MESSAGE);
		labels_tiles.update_board();//the player hasnt move as they might be in jail
		labels_tiles.update_player_info();//this is just to make sure the players are in their respective tiles edy
		this.play();//after roll, they start the play
	}else {//if they dont wanna roll, they quit
		this.quit_game();
		return;
	}
	labels_tiles.update_board();
	labels_tiles.update_player_info();
}
private void play() {
	//if they are starting from zero they get 2M.
	if(this.current_tile==1){
		this.balance+=2;
		
	}//if they were in jail before
	if(this.injail) {//check if they double roll, if yes, they just move fine, or they pay 250k fine before moving
		if(this.dice1!=this.dice2) {
			JOptionPane.showMessageDialog(null,"You didnt double dice. Thus paid fine worth 250k","DuitRia",JOptionPane.PLAIN_MESSAGE);
			this.injail=false;//change their injail status after coming out of prison
			this.balance-=0.25;
		}if(this.balance<0) {//if their balance is lower than 0, they lose 
			this.quit_game();
			return;
		}
	}//update their current_tile
	this.current_tile+=this.totalDice;
	//if they made a full round edy, correct the tile and give them 2M
	if(this.current_tile>40) {
		this.current_tile=this.current_tile%40;
		this.balance+=2;
	}//update the board and the info in the middle of the board
	labels_tiles.update_board();
	labels_tiles.update_player_info();
	//check if they are in fate,tax,jail,parking,prison tiles
	if(isFate(this.current_tile)) {
		this.fate();
		this.show_balance();//show_balance() basically call update_player_info() again
		return;
	}
	if(isTax(this.current_tile)) {
		this.tax();
		this.show_balance();
		return;
	}
	if(this.current_tile==jail_tile) {
		this.jail();
		this.show_balance();
		return;
	}
	if(this.current_tile==parking_tile) {
		JOptionPane.showMessageDialog(null,"You have landed on parking tile","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.show_balance();//if they are on parking or prison tile, nothing happens, the next player rolls
		return;
	}
	if(this.current_tile==prison_tile) {
		JOptionPane.showMessageDialog(null,"You have landed on prison to visit","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.show_balance();
		return;
	}//if they didnt land on any special tile, they continue playing
	this.continue_play(this.current_tile);
	if(sorted_players.contains(this))//if they player,still in game,update middle board 
	    this.show_balance();
	return;
	
}
public void quit_game() {
	this.tiles_owned.clear();//the tiles owned by them will become zero
	JOptionPane.showMessageDialog(null,this.name+" you are eliminated.","DuitRia",JOptionPane.PLAIN_MESSAGE);
	sorted_players.remove(this);//they are removed from the sorted_players array 
	for(int i=0;i<sorted_players.size();i++)
		System.out.print(sorted_players.get(i).name);
	System.out.println();
	labels_tiles.update_board();
	labels_tiles.update_player_info();
	current_player=current_player-1;//the current player minus one, because of the nature of arrayList, if not the next player will be skipped
}
//return an array that contains the tile_number,tile_name,rent,house1-4,land_price
public static String[] read_file(int targetRow) {
String file = "src\\duitRia_tiles.csv";
BufferedReader reader = null;
String line = "";
try {
 reader = new BufferedReader(new FileReader(file));
 int currentRow = 0;
 while((line = reader.readLine()) != null) {
	 if (currentRow == targetRow) {
		 String[] row = line.split(",");
		 return row; 
     }
     currentRow++;
  }
}
catch(Exception e) {
 e.printStackTrace();
}
finally {
 try {
  reader.close();
 }catch (IOException e) {
	    e.printStackTrace(); 
}}
return null;
}
public void show_balance() {
	labels_tiles.update_player_info();
}
//checks a particular tile owned by who, if no one null is returned
public static players owned_by(int current_tile) {
	for(int i=0;i<sorted_players.size();i++) {
		for(int j=0;j<sorted_players.get(i).tiles_owned.size();j++) {
			if(sorted_players.get(i).tiles_owned.get(j)==current_tile)
				return sorted_players.get(i); 
		}
	}
	return null;
}//checks if the tile is fate or not
public boolean isFate(int current_tile) {
	for(int i=0;i<fate.length;i++) {
		if(fate[i]==current_tile)
			return true;
	}
	return false;
}
//if it is a fate tile,one of nine methods will be chosen
public void fate(){
	JOptionPane.showMessageDialog(null,"You have landed on fate","DuitRia",JOptionPane.PLAIN_MESSAGE);
	int choice=random.nextInt(10)+1;
	switch(choice) {
	case 1:{
		JOptionPane.showMessageDialog(null,"You got advance_to_go","DuitRia",JOptionPane.PLAIN_MESSAGE);
		advance_to_go();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 2:{
		JOptionPane.showMessageDialog(null,"You got advance_to_railroad","DuitRia",JOptionPane.PLAIN_MESSAGE);
		advance_to_railroad();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 3:{
		JOptionPane.showMessageDialog(null,"You got birthday","DuitRia",JOptionPane.PLAIN_MESSAGE);
		birthday();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 4:{
		JOptionPane.showMessageDialog(null,"You got bank_error","DuitRia",JOptionPane.PLAIN_MESSAGE);
		bank_error();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 5:{
		JOptionPane.showMessageDialog(null,"You got go_back_3","DuitRia",JOptionPane.PLAIN_MESSAGE);
		go_back_3();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 6:{
		JOptionPane.showMessageDialog(null,"You got go_to_jail","DuitRia",JOptionPane.PLAIN_MESSAGE);
		go_to_jail();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 7:{
		JOptionPane.showMessageDialog(null,"You got general_repairs","DuitRia",JOptionPane.PLAIN_MESSAGE);
		general_repairs();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 8:{
		JOptionPane.showMessageDialog(null,"You got hospital_fees","DuitRia",JOptionPane.PLAIN_MESSAGE);
		hospital_fees();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 9:{
		JOptionPane.showMessageDialog(null,"You got school_fees","DuitRia",JOptionPane.PLAIN_MESSAGE);
		school_fees();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	case 10:{
		JOptionPane.showMessageDialog(null,"You got speeding_fine","DuitRia",JOptionPane.PLAIN_MESSAGE);
		speeding_fine();
		labels_tiles.update_board();
		labels_tiles.update_player_info();
		break;
		}
	default:;
	}
}
public void tax() {//if they are in tax,the balance reduces by 2M
	JOptionPane.showMessageDialog(null,"You have landed on tax tile(-2M)","DuitRia",JOptionPane.PLAIN_MESSAGE);
	this.balance-=2;
	if(this.balance<0)
		this.quit_game();
}
//checks whether they are in Tax tile or not
public boolean isTax(int current_tile) {
	for(int i=0;i<tax.length;i++) {
		if(tax[i]==current_tile)
			return true;
	}
	return false;
}
public void continue_play(int current_tile) {
	if(owned_by(current_tile)!=null) {//if the tile owned by someone:
	   if(owned_by(current_tile).name.equalsIgnoreCase(this.name)) {//if tile is owned by yourself
		   JOptionPane.showMessageDialog(null,"This tile is owned by u: "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
		   if(!(is_special_tiles(current_tile)))//checks if it is a special tile
		      owned_by_you(current_tile);
		   else
			   special_tile_owned_by_you(current_tile);
		   return;
	   }else {//owned by someone else
		   JOptionPane.showMessageDialog(null,"This tile is owned by "+owned_by(current_tile).name +" : "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
		  if(!(is_special_tiles(current_tile)))
			      owned_by_others(current_tile);
			   else
				   special_tile_owned_by_others(current_tile);
		   return;
	   }
	}else {
		check_wanna_buy(current_tile);//if it not owned, check if they wanna buy the tile
	}
}
//if it is a special tile owned by yourself, you just skip cause cant buy houses
private void special_tile_owned_by_you(int current_tile) {
	JOptionPane.showMessageDialog(null,"The tile is "+read_file(current_tile)[1]+" owned by you. No house can be bought","DuitRia",JOptionPane.PLAIN_MESSAGE);
	
}//pay the rent_price by reading the file again
private void special_tile_owned_by_others(int current_tile) {
	JOptionPane.showMessageDialog(null,"The tile is "+read_file(current_tile)[1]+"This place is owned "+owned_by(current_tile).name,"DuitRia",JOptionPane.PLAIN_MESSAGE);
	double rent_price=Double.parseDouble(read_file(current_tile)[2]);
	if(this.balance<rent_price) {
		JOptionPane.showMessageDialog(null,"You dont have enough money to pay rent. You lost","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.quit_game();
		return;
	}
	else {
		JOptionPane.showMessageDialog(null,"You lost "+rent_price+"M"+owned_by(current_tile).name+" gained "+rent_price+"M","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.balance-=rent_price;
		owned_by(current_tile).balance+=rent_price;
		return;
	}
}//check if it is a special tile or not
private boolean is_special_tiles(int current_tile) {
	for(int i=0;i<special_tiles.length;i++) {
		if(special_tiles[i]==current_tile)
			return true;
	}
	return false;
}//they will jump to prison tile, the injail boolean becomes true
public void jail() {
	this.injail=true;
	JOptionPane.showMessageDialog(null,"Your current tile is "+this.current_tile+".You are in jail now(jumped to prison now)","DuitRia",JOptionPane.PLAIN_MESSAGE);
	current_tile=prison_tile;
	labels_tiles.update_board();
	labels_tiles.update_player_info();
}
//checks wanna buy the tile or not
public void check_wanna_buy(int current_tile) {
	int answer=JOptionPane.showConfirmDialog(null,"The current tile name is: "+read_file(current_tile)[1]+
			"The price to buy the land is "+read_file(current_tile)[7]+"M"+
"Enter 1 to buy the land or 0 to skip: ","DuitRia",JOptionPane.YES_NO_OPTION);
    if(answer==0) {//check whether they got enough balance 
    	if(Double.parseDouble(read_file(current_tile)[7])>this.balance) {
    		JOptionPane.showMessageDialog(null,"Sorry your balance is not sufficient.","DuitRia",JOptionPane.PLAIN_MESSAGE);
    		return;
    	}//if enough buy it.update in tiles_owned
    	this.balance-=Double.parseDouble(read_file(current_tile)[7]);
    	this.tiles_owned.add(current_tile);
    	JOptionPane.showMessageDialog(null,"Congrats you are the owner of: "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
        return;
    }else {
    	return;
    }
}
//if it is owned by you, check whether if they wanna buy houses
public void owned_by_you(int current_tile) {
	if(houses_on_each_tile[current_tile]>=4) {
		JOptionPane.showMessageDialog(null,"You have edy bought 4 houses on this land","DuitRia",JOptionPane.PLAIN_MESSAGE);
		return;
	}else {
		int answer=JOptionPane.showConfirmDialog(null,"You own "+houses_on_each_tile[current_tile]+" on this land."+"Do you wanna buy more.","DuitRia",JOptionPane.YES_NO_OPTION);
		if(answer==0)
			buy_house(current_tile);
		else {
			return;
		}
	}
}//pay the rent
public void owned_by_others(int current_tile) {
	double rent_price;
	if(owned_by(current_tile).houses_on_each_tile[current_tile]!=0) {
		rent_price=Double.parseDouble(read_file(current_tile)[this.houses_on_each_tile[current_tile]+2]);
	}else {
		rent_price=Double.parseDouble(read_file(current_tile)[2]);
	}
	if(this.balance<rent_price) {
		JOptionPane.showMessageDialog(null,"You dont have enough money to pay rent. You lost","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.quit_game();
		return;
	}else {
		JOptionPane.showMessageDialog(null,"You lost "+rent_price+"M. "+owned_by(current_tile).name+" gained "+rent_price+"M","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.balance-=rent_price;
		owned_by(current_tile).balance+=rent_price;
		labels_tiles.update_player_info();
		return;
	}
		
}
public void buy_house(int current_tile) {
	//buying the houses
	int houses_wanna_buy=Integer.parseInt(JOptionPane.showInputDialog("Your account balance: "+this.balance+"M. "+
"How many houses you wanna buy? The total houses cant exceed 4 and each house costs 200k: "));
	if(houses_wanna_buy*0.2>this.balance) {
		JOptionPane.showMessageDialog(null,"Sorry insufficient money","DuitRia",JOptionPane.PLAIN_MESSAGE);
		}else {
		houses_on_each_tile[current_tile]+=houses_wanna_buy;
		this.balance-=houses_wanna_buy*0.2;
		labels_tiles.update_player_info();
	}
		
}
public void advance_to_go() {//go to the first tile and collect 2M
	this.current_tile=1;
	labels_tiles.update_board();
	labels_tiles.update_player_info();
}
public void birthday() {//get 0.1k from every player
	JOptionPane.showMessageDialog(null,"You can collect 0.1k from every player","DuitRia",JOptionPane.PLAIN_MESSAGE);
	for(int i=0;i<sorted_players.size();i++) {
		if(sorted_players.get(i).name.equals(this.name))
			break;
		else {
			sorted_players.get(i).balance-=0.1;
			this.balance+=0.1;
		}
	}
	for(int i=0;i<sorted_players.size();i++) {
		if(sorted_players.get(i).balance<0)
			sorted_players.get(i).quit_game();
	}
}
public void bank_error() {//get 2M from the bank
	this.balance+=2;
	labels_tiles.update_player_info();
}
public void advance_to_railroad() {//go to the nearest railroad
	if(Math.abs(this.current_tile-26)<Math.abs(this.current_tile-36))//check which is the nearest railroad
		this.current_tile=26;
	else
		this.current_tile=36;
	labels_tiles.update_board();
	if(owned_by(current_tile)!=null) {
		   if(owned_by(current_tile).name.equalsIgnoreCase(this.name)) {
			JOptionPane.showMessageDialog(null,"This tile is owned by u: "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
			   special_tile_owned_by_you(current_tile);
			   return;
		   }else {
			   JOptionPane.showMessageDialog(null,"This tile is owned by "+owned_by(current_tile).name +" : "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
			   special_tile_owned_by_others_2(current_tile);
			   return;
		   }
		}else {
			check_wanna_buy(current_tile);
		}
}
private void special_tile_owned_by_others_2(int current_tile) {
	JOptionPane.showMessageDialog(null,"The tile is "+read_file(current_tile)[1]+"This place is owned "+owned_by(current_tile).name,"DuitRia",JOptionPane.PLAIN_MESSAGE);
	double rent_price=2*Double.parseDouble(read_file(current_tile)[2]);
	if(this.balance<rent_price) {
		JOptionPane.showMessageDialog(null,"You dont have enough money to pay rent. You lost","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.quit_game();
		return;
	}
	else {
		JOptionPane.showMessageDialog(null,"You lost "+rent_price+"M. "+owned_by(current_tile).name+" gained "+rent_price+"M","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.balance-=rent_price;
		owned_by(current_tile).balance+=rent_price;
		labels_tiles.update_player_info();
		return;
	}
}
//go back 3 spaces
public void go_back_3() {
	this.current_tile=this.current_tile-3;
	if(this.current_tile<1)
		this.current_tile+=40;
	labels_tiles.update_board();
	labels_tiles.update_player_info();
	if(owned_by(current_tile)!=null) {
		   if(owned_by(current_tile).name.equalsIgnoreCase(this.name)) {
			JOptionPane.showMessageDialog(null,"This tile is owned by u: "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
			   special_tile_owned_by_you(current_tile);
			   return;
		   }else {
			   JOptionPane.showMessageDialog(null,"This tile is owned by "+owned_by(current_tile).name +" : "+read_file(current_tile)[1],"DuitRia",JOptionPane.PLAIN_MESSAGE);
			   special_tile_owned_by_others_2(current_tile);
			   return;
		   }
		}else {
			check_wanna_buy(current_tile);
		}
}
//go to jail
public void go_to_jail() {
	this.jail();
}
//pay 0.2M for every house u own on every tiles
public void general_repairs() {
	double sum_houses=0;
	for(int i=0;i<this.houses_on_each_tile.length;i++)
	      sum_houses+=this.houses_on_each_tile[i];
	double total_repairs=sum_houses*0.2; 
	if(this.balance<total_repairs) {
		JOptionPane.showMessageDialog(null,"You dont have enough money to pay for repairs. You lost","DuitRia",JOptionPane.PLAIN_MESSAGE);
		this.quit_game();
		return;
	}else {
		JOptionPane.showMessageDialog(null,"You lost "+total_repairs+"M","DuitRia",JOptionPane.PLAIN_MESSAGE);		this.balance-=total_repairs;
		return;
	}
		
}

public void hospital_fees() {
	this.balance-=0.25;//pay 0.25M
	if(this.balance<0)
		this.quit_game();
}
public void school_fees() {
	this.balance-=0.1;//pay 0.1M
	if(this.balance<0)
		this.quit_game();
}
public void speeding_fine() {
	this.balance-=0.1;//pay 0.1M
	if(this.balance<0)
		this.quit_game();
}

}







