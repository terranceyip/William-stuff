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
}
