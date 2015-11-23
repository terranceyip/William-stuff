import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShoppingCart extends User {

	private String[] content = null; //array of items
	
	public ShoppingCart(String name) {
		super(name);
	}

	public String[] getContent() {
		return content;
		//return the content of the shopping cart
	}
	
	public void setContents(String[] s){
		content = s;
	}

	public void addItem(int sNo, String name, int quantity, int price, int shipping) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyy");
		Date date = new Date();
		String sDate = df.format(date);
		String filename = "Cart_" + username + ".txt";
		boolean doesExist = false;
		String[] s = getFile(filename);
		for (int i = 0; i < s.length; i++){
			if(s[i].matches(sNo + ", .*")){
				doesExist = true;
				String[] temp = s[i].split(", ");
				int q = Integer.parseInt(temp[3]) + quantity;
				s[i].replace(", " + temp[3],", " + q);
			}
		}
		if (!doesExist){
			addLine(filename, sNo + ", " + name + ", " + sDate + ", " + quantity);
		}
		setFile(filename, s);
		content = getFile(filename);
	}
	
	public String[] getFile(String filename){
		BufferedReader in;
		String text = "";
		try {
			in = new BufferedReader(new FileReader(filename));
			String textbuffer = in.readLine();
			text = text.concat(textbuffer);
			while ((textbuffer = in.readLine()) != null) {
				text = text.concat("\n" + textbuffer);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return text.split("\n");
	}
	
	public void addLine (String filename, String input) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.newLine();
			out.append(input);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void setFile(String filename, String[] s){
		int size = s.length;
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < size-1; i++){
				out.write(s[i]);
				out.newLine();
			}
			out.write(s[size-1]);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
