import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class williamstuff {
	
	private String[] usernames;
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
	
	public static void main (String args[]) {
		// SIGN IN
		String consoleInput = "WILLIAM";
		
	}
}
