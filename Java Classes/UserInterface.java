import java.util.Scanner;

public class UserInterface {
  //private array readables;
  //private array audioProducts;
  private int currentPage = 1;;    //the page number (P1..P10)
  private Scanner sc = new Scanner(System.in);
  
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
  
  public void getReadables(){
	  
  }   //fetches all readables from the files and places them in the readables array
  
  public void getAudioProducts(){
	  //fetches all audio products from the files and places them in the readables array
  }
  
  public void showReadables(){
	  //displays all readables for browsing
  }
  
  public void showAudioProducts(){
	  //displays all audio products for browsing
  }
  
  // all pages
  
  public void P1(){
		System.out.println("1. Sign in\n2. Sign up\n\nChoose your option:");
		int i = sc.nextInt();
		if (i == 1)
			currentPage = 2;
		else if (i == 2)
			currentPage = 3;
		else{
			//Invalid input stuff
		}
  }
  
	public void P2(){
		System.out.println("Choose your username:");
		String name = sc.nextLine();
		System.out.println("Username successfully added");
		currentPage = 1;
	}
	
	public void P3(){
		System.out.println("Enter your username:");
		String name = sc.nextLine();
		// if name is in database
		// currentPage = 5
		// else currentPage 4
	}
  
	public void P4(){
		System.out.println("No Access");
		currentPage = 1;
	}
	
	public void P5(){
		System.out.println("1. View Items By Category\n2. View Shoppint Cart\n"
				+ "3. Sign Out\n\nChoose your option:");
		int i = Integer.parseInt(sc.nextLine());
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
		
	}
	
	public void P9(){
		
	}
	
	public void P10(){
		
	}
}
