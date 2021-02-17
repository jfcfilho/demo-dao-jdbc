package application;

import java.math.BigDecimal;
import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department obj = new Department(1, "Books");
		
		System.out.println(obj);
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), new BigDecimal(3000), obj);
		
		System.out.println(seller);
		
		
	}
}
