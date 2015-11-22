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
}
