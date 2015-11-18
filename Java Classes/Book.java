public class Book extends Readable {
  //...
  
  @Override
  public int getPrice(...) {
    //override to get the item price and add 2% (Environment Tax)
    //...
  }
}
