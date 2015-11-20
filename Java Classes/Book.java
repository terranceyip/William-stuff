public class Book extends Readable {
	//...

	public Book(String info) {
		super(info);
		type = "Book";
	}

	@Override
	public int getPrice() {
		return (int) (1.02*price);
		//override to get the item price and add 2% (Environment Tax)
	}
}
