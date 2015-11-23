import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class williamstuff {

	private String[] usernames;

	public static void main (String args[]) {
		sortByPrice("CDs.txt");
	}

	public static void sortByPrice(String filename){
		String[] s = getFile(filename);
		String[][] ss = new String [s.length][];
		for (int i = 0; i<s.length; i++){
			ss[i] = s[i].split(", ");
		}
		Arrays.sort(ss, new Comparator<String[]>(){
			public int compare(String[] entry1, String[] entry2) {
				int price1 = Integer.parseInt(entry1[3]);
				int price2 = Integer.parseInt(entry2[3]);
				if(price1 > price2)
					return 1;
				else 
					return -1;
			}
		});
		for (int i = 0; i < ss.length; i++){
			s[i] = ss[i][0] + ", " + ss[i][1] + ", " + ss[i][2] + ", " + ss[i][3] + ", " + ss[i][4];
		}
		setFile(filename, s);
	}

	public static void sortByName(String filename){
		String[] s = getFile(filename);
		String[][] ss = new String [s.length][];
		for (int i = 0; i<s.length; i++){
			ss[i] = s[i].split(", ");
		}
		Arrays.sort(ss, new Comparator<String[]>(){
			public int compare(String[] entry1, String[] entry2) {
				String title1 = entry1[1];
				String title2 = entry2[1];
				return title1.compareTo(title2);
			}
		});
		for (int i = 0; i < ss.length; i++){
			s[i] = ss[i][0] + ", " + ss[i][1] + ", " + ss[i][2] + ", " + ss[i][3] + ", " + ss[i][4];
		}
		setFile(filename, s);
	}



	//PUT USERS.TXT INTO ARRAY AND SPLIT
	public void getUserName () {
		BufferedReader in;
		String text = "";
		try {
			in = new BufferedReader(new FileReader("Users.txt"));
			String textbuffer = "";
			textbuffer = in.readLine();
			text.concat(textbuffer);
			while (textbuffer != null) {
				textbuffer = in.readLine();
				text.concat("\n" + textbuffer);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		usernames = text.split("\n");
	}

	//CHECK INPUT WITH ARRAY
	public boolean access (String[] usernames, String input) {
		String thingy = "";
		for (int i = 0; i < usernames.length; i++) {
			thingy = usernames[i];
			if (thingy.equals(input) ) {
				return true;		
			}
		}
		return false;
	}
	//SIGN UP TO PUT NAME IN THE TEXT
	public void putUserName (String input) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("Users.txt", true));
			out.append(input);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//PUT READABLES AND AUDIO.TXT INTO ARRAY
	public void getUserName (String filename) {
		BufferedReader in;
		String text = "";
		try {
			in = new BufferedReader(new FileReader(filename));
			String textbuffer = "";
			textbuffer = in.readLine();
			text.concat(textbuffer);
			while (textbuffer != null) {
				textbuffer = in.readLine();
				text.concat("\n" + textbuffer);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	public static String[] getFile(String filename){
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
