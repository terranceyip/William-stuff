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
	private Readable[] readables;
	private Audio[] audioProducts;
	private int currentPage = 1;    //the page number (P1..P10)
	private Scanner sc = new Scanner(System.in);
	private ShoppingCart[] users;
	private boolean isAdmin = false;
	private ShoppingCart currentUser;
	
	public UserInterface(){
		getUsers();
		getReadables();
		getAudioProducts();
	}

	public int getCurrentPage() {
		return currentPage;
		//this method is for page navigation. Based on the value of the state variable, call different pages
	}

	public int changeCurrentPage() {
		switch (currentPage){
		case 1:
			P1();
			break;
		case 2:
			P2();
			break;
		case 3:
			P3();
			break;
		case 4:
			P4();
			break;
		case 5:
			P5();
			break;
		case 6:
			P6();
			break;
		case 7:
			P7();
			break;
		case 8:
			P8();
			break;
		case 9:
			P9();
			break;
		case 10:
			P10();
			break;
		}
		return 0;
		//This method is for page navigation. It should change to current page and show the content.
	}

	public void getUsers(){
		String[] s = getFile("Users.txt");
		users = new ShoppingCart[s.length];
		for (int i = 0; i < s.length; i++){
			users[i] = new ShoppingCart(s[i]);
		}
	}
	
	public void getReadables(){
		String[] books = getFile("Books.txt");
		String[] ebooks = getFile("Ebooks.txt");
		readables = new Readable[books.length+ebooks.length];
		for (int i = 0; i < books.length; i++){
			readables[i] = new Book(books[i]);
		}
		for (int i = books.length; i < books.length + ebooks.length; i++){
			readables[i] = new eBook(ebooks[i-books.length]);
		}
	}   //fetches all readables from the files and places them in the readables array

	public void getAudioProducts(){
		String[] cds = getFile("CDs.txt");
		String[] mp3s = getFile("MP3.txt");
		audioProducts = new Audio[cds.length+mp3s.length];
		for (int i = 0; i < cds.length; i++){
			audioProducts[i] = new CD(cds[i]);
		}
		for (int i = cds.length; i < cds.length + mp3s.length; i++){
			audioProducts[i] = new MP3(mp3s[i-cds.length]);
		}
	}	//fetches all audio products from the files and places them in the readables array

	public void showUsers(){
		for (ShoppingCart sc: users)
			System.out.println(sc.getUsername());
	}
	
	public void showReadables(){
		for (Readable r: readables) {
			r.printInfo();
		}
		//displays all readables for browsing
	}

	public void showAudioProducts(){
		for (Audio a: audioProducts)
			a.printInfo();
		//displays all audio products for browsing
	}

	// all pages

	public void P1(){
		System.out.println("1. Sign in\n2. Sign up\n\nChoose your option:");
		int i = sc.nextInt();
		if (i == 1)
			currentPage = 3;
		else if (i == 2)
			currentPage = 2;
		else {
			//Invalid input stuff
		}
	}

	public void P2(){
		System.out.println("Choose your username:");
		String name = sc.next();
		currentUser = checkUser(users, name);
		if (currentUser == null) {
			addLine("Users.txt", name);
			System.out.println("Username successfully added");
			getUsers();
		}
		else{
			System.out.println("Username already in use");
		}
		currentPage = 1;
	}

	public void P3(){
		isAdmin = false;
		System.out.println("Enter your username:");
		String name = sc.next();
		currentUser = checkUser(users, name);
		if (currentUser != null){
			currentUser = setupUser(currentUser);
			if (name.equals("ADMIN")){
				System.out.println("Enter your password:");
				String pw = sc.next();
				if (pw.equals("ADMIN")){
					isAdmin = true;
					System.out.println("Hello " + name);
					currentPage = 5;
				}
				else
					currentPage = 4;
			}
			else{
				System.out.println("Hello " + name);
				currentPage = 5;
			}
		}
		else 
			currentPage = 4;
	}

	public void P4(){
		System.out.println("No Access");
		currentPage = 1;
	}

	public void P5(){
		System.out.println("1. View Items By Category\n2. View Shopping Cart\n"
				+ "3. Sign Out\n\nChoose your option:");
		int i = sc.nextInt();
		if (i == 1)
			currentPage = 6;
		else if (i == 2)
			currentPage = 7;
		else if (i == 3)
			currentPage = 1;
		else{
			// invalid input stuff
		}
	}

	public void P6(){
		System.out.println("1. Readables\n2. Audio\n\nChoose your option\n\n"
				+ "Press -1 to return to the previous menu");
		int i = sc.nextInt();
		if (i == 1)
			currentPage = 8;
		else if (i == 2)
			currentPage = 9;
		else if (i == -1)
			currentPage = 5;
		else{
			//error things here
		}
	}

	public void P7(){
		String[] s = getFile("Cart_" + currentUser.getUsername() + ".txt");
		if (s != null){
			for (String str: s)
				System.out.println(str);
		}
		currentPage = 6;
	}

	public void P8(){
		System.out.println("Readables:\n");
		System.out.format("%6s%30s%16s%12s%20s%12s%s", "S.No",
				"Name of Book", "Author", "Price($)", "Quantity in Store", "Type", "\n");
		showReadables();
		System.out.println("\nChoose your option:" + "\nPress -1 to return to previous menu.");
		int i = sc.nextInt();
		Item currentItem = checkItem(readables, i);
		if (i == -1 || i == -2)
			currentPage = 6;
		else if (currentItem != null){
			System.out.println("Enter a quantity:");
			i = sc.nextInt();
			if (currentItem.quantity >= i){
				currentUser.addItem(currentItem.getSNo(), currentItem.getName(), i, currentItem.getPrice(), currentItem.getShipping());
			}
			else{
				System.out.println("There is not enough is stock");
				currentPage = 8;
			}
		}
		else if (i == 0){
			currentPage = 10;
		}
		else{
			currentPage = 8;
		}
	}

	public void P9(){
		System.out.println("Audio:\n");
		System.out.format("%6s%30s%16s%12s%20s%12s%s", "S.No",
				"Name", "Artist", "Price($)", "Quantity in Store", "Type", "\n");
		showAudioProducts();
		System.out.println("\nChoose your option:" + "\nPress -1 to return to previous menu.");
		int i = sc.nextInt();
		Item currentItem = checkItem(audioProducts, i);
		if (i == -1 || i == -2)
			currentPage = 6;
		else if (currentItem != null){
			System.out.println("Enter a quantity:");
			i = sc.nextInt();
			if (currentItem.quantity >= i){
				currentUser.addItem(currentItem.getSNo(), currentItem.getName(), i, currentItem.getPrice(), currentItem.getShipping());
			}
			else{
				System.out.println("There is not enough is stock");
				currentPage = 8;
			}
		}
		else if (i == 0){
			currentPage = 10;
		}
		else{
			currentPage = 9;
		}
	}

	public void P10(){
		String[] s = getFile("Cart_" + currentUser.getUsername() + ".txt");
		int[][] items = new int[s.length][2];
		int totalCost = 0;
		int shippingCost = 0;
		Item currentItem;
		boolean canPurchase = true;
		
		for (int i = 0; i < s.length; i++){
			String[] temp = s[i].split(", ");
			items[i][0] = Integer.parseInt(temp[0]);
			items[i][1] = Integer.parseInt(temp[3]);
			currentItem = checkItem(readables, items[i][0]);
			if (currentItem == null) currentItem = checkItem(audioProducts, items[i][0]);
			
			if (currentItem.getQuantity() >= items[i][1]){
				totalCost += currentItem.getPrice()*items[i][1];
				shippingCost += currentItem.getShipping()*items[i][1];
			}
			else{
				System.out.println("Not enough in stock. Purchase invalid");
				canPurchase = false;
				break;
			}
		}
		
		if (canPurchase){
			//print prices stuff thing
			
			//remove quantities from files
			for (int i = 0; i < s.length; i++){
				for (int j = 0; j < readables.length; j++){
					if (items[i][0] == readables[i].getSNo()){
						readables[i].subtractQuantity(items[i][1]);
					}
				}
				for (int j = 0; j < audioProducts.length; j++){
					if (items[i][0] == audioProducts[i].getSNo()){
						audioProducts[i].subtractQuantity(items[i][1]);
					}
				}
			}
			setFileByType("CDs.txt", audioProducts, "CD");
			setFileByType("MP3.txt", audioProducts, "MP3");
			setFileByType("Ebooks", readables, "eBook");
			setFileByType("Books.txt", readables, "Book");
			
			try{
				File f = new File("Cart_" + currentUser.getUsername() + ".txt");
				f.delete();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		currentPage = 1;
	}
	
	public ShoppingCart checkUser(ShoppingCart[] arr, String input){
		for (int i = 0; i < arr.length; i++) {
			if (input.equals(arr[i].getUsername()))
				return arr[i];
		}
		return null;
	}
	
	public Item checkItem(Item[] arr, int input){
		for (int i = 0; i < arr.length; i++) {
			if (input == (arr[i].getSNo()))
				return arr[i];
		}
		return null;
	}
	
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
	
	public static String[] getFile(String filename){
		BufferedReader in;
		String text = "";
		try {
			in = new BufferedReader(new FileReader(filename));
			String textbuffer = in.readLine();
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

	public static void setFileByType(String filename, Item[] items, String type){
		int size = items.length;
		BufferedWriter out;
		String temp1 = null;
		String temp2 = null;
		try {
			out = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < size-1; i++){
				temp2 = temp1;
				if (items[i].getType().equals(type)){
					temp1 = items[i].getInfo();
					if (temp2 != null){
						out.write(temp2);
					}
				}
			}
			out.write(temp1);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public ShoppingCart setupUser (ShoppingCart user) {
		String cartName = "Cart_"+user.getUsername()+".txt";
		File f = new File(cartName);
		if (f.exists()) {
			user.setContents(getFile("Cart_"+user.getUsername()+".txt"));
		}
		else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public static void sortByPrice(String filename){
		String[] s = getFile(filename);
		String[][] ss = new String [s.length][];
		for (int i = 0; i<s.length; i++){
			ss[i] = s[i].split(", ");
		}
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
		setFile(filename, s);
	}

	public static void sortByName(String filename){
		String[] s = getFile(filename);
		String[][] ss = new String [s.length][];
		for (int i = 0; i<s.length; i++){
			ss[i] = s[i].split(", ");
		}
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
		setFile(filename, s);
	}
}
