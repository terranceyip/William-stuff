public class eBook extends Readable {

	public eBook(String info) {
		super(info);
		type = "eBook";
	}

	@Override
	public int getPrice() {
		return price;
		//override and only call the parent's constructor to get the base price
	}
}
