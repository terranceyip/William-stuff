public class Audio extends Item {
  protected String artistName;
  public String getInfo() {
	return artistName;
    //Returns sNo, Name, Artist name, etc in a string
  }
  
  @Override
  public int getPrice() {
	return price;
    //override
    //...
  }
}
