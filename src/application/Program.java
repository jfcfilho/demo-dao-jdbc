package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDAO sellerDAO = DAOFactory.createSellerDAO();
		
		System.out.println("==== TESTE 1: seller findById =====");
		Seller seller = sellerDAO.findById(3);
		
		System.out.println();
		System.out.println("==== TESTE 2: seller findByDepartment =====");
		Department department = new Department();
		department.setId(2);
		List<Seller> sellers = sellerDAO.findByDepartment(department);
		sellers.forEach(System.out::println);		
		
		System.out.println(seller);
		
		
	}
}
