/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Abstract outline for all objects.
 */

public abstract class Item {

	public abstract String getInfo();
	public abstract int getPrice();
	public abstract int getShipping();
	public abstract int getSNo();
	public abstract String getName();
	public abstract int getQuantity();
	public abstract void subtractQuantity(int i);
	public abstract String getType();
	public abstract int getEnvTax();

	protected int price;
	protected int sNo;
	protected String name;
	protected int quantity;
	protected String type;
}
