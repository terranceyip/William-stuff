/*
 * Name: Terrance Yip, Susan Yuen, William Tran
 * MacID: yipsh, yuens2, tranwt
 * Student Number: 1415472, 1416198, 1407613
 * Description: Holds methods for username and admin.
 */

public class User {
	
	protected String username;
	private String password;
	
	public User(String name){
		username = name;
		if (name.matches("ADMIN, .*")){
			String[] s = name.split(", ");
			username = s[0];
			password = s[1];
		}
	}
	
	public String getUsername() {
		return username;
		//stores the username
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String pw){
		password = pw;
	}
}
