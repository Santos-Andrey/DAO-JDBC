package Application;

import java.util.Date;

import model.entities.Departament;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Departament obj = new Departament(1, "Books");
		System.out.println(obj);
		
		Seller sl = new Seller(1, "Jo�o", "Jo�o.nft@gmail.com", new Date(), 3000.0, obj);
		System.out.println(sl);
	}

}
