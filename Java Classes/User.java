public class User {
	
	protected String username;
	private String password;
	
	public User(String name){
		username = name;
		if (name.matches("^(Admin, ).*")){
			String[] s = name.split(", ");
			s[0] = username;
			s[1] = password;
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
