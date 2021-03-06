/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Holds methods for the shopping cart.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShoppingCart extends User {

	private String[] content = null; //array of items
	
	public ShoppingCart(String name) {
		super(name);
	}

	public String[] getContent() {
		return content;
		//return the content of the shopping cart
	}
	
	public void setContents(String[] s){
		content = s;
	}

	public void addItem(int sNo, String name, int quantity, int price, int shipping) {
		//get date
		DateFormat df = new SimpleDateFormat("dd/MM/yyy");
		Date date = new Date();
		String sDate = df.format(date);
		
		String filename = "Cart_" + username + ".txt";
		boolean doesExist = false;
		String[] s = getFile(filename);
		if (s != null){ //if file is not empty
			for (int i = 0; i < s.length; i++){
				if(s[i].matches(sNo + ", .*")){ //if the item is already in the cart, only change the quantity
					doesExist = true;
					String[] temp = s[i].split(", ");
					int q = Integer.parseInt(temp[3]) + quantity;
					temp[3] = q + "";
					s[i] = temp[0] + ", " + temp[1] + ", " + temp[2] + ", " + temp[3];
				}
			}
		}
		if (doesExist) //rewrite file
			setFile(filename, s);
		else{ //append new item
			addLine(filename, sNo + ", " + name + ", " + sDate + ", " + quantity, s != null);
		}
		content = getFile(filename);
	}
	
	//returns an array of strings holding each line of a file
	public String[] getFile(String filename){
		BufferedReader in;
		String text = "";
		try {
			in = new BufferedReader(new FileReader(filename));
			String textbuffer = in.readLine();
			if (textbuffer == null)
				return null;
			text = text.concat(textbuffer);
			while ((textbuffer = in.readLine()) != null) {
				text = text.concat("\n" + textbuffer);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return text.split("\n");
	}
	
	//add add a new line containing input
	public void addLine (String filename, String input, boolean start) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			if (start)
				out.newLine();
			out.write(input);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//rewrite a file given an array of strings
	public static void setFile(String filename, String[] s){
		int size = s.length;
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < size-1; i++){
				out.write(s[i]);
				out.newLine();
			}
			out.write(s[size-1]);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
