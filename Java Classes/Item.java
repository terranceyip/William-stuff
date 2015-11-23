public abstract class Item {

	public abstract String getInfo();
	public abstract int getPrice();
	public abstract int getShipping();
	public abstract int getSNo();
	public abstract String getName();
	public abstract int getQuantity();
	public abstract void subtractQuantity(int i);
	public abstract String getType();

	protected int price;
	protected int sNo;
	protected String name;
	protected int quantity;
	protected String type;

	//Add other fields if necessary

}
