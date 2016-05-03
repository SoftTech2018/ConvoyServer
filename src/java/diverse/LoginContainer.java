/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverse;

/**
 *
 * @author Ebbe
 */
public class LoginContainer {
	
	String username, password;
	
	public LoginContainer(String username, String password){
		this.username=username;
		this.password=password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}

}
