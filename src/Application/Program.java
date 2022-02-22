package Application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Departament;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Departament obj = new Departament(1, "Books");
		System.out.println(obj);
		
		Seller sl = new Seller(1, "João", "João.nft@gmail.com", new Date(), 3000.0, obj);
		System.out.println(sl);
		
		SellerDAO sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(1);
		
		Departament departament = new Departament(2, null);
		List<Seller> list = sellerDao.findByDepartament(departament);
		for (Seller sel : list) {
			System.out.println(sel);
		}
	}

}
