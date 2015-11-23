public class Audio extends Item {

	protected String artistName;
	
	public Audio(String info){
		String[] arr = info.split(", ");	//arr holds array of strings containing sNo, Name, etc.
		sNo = Integer.parseInt(arr[0]);
		name = arr[1];
		artistName = arr[2];
		price = Integer.parseInt(arr[3]);
		quantity = Integer.parseInt(arr[4]);
	}
	
	public String getInfo() {
		return (sNo + ", " + name + ", " + artistName + ", " + price + ", " + quantity + ", " + type);
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

	public int getSNo(){
		return sNo;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void subtractQuantity(int i){
		quantity -= i;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
}
