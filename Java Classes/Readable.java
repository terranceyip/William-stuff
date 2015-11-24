/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Outline for Readable objects.
 */

public class Readable extends Item {

	protected String authorName;
	
	public Readable(String info) {
		String[] arr = info.split(", ");	//arr holds array of strings containing sNo, Name, etc.
		sNo = Integer.parseInt(arr[0]);
		name = arr[1];
		authorName = arr[2];
		price = Integer.parseInt(arr[3]);
		quantity = Integer.parseInt(arr[4]);
	}

	public String getInfo() {
		return (sNo + ", " + name + ", " + authorName + ", " + price + ", " + quantity);
	}
	
	public void printInfo() {
		System.out.format("%6s%30s%16s%12d%20d%12s%s", sNo + ".", name, authorName, price, quantity, type, "\n");
	}

	@Override
	public int getPrice() {
		return price;
	}

	public int getShipping() {
		return 0;
	}

	public int getEnvTax(){
		return 0;
	}
	
	public int getSNo(){
		return sNo;
	}
	
	public int getQuantity(){
		return quantity;
	}

	public void subtractQuantity(int i){
		quantity -= i;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
}
