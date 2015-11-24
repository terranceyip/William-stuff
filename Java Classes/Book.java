/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Outline for book objects.
 */

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
	
	public int getEnvTax(){
		return (int) (0.02*price);
	}
}
