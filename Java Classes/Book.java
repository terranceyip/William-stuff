public class Book extends Readable {

	public Book(String info) {
		super(info);
		type = "Book";
	}

	@Override
	public int getPrice() {
		return price;
	}
	
	@Override
	public int getShipping() {
		return 0;
	}
}
