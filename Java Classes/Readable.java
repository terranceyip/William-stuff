public class Readable extends Item {

	protected String authorName;
	protected String info;
	
	public Readable(String info) {
		this.info = info;
		String[] arr = info.split(", ");	//arr holds array of strings containing sNo, Name, etc.
		sNo = Integer.parseInt(arr[0]);
		name = arr[1];
		authorName = arr[2];
		price = Integer.parseInt(arr[3]);
		quantity = Integer.parseInt(arr[4]);
		type = arr[5];
	}

	public String getInfo() {
		return info;
	}

	@Override
	public int getPrice() {
		return price;
	}
}
