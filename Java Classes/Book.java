public class Book extends Readable {
  //...
  
  @Override
  public int getPrice() {
	  return 0;
    //override to get the item price and add 2% (Environment Tax)
    //...
  }
}
