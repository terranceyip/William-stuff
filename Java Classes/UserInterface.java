/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Allows user to interact with the program.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class UserInterface {
	private Readable[] readables;					//array of books and ebooks
	private Audio[] audioProducts;					//array of CDs and MP3s
	private int currentPage = 1;    				//the page number (P1..P10)
	private Scanner sc = new Scanner(System.in);	//Scanner for input
	private ShoppingCart[] users;					//array of all users
	private boolean isAdmin = false;				//Used to determine whether user is Admin or not
	private ShoppingCart currentUser;				//currentUser used to refer to the current user

	public UserInterface(){
		//setup all arrays
		getUsers();				//sets up Users array by calling the getUsers() method
		getReadables();			//sets up Readables array by calling the getReadables() method
		getAudioProducts();		//sets up AudioProjects array by calling the getAudioProducts() method
	}

	public int getCurrentPage() {	//gets the current page number
		return currentPage;
	}

	public int changeCurrentPage() {
		//swaps each page after every loop
		switch (currentPage){
		case 1:
			P1();			//if current page is 1, go to P1 (page 1)
			break;
		case 2:
			P2();			//if currentPage = 2, go to P2
			break;
		case 3:
			P3();			//if currentPage = 3, go to P3
			break;
		case 4:
			P4();			//if currentPage = 4, go to P4
			break;
		case 5:
			P5();			//if currentPage = 5, go to P5
			break;
		case 6:
			P6();			//if currentPage = 6, go to P6
			break;
		case 7:
			P7();			//if currentPage = 7, go to P7
			break;
		case 8:
			P8();			//if currentPage = 8, go to P8
			break;
		case 9:
			P9();			//if currentPage = 9, go to P9
			break;
		case 10:
			P10();			//if currentPage = 10, go to P10
			break;
		case 11:
			P11();
			break;
		case 12:
			P12();
			break;
		}
		return 0;
	}

	public void getUsers(){
		//gets array of users
		String[] s = getFile("Users.txt");		//gets the Users.txt file
		users = new ShoppingCart[s.length];		//creates and sets the length of the users array
		for (int i = 0; i < s.length; i++){
			users[i] = new ShoppingCart(s[i]);	//add each username to the users array
		}
	}

	public void getReadables(){
		//fetches all readables from the files and places them in the readables array
		String[] books = getFile("Books.txt");		//gets the Books.txt file
		String[] ebooks = getFile("Ebooks.txt");	//gets to Ebooks.txt file
		readables = new Readable[books.length+ebooks.length];	//creates and sets the length of readables array
		for (int i = 0; i < books.length; i++) {
			readables[i] = new Book(books[i]);		//add each book to the readables array
		}
		for (int i = books.length; i < books.length + ebooks.length; i++) {
			readables[i] = new eBook(ebooks[i-books.length]);	//add each eBook to the readables array
		}
	}

	public void getAudioProducts(){
		//fetches all audio products from the files and places them in the audioProducts array
		String[] cds = getFile("CDs.txt");		//gets the CDs.txt file
		String[] mp3s = getFile("MP3.txt");		//gets the MP3.txt file
		audioProducts = new Audio[cds.length+mp3s.length];	//creates and sets the length of audioProducts array
		for (int i = 0; i < cds.length; i++) {
			audioProducts[i] = new CD(cds[i]);	//add each CD to the audioProducts array
		}
		for (int i = cds.length; i < cds.length + mp3s.length; i++){
			audioProducts[i] = new MP3(mp3s[i-cds.length]);	//add each MP3 to the audioProducts array
		}
	}	

	public void showUsers(){						//method for showing all Users
		for (ShoppingCart sc: users)				//loops through the users array,
			System.out.println(sc.getUsername());	//and prints all users.
	}

	public void showReadables(){					//method for showing all Readables
		for (Readable r: readables) {				//loops through the readables array,
			r.printInfo();							//and prints all readable items.
		}
	}

	public void showAudioProducts(){				//method for showing all Audio Products
		for (Audio a: audioProducts)				//loops through the audioProducts array,
			a.printInfo();							//and prints all audio products.
	}


	//ALL PAGES

	public void P1(){			//Method for page 1
		System.out.println("1. Sign in\n2. Sign up\n\nChoose your option:");	//prints text to screen
		int i = sc.nextInt();	//scanner to interpret input from user
		if (i == 1)
			currentPage = 3;	//if user inputs 1, changes current page to 3
		else if (i == 2)
			currentPage = 2;	//if user inputs 2, changes current page to 2
		else {					//if user does not input 1 or 2,
			System.out.println("Invalid input.\n");		//prints "Invalid input."
			currentPage = 1;	//then refreshes the current page (which is 1).
		}
	}

	public void P2(){			//Method for page 2 (sign up)
		System.out.println("Choose your username:");		//prints "Choose your username:"
		String name = sc.next();		//scanner to interpret username entered by user
		currentUser = checkUser(users, name); //checks input against registered users
		if (currentUser == null) {		//if the username doesn't already exist,
			addLine("Users.txt", name);	//then add the username to Users.txt
			System.out.println("Username successfully added");	//prints that the username has been successfully added
			getUsers();					//updates the users array
		}
		else {							//if username already exists,
			System.out.println("Username already in use");	//tells the user that the username already exists
		}
		currentPage = 1;		//brings user back to previous menu
	}

	public void P3(){			//Method for page 3 (sign in)
		isAdmin = false;
		System.out.println("Enter your username:");		//prints "Enter your username:" to screen
		String name = sc.next();		//scanner to interpret user input
		currentUser = checkUser(users, name);	//checks input against registered users
		if (currentUser != null){				//if username entered is an existing username,
			currentUser = setupUser(currentUser);	//then sets up currentUser.

			//admin checks
			if (name.equals("ADMIN")){			//if the username entered is ADMIN
				System.out.println("Enter your password:");
				String pw = sc.next();			//sets up scanner for user input of the admin password
				if (pw.equals(currentUser.getPassword())){		//if password entered is correct,
					isAdmin = true;				//then sets isAdmin to true.
					System.out.println("Hello " + name);
					currentPage = 5;			//moves user to page 5.
				}
				else
					currentPage = 4;			//if password entered is invalid, move user to page 4
			}
			else {								//if username entered exists and is not ADMIN,
				System.out.println("Hello " + name);
				currentPage = 5;				//moves user to page 5.
			}
		}
		else
			currentPage = 4;					//if username entered does not exist, move user to page 4
	}

	public void P4(){			//Method for page 4
		System.out.println("No Access");		//prints "No Access" to screen
		currentPage = 1;		//moves user to page 1
	}

	public void P5(){			//Method for page 5
		if(isAdmin){
			System.out.println("1. View Items By Category\n2. View Shopping Cart\n"
					+ "3. Sign Out\n4. Change Password\n\nChoose your option:");
			int i = sc.nextInt();
			if (i == 1)
				currentPage = 6;
			else if (i == 2)
				currentPage = 7;
			else if (i == 3)
				currentPage = 1;
			else if (i == 4){
				System.out.println("Enter your new password:");
				String pw = sc.next();
				for (int j = 0; j < users.length; j++){
					if(users[j].getUsername().equals("ADMIN")){
						users[j].setPassword(pw);
						String[] s = getFile("Users.txt");
						for (int k = 0; k < s.length; k++){
							if (s[k].matches("ADMIN, .*"))
								s[k] = "ADMIN, " + pw;
						}
						setFile("Users.txt", s);
					}
				}
				System.out.println("You're password has be changed");
				currentPage = 5;
			}
			else{

			}
		}
		else{
			System.out.println("1. View Items By Category\n2. View Shopping Cart\n"
					+ "3. Sign Out\n\nChoose your option:");
			int i = sc.nextInt();		//scanner to interpret user input
			if (i == 1)
				currentPage = 6;		//if user inputs 1, changes current page to 6
			else if (i == 2)
				currentPage = 7;		//if user inputs 2, changes current page to 7
			else if (i == 3)
				currentPage = 1;		//if user inputs 3, changes current page to 1
			else {						//if user does not input 1, 2, or 3,
				System.out.println("Invalid input.\n");		//prints "Invalid input"
				currentPage = 5;		//refreshes the current page (page 5)
			}
		}
	}

	public void P6(){			//Method for page 6

		if (isAdmin){
			System.out.println("1. Readables\n2. Audio\n3. Sort Items\n4. Add Item\n\nChoose your option\n\n"
					+ "Press -1 to return to the previous menu");
			int i = sc.nextInt();		//scanner to interpret user input
			if (i == 1)
				currentPage = 8;		//if user inputs 1, changes current page to 8
			else if (i == 2)
				currentPage = 9;		//if user inputs 2, changes current page to 9
			else if (i == 3)
				currentPage = 11;
			else if (i == 4)
				currentPage = 12;
			else if (i == -1)
				currentPage = 5;		//if user inputs -1, changes current page to 5 (back to previous menu)
			else{						//if user does not input 1, 2, or -1,
				System.out.println("Invalid input.\n");		//prints "Invalid input"
				currentPage = 6;		//refreshes the current page (page 6)
			}
			//SORT TO PAGE 11, ADD TO PAGE 12
			/**************ADD PAGE CHANGES OIAESHFOIA;WEJRHOAIWERH*************/
		}

		else{
			System.out.println("1. Readables\n2. Audio\n\nChoose your option\n\n"
					+ "Press -1 to return to the previous menu");
			int i = sc.nextInt();		//scanner to interpret user input
			if (i == 1)
				currentPage = 8;		//if user inputs 1, changes current page to 8
			else if (i == 2)
				currentPage = 9;		//if user inputs 2, changes current page to 9
			else if (i == -1)
				currentPage = 5;		//if user inputs -1, changes current page to 5 (back to previous menu)
			else{						//if user does not input 1, 2, or -1,
				System.out.println("Invalid input.\n");		//prints "Invalid input"
				currentPage = 6;		//refreshes the current page (page 6)
			}
		}
	}

	public void P7(){			//Method for page 7
		//shows shopping cart
		String[] s = getFile("Cart_" + currentUser.getUsername() + ".txt"); //gets shopping cart txt file for current user
		if (s == null)// if nothing is in the cart
			System.out.println("There is nothing in your cart\n");	//tells user there's nothing in their cart
		else if (s != null) {				//if cart is not empty,
			for (String str: s)
				System.out.println(str);	//prints the contents in the user's cart txt file
		}
		System.out.println("\nPress -1 to go back to previous menu");
		int i = sc.nextInt();	//scanner to interpret user input
		if (i == -1)
			currentPage = 5;	//if user inputs -1, changes current page to 5
		else {					//if user does not input -1,
			System.out.println("Invalid input.\n");	//prints "Invalid input"
			currentPage = 7;	//refreshes the current page (page 7)
		}
	}

	public void P8(){			//Method for page 8
		//prints readables to screen
		System.out.println("Readables:\n");
		System.out.format("%6s%30s%16s%12s%20s%12s%s", "S.No",
				"Name of Book", "Author", "Price($)", "Quantity in Store", "Type", "\n");
		showReadables();
		System.out.println("\nChoose your option:" + "\nPress -1 to return to previous menu.");
		int i = sc.nextInt();		//scanner to interpret user input
		Item currentItem = checkItem(readables, i);	//check input against all items
		if (i == -1)
			currentPage = 6;			//if user inputs -1, changes current page to 6
		else if (currentItem != null){ 	//if the item exists
			System.out.println("Enter a quantity:");	//asks user for the quantity they want to buy
			i = sc.nextInt();			//scanner to interpret user input
			if (i <= 0){				//if the quantity is <= 0,
				System.out.println("Invalid input.\n");	//prints "Invalid input."
				currentPage = 8;		//refreshes current page
			}
			else if (currentItem.quantity >= i){	//if the item stock is greater or equal to quantity user wants to buy,
				//add item to shopping cart.
				currentUser.addItem(currentItem.getSNo(), currentItem.getName(), i, currentItem.getPrice(), currentItem.getShipping());
				System.out.println(i + " " + currentItem.getName() + " " +
						currentItem.getType() + "s successfully added to your cart.");
				System.out.println("\nPress -2 to Continue Shopping or Press 0 to Checkout:");
				i = sc.nextInt();	//scanner to interpret user input
				if (i == -2)
					currentPage = 6;	//if user inputs -2, changes current page to 6
				else if (i == 0)
					currentPage = 10;	//if user inputs 0, changes current page to 10
				else {
					System.out.println("Invalid input.\n");	//prints "Invalid input."
					currentPage = 8;		//refreshes current page
				}
			}
			else {			//if there quantity > stock,
				System.out.println("There is not enough in stock.\n");	//tells user there is not enough in stock
				currentPage = 8;		//refreshes current page
			}
		}
	}

	public void P9(){				//Method for page 9
		//prints Audio products to screen
		System.out.println("Audio:\n");
		System.out.format("%6s%30s%16s%12s%20s%12s%s", "S.No",
				"Name", "Artist", "Price($)", "Quantity in Store", "Type", "\n");
		showAudioProducts();
		System.out.println("\nChoose your option:" + "\nPress -1 to return to previous menu.");
		int i = sc.nextInt();	//scanner to interpret user input
		Item currentItem = checkItem(audioProducts, i); //check input against all items
		if (i == -1 || i == -2)		//if user inputs -1 or -2,
			currentPage = 6;		//moves user to page 6.
		else if (currentItem != null){ //if the item exists,
			System.out.println("Enter a quantity:");	//prints "Enter a quantity:"
			i = sc.nextInt();		//scanner to interpret user input
			if (i <= 0){			//if quantity is <= 0,
				System.out.println("Invalid input.\n");		//prints "Invalid input."
				currentPage = 9;	//refreshes current page
			}
			else if (currentItem.quantity >= i){	//if stock > quantity,
				//add item to shopping cart
				currentUser.addItem(currentItem.getSNo(), currentItem.getName(), i, currentItem.getPrice(), currentItem.getShipping());
				System.out.println(i + " " + currentItem.getName() + " " +
						currentItem.getType() + "s successfully added to your cart.");
				System.out.println("\nPress -2 to Continue Shopping or Press 0 to Checkout:");
				i = sc.nextInt();
				if (i == -2)
					currentPage = 6;
				else if (i == 0)
					currentPage = 10;
				else {
					System.out.println("Invalid input.\n");		//prints "Invalid input."
					currentPage = 9;	//refreshes current page
				}
			}
			else {			//if quantity > stock
				System.out.println("There is not enough is stock.\n");	//tells user there is not enough in stock
				currentPage = 8;		//refreshes current page
			}
		}
	}

	public void P10(){		//Method for page 10
		String[] s = getFile("Cart_" + currentUser.getUsername() + ".txt"); //gets shopping cart .txt file of user
		int[][] items = new int[s.length][3];	//used to check sNo, quantity, and price of items in the shopping cart
		int totalCost = 0;			//totalCost holds the total cost
		int shippingCost = 0;		//shippingCost holds the final shipping cost
		int envTaxCost = 0;			//envTaxCost holds the total environment tax
		Item currentItem;			//creates variable currentItem for the item currently being looked at
		boolean canPurchase = true;	//tells whether or not the item can be purchased

		for (int i = 0; i < s.length; i++) {	//for each item in the cart
			String[] temp = s[i].split(", ");	//saves shopping cart things in an string array temp
			currentItem = checkItem(readables, Integer.parseInt(temp[0])); //check if item is a readable
			if (currentItem == null) currentItem = checkItem(audioProducts, Integer.parseInt(temp[0])); //if not, the item is an audio
			items[i][0] = Integer.parseInt(temp[0]);	//saves sNo in first element of items
			items[i][1] = Integer.parseInt(temp[3]);	//saves quantity in second element of items
			items[i][2] = currentItem.getPrice();		//saves price in third element of items

			if (currentItem.getQuantity() >= items[i][1]){			//if stock >= quantity user wants to buy,
				totalCost += currentItem.getPrice()*items[i][1];	//adds price*quantity of currentItem to totalCost
				shippingCost += currentItem.getShipping()*items[i][1]; //adds shipping*quantity of currentItem to shippingCost
				envTaxCost += currentItem.getEnvTax()*items[i][1];
			}
			else {						//cannot purchase, exit
				System.out.println("Not enough in stock. Purchase invalid.\n");
				canPurchase = false;	//sets canPurchase to false, telling us the item is not purchasable
				currentPage = 5;		//moves user to page 5
				break;
			}
		}

		if (canPurchase){			//if item is purchasable,
			//prints bill.
			System.out.println("Billing Information:");
			System.out.format("%40s%8s%20s%15s", "Name", "", "Quantity", "Price\n\n");
			String[] contents = currentUser.getContent();	//contents = shopping cart contents of the current user
			for (int i = 0; i < contents.length; i++) {
				String line = contents[i];					//line = current item being looked at
				String[] arr = line.split(", ");
				System.out.format("%40s%8s%20s%15s", arr[1], "", arr[3], items[i][2]+"\n");	//prints Name, Quantity, and Price
			}
			int total = (int) (totalCost*1.13 + shippingCost + envTaxCost);
			System.out.println("\n");
			System.out.format("%40s%8s%20s%15s", "Environment Tax", "2%", "", envTaxCost + "\n");	//prints Environment Tax
			System.out.format("%40s%8s%20s%15s", "HST", "13%", "", (int) (totalCost*0.13) + "\n\n");	//prints HST
			System.out.format("%40s%8s%20s%15s", "Shipping", "10%", "", shippingCost + "\n");		//prints Shipping
			System.out.format("%40s%8s%20s%15s", "", "", "", "____________\n");
			System.out.format("%40s%8s%20s%15s", "Total:", "", "", total + "\n\n");	//prints the total
			System.out.println("Are you sure you want to pay? yes or no. ");	//asks user for confirmation on purchases
			String yes = sc.next();		//scanner to interpret user input

			if (yes.equalsIgnoreCase("yes")) {	//if user inputs yes (ignores case sensitivity)
				//Get unique ID
				String[] id = getFile("ItemsBought.txt");
				String currentID = null;
				if (id == null){
					currentID = "U1000";
					writeLine("ItemsBought.txt", currentID);
				}
				else{
					currentID = "U" + (1000 + id.length);
					addLine("ItemsBought.txt", currentID);
				}
				System.out.println("\nConfirmation ID: " + currentID);	//prints the confirmation ID
				System.out.println("Items Shipped to: " + currentUser.getUsername());	//prints who the items have been shipped to

				//set quantities in arrays
				for (int i = 0; i < s.length; i++){
					for (int j = 0; j < readables.length; j++){
						if (items[i][0] == readables[j].getSNo()){			//if the sNo matches sNo of a readable,
							readables[j].subtractQuantity(items[i][1]);
						}
						else if (items[i][0] == audioProducts[j].getSNo()){
							audioProducts[j].subtractQuantity(items[i][1]);
						}
					}
				}

				//set quantities in files
				setFileByType("CDs.txt", audioProducts, "CD");
				setFileByType("MP3.txt", audioProducts, "MP3");
				setFileByType("Ebooks.txt", readables, "eBook");
				setFileByType("Books.txt", readables, "Book");

				// removes current user's shopping cart
				BufferedWriter out;
				try {
					out = new BufferedWriter(new FileWriter("Cart_" + currentUser.getUsername() + ".txt"));
					out.write("");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				currentPage = 1;
			}
			else {
				currentPage = 5;
			}
		}
	}

	//sorting page
	public void P11(){
		System.out.println("1. Sort items by price\n2. Sort items by name\n\n"
				+ "Choose your option\n\nPress -1 to return to the previous menu");
		int i = sc.nextInt();
		if (i == 1)
			sortByPrice();
		else if (i == 2)
			sortByName();
		else if (i == -1)
			currentPage = 6;
		else{
			//error things
		}
	}

	//adding page
	public void P12(){
		String file = null;
		String sNo = null;
		String name = null;
		String person = null;
		String price = null;
		String quantity = null;

		System.out.println("1. Add CD\n2. Add MP3\n3. Add Book\n4. Add eBook\n\n"
				+ "Choose your option\n\nPress -1 to return to the previous menu");
		int i = sc.nextInt();
		if (i == -1)
			currentPage = 6;
		else if (i == 1)
			file = "CDs.txt";
		else if (i == 2)
			file = "MP3.txt";
		else if (i == 3)
			file = "Books.txt";
		else if (i == 4)
			file = "Ebooks.txt";
		else{
			//ERROR CRAP
		}
		boolean flag = true;
		do{
			System.out.println("Enter the sNo: ");
			sNo = sc.next();
			flag = !checkSNo(sNo);
			if (flag)
				System.out.println("sNo already exists. Try again.");
		} while (flag);
		System.out.println("Enter the title:");
		name = sc.next();
		if (i == 1 || i == 2)
			System.out.println("Enter the artist:");
		else
			System.out.println("Enter the author:");
		person = sc.next();
		System.out.println("Enter the price:");
		price = sc.next();
		System.out.println("Enter the quantity");
		quantity = sc.next();
		String s = sNo + ", " + name + ", " + person + ", " + price + ", " + quantity;
		addLine(file, s);
		getReadables();
		getAudioProducts();
		System.out.println("Item has been added");
		currentPage = 6;
	}

	private boolean checkSNo(String sNo) {
		boolean flag = true;
		for (Readable r: readables){
			if (Integer.parseInt(sNo) == r.getSNo())
				flag = false;
		}
		for (Audio a: audioProducts){
			if (Integer.parseInt(sNo) == a.getSNo())
				flag = false;
		}
		return flag;
	}

	//checks input against registered users
	public ShoppingCart checkUser(ShoppingCart[] arr, String input){
		for (int i = 0; i < arr.length; i++) {
			if (input.equals(arr[i].getUsername()))
				return arr[i];
		}
		return null;
	}

	//check sNO against items in array
	public Item checkItem(Item[] arr, int sNo){
		for (int i = 0; i < arr.length; i++) {
			if (sNo == (arr[i].getSNo()))
				return arr[i];
		}
		return null;
	}
	
	//start a file with input
	public void writeLine (String filename, String input) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.write(input);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//add add a new line containing input
	public void addLine (String filename, String input) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.newLine();
			out.append(input);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	//returns an array of strings holding each line of a file
	public static String[] getFile(String filename){
		BufferedReader in;
		String text = "";
		try {
			in = new BufferedReader(new FileReader(filename));
			String textbuffer = in.readLine();
			if (textbuffer == null) // if file is empty, return null
				return null;
			text = text.concat(textbuffer);
			while ((textbuffer = in.readLine()) != null) {
				text = text.concat("\n" + textbuffer);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return text.split("\n");
	}

	//rewrites a file from a given array, sorted by type
	public static void setFileByType(String filename, Item[] items, String type){
		int size = items.length;
		BufferedWriter out;
		String text = "";
		try {
			out = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < size; i++){
				if (items[i].getType().equals(type)){ //if the item matches the given type
					text = text.concat("\n" + items[i].getInfo()); // write it in
				}
			}
			text = text.trim(); //removes \n from the end
			out.write(text);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//setup shopping cart after relogging in
	public ShoppingCart setupUser (ShoppingCart user) {
		String cartName = "Cart_"+user.getUsername()+".txt";
		File f = new File(cartName);
		if (f.exists()) { //if file exists, add items to shoppingCart
			user.setContents(getFile("Cart_"+user.getUsername()+".txt"));
		}
		else { //make a new file
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return user;
	}

	public static String[][] getAllInfo(){
		String[] cd = getFile("CDs.txt");
		String[] mp3 = getFile("MP3.txt");
		String[] books = getFile("Books.txt");
		String[] ebooks = getFile("Ebooks.txt");
		int size = cd.length + mp3.length + books.length + ebooks.length;
		String[][] allInfo = new String[size][];
		for (int i = 0; i < cd.length; i++){
			allInfo[i] = cd[i].split(", ");
		}
		for (int j = cd.length; j < cd.length + mp3.length; j++){
			allInfo[j] = mp3[j-cd.length].split(", ");
		}
		for (int k = cd.length + mp3.length; k < size - ebooks.length; k++){
			allInfo[k] = books[k - cd.length - mp3.length].split(", ");
		}
		for (int l = size - ebooks.length; l < size; l++){
			allInfo[l] = ebooks[l - cd.length - mp3.length - books.length].split(", ");
		}
		return allInfo;
	}

	public static void sortByPrice(){
		String[][] ss = getAllInfo();
		String[] s = new String[ss.length];
		Arrays.sort(ss, new Comparator<String[]>(){
			public int compare(String[] entry1, String[] entry2) {
				int price1 = Integer.parseInt(entry1[3]);
				int price2 = Integer.parseInt(entry2[3]);
				if(price1 > price2)
					return 1;
				else 
					return -1;
			}
		});
		for (int i = 0; i < ss.length; i++){
			s[i] = ss[i][0] + ", " + ss[i][1] + ", " + ss[i][2] + ", " + ss[i][3] + ", " + ss[i][4];
		}

		System.out.println();
		for (String st: s)
			System.out.println(st);
		System.out.println();
	}

	public static void sortByName(){
		String[][] ss = getAllInfo();
		String[] s = new String[ss.length];
		Arrays.sort(ss, new Comparator<String[]>(){
			public int compare(String[] entry1, String[] entry2) {
				String title1 = entry1[1];
				String title2 = entry2[1];
				return title1.compareTo(title2);
			}
		});
		for (int i = 0; i < ss.length; i++){
			s[i] = ss[i][0] + ", " + ss[i][1] + ", " + ss[i][2] + ", " + ss[i][3] + ", " + ss[i][4];
		}

		System.out.println();
		for (String st: s)
			System.out.println(st);
		System.out.println();
	}
}
