public class MP3 extends Audio {

	public MP3(String info) {
		super(info);
	}

	@Override
	public int getPrice() {
		return price;
		//override and only call the parent's getPrice() to get the base price
	}
}
