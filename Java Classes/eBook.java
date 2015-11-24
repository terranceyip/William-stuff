/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Outline for eBook objects.
 */

public class eBook extends Readable {

	public eBook(String info) {
		super(info);
		type = "eBook";
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
		return 0;
	}
}
