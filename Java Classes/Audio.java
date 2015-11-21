public class Audio extends Item {

	protected String artistName;
	protected String info;
	
	public Audio(String info){
		this.info = info;
		String[] arr = info.split(", ");	//arr holds array of strings containing sNo, Name, etc.
		sNo = Integer.parseInt(arr[0]);
		name = arr[1];
		artistName = arr[2];
		price = Integer.parseInt(arr[3]);
		quantity = Integer.parseInt(arr[4]);
	}
	
	public String getInfo() {
		return info;		//returns sNo, name, Artist name, etc in a string
	}

	@Override
	public int getPrice() {
		return price;
	}
}
