/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverse;

import convoy.db.Spot;

/**
 *
 * @author Ebbe
 */
public class PutContainer {
	
	Spot spot;
	String token;
	
	public PutContainer(Spot spot, String token){
		this.spot=spot;
		this.token=token;
	}
	
	public Spot getSpot(){
		return spot;
	}
	
	public String getToken(){
		return token;
	}

}
