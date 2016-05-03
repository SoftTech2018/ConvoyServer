/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brugerautorisation.transport.rmi;


import brugerautorisation.data.Bruger;
import java.rmi.Naming;


/**
 *  Klassens formål: At oprette forbindelse til brugerautorisationsserveren og 
 *  håndtere indgående login-anmodninger fra REST-serveren, samt oprette tokens
 *  på godkendte logins.
 *  @author Ebbe
 *  
 */
public class ConvoyBackendRmi 
{
    
    Brugeradmin ba;
    TokenHandler th = new TokenHandler();

    public ConvoyBackendRmi() throws Exception{
        System.out.println("Publicerer RMI-tjeneste");
        this.ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        System.out.println("RMI-tjeneste publiceret"); 
//    adminLogin("s144842", "xxx");
//    adminLogin("s144842", "xxx1");
    }
    
    public String adminLogin(String brugernavn, String adgangskode) throws Exception{

    try {
         Bruger b = ba.hentBruger(brugernavn, adgangskode);      //Bruger forsøges hentes
         System.out.println("Bruger hentet: " + b.brugernavn);
         String newToken = th.createToken(b.brugernavn);         //Ny token til bruger oprette
         System.out.println(newToken);
         System.out.println("Token oprettet");
         
         return newToken;
         
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Bruger findes ikke på serveren eller password er forkert");
//            String bad = "Bruger findes ikke på serveren eller password er forkert";
            return null;
        }
    
    
    
    
    
//        if (ba.getEkstraFelt("token", adgangskode, brugernavn) == null) {
//            UUID id = UUID.fromString(brugernavn);
//            ba.setEkstraFelt(brugernavn, adgangskode, "token", id);   
//        }
//        else {
//            String token = ba.getEkstraFelt(brugernavn, adgangskode, "token").toString();
//             return ba.hentBruger(brugernavn, adgangskode).toString() + token;
//        }
        
       
       
        
    }
    
    
    
}
