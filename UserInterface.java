public class UserInterface {
  private array readables;
  private array audioProducts;
  private int currentPage;    //the page number (P1..P10)
  
  public int getCurrentPage(...) {
    //this method is for page navigation. Based on the value of the state variable, call different pages
  }
  
  public int changeCurrentPage(...) {
    //This method is for page navigation. It should change to current page and show the content.
  }
  
  public void getReadables();   //fetches all readables from the files and places them in the readables array
  
  public void getAudioProducts(); //fetches all audio products from the files and places them in the readables array
  
  public void showReadables();  //displays all readables for browsing
  
  public void showAudioProducts();  //displays all audio products for browsing
  //...
}
