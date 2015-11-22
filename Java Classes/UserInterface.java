import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
	private Readable[] readables;
	private Audio[] audioProducts;
	private int currentPage = 1;    //the page number (P1..P10)
	private Scanner sc = new Scanner(System.in);
	private ShoppingCart[] users;
	
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
		boolean b = check_match(users, name);
		if (!b) {
			addLine("Users.txt", name);
			System.out.println("Username successfully added");
		}
		currentPage = 1;
	}

	public void P3(){
		System.out.println("Enter your username:");
		String name = sc.next();
		boolean b = check_match(users, name);
		if (b){
			System.out.println("Hello " + name);
			currentPage = 5;
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

	}

	public void P8(){
		System.out.println("Readables:\n");
		System.out.format("%6s%30s%16s%12s%20s%12s%s", "S.No",
				"Name of Book", "Author", "Price($)", "Quantity in Store", "Type", "\n");
		showReadables();
		System.out.println("\nChoose your option:" + "\nPress -1 to return to previous menu.");
		int i = sc.nextInt();
		if (i == -1)
			currentPage = 6;
		//ADD SOME BUYING STUFF HERE
		else {
			//error things here
		}
	}

	public void P9(){
		System.out.println("Audio:\n");
		System.out.format("%6s%30s%16s%12s%20s%12s%s", "S.No",
				"Name", "Artist", "Price($)", "Quantity in Store", "Type", "\n");
		showAudioProducts();
		System.out.println("\nChoose your option:" + "\nPress -1 to return to previous menu.");
		int i = sc.nextInt();
		if (i == -1)
			currentPage = 6;
		//ADD SOME BUYING STUFF HERE
		else {
			//error things here
		}
	}

	public void P10(){

	}
	
	public boolean check_match(ShoppingCart[] arr, String input){
		for (int i = 0; i < arr.length; i++) {
			if (input.equals(arr[i].getUsername()))
				return true;
		}
		return false;
	}
	
	public void addLine (String filename, String input) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.append(input);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String[] getFile(String filename){
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
}
