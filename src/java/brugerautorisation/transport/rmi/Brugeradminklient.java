package brugerautorisation.transport.rmi;

import brugerautorisation.data.Bruger;
import java.rmi.Naming;
import java.util.UUID;

public class Brugeradminklient {
	public static void main(String[] arg) throws Exception {
//		Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://localhost/brugeradmin");
		Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
                UUID id = new UUID(0, 0);
    //ba.sendGlemtAdgangskodeEmail("jacno", "Dette er en test, husk at skifte kode");
		//ba.ændrAdgangskode("jacno", "kodenj4gvs", "xxx");
		Bruger b = ba.hentBruger("s125015", "GetOutOrGetRekt");
		System.out.println("Fik bruger = " + b);
		// ba.sendEmail("jacno", "xxx", "Hurra det virker!", "Jeg er så glad");

		Object ekstraFelt = ba.getEkstraFelt("jacno", "xxx", "s123456_testfelt");
		System.out.println("Fik ekstraFelt = " + ekstraFelt);
                //ba.ændrAdgangskode("s144855", "kode9pyh3p", "1234");
                Bruger b1 = ba.hentBruger("s125015", "GetOutOrGetRekt");
                System.out.println("bruger: " + b1 );
		//ba.setEkstraFelt("s125015", "xxx", "s123456_testfelt", "Hej fra Jacob"); // Skriv noget andet her
	}
}
