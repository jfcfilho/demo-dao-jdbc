package application;

import java.math.BigDecimal;
import java.util.Date;
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
		System.out.println(seller);
		
		System.out.println();
		System.out.println("==== TESTE 2: seller findByDepartment =====");
		Department department = new Department();
		department.setId(2);
		List<Seller> sellers = sellerDAO.findByDepartment(department);
		sellers.forEach(System.out::println);
		
		System.out.println();
		System.out.println("==== TESTE 3: seller findAll =====");
		List<Seller> allSellers = sellerDAO.findAll();
		allSellers.forEach(System.out::println);
		
		System.out.println();
		System.out.println("==== TESTE 4: seller insert =====");
		Seller newSeller = new Seller();
		newSeller.setName("Greg");
		newSeller.setEmail("greg@gmail.com");
		newSeller.setBirthDate(new Date());
		newSeller.setBaseSalary(new BigDecimal(4000));
		newSeller.setDepartment(department);
		sellerDAO.insert(newSeller);
		System.out.println("Inserted New Id = " + newSeller.getId());
		
	}
}
