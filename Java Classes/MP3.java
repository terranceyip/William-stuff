/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Outline for MP3 objects.
 */

public class MP3 extends Audio {

	public MP3(String info) {
		super(info);
		type = "MP3";
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
		return 0;
	}
}
