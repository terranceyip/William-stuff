/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Outline for CD objects.
 */

public class CD extends Audio {

	public CD(String info) {
		super(info);
		type = "CD";
	}

	@Override
	public int getPrice() {
		return price;
	}
	
	@Override
	public int getShipping() {
		return (int) (0.10*price);
	}
	
	public int getEnvTax(){
		return (int) (0.02*price);
	}
}
