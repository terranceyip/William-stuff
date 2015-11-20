public class CD extends Audio {

	public CD(String info) {
		super(info);
		type = "CD";
	}

	@Override
	public int getPrice() {
		return (int) (1.02*price);
		//override to get the item price and add 2% (Environment Tax)
	}
}
