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
		return (sNo + "\t" + name + "\t" + artistName + "\t" + price + "\t" + quantity + "\t" + type);
	}
	
	public void printInfo() {
		System.out.format("%6s%30s%16s%12d%20d%12s%s", sNo, name, artistName, price, quantity, type, "\n");
	}

	@Override
	public int getPrice() {
		return price;
	}
	
	public int getShipping() {
		return 0;
	}
}
