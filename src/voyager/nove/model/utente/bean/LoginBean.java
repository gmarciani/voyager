package voyager.nove.model.utente.bean;

import java.io.Serializable;

/**
 * @name LoginBean
 *
 * @project Voyager
 *
 * @package voyager.nove.model.utente.bean
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	public LoginBean() {}

	public String getUsername() {
		return this.username;
	}

	public LoginBean setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public LoginBean setPassword(String password) {
		this.password = password;
		return this;
	}
	
	

	

}
