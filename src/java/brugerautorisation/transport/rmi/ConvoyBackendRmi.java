/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brugerautorisation.transport.rmi;


import brugerautorisation.data.Bruger;
import java.rmi.Naming;


/**
 *
 * @author ministeren
 */
public class ConvoyBackendRmi 
{
    
    Brugeradmin ba;
    TokenHandler th = new TokenHandler();

    public ConvoyBackendRmi() throws Exception{
        System.out.println("Publicerer RMI-tjeneste");
        this.ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        System.out.println("RMI-tjeneste publiceret");
//        Naming.bind("rmi://javabog.dk/brugeradmin", ba);
//        Bruger b = ba.hentBruger("s125015", "GetOutOrGetRekt");
//        System.out.println("Fik bruger: " + b ) ;  
    adminLogin("s144842", "xxx");
    }
    
    public String adminLogin(String brugernavn, String adgangskode) throws Exception{
//        ba.hentBruger(brugernavn, adgangskode);
    try {
         Bruger b = ba.hentBruger(brugernavn, adgangskode);   //Bruger forsøges hentes
         System.out.println("Bruger hentet: " + b.brugernavn);
         
         String newToken = th.createToken(b.brugernavn); //Ny token til bruger oprette
         System.out.println(newToken);
         System.out.println("Token oprettet");
         
         return newToken;
         
        } catch (Exception e) {
            System.out.println("Bruger findes ikke på serveren eller password er forkert");
            String bad = "Bruger findes ikke på serveren eller password er forkert";
            return bad;
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
