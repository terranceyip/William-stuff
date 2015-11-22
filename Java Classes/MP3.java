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
}
