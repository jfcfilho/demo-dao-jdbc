package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();

		System.out.println("==== TEST 1: department findById ====");
		Department dep = departmentDAO.findById(1);
		System.out.println(dep);
		
		System.out.println();
		System.out.println("==== TEST 2: department findAll ====");
		List<Department> deps = departmentDAO.findAll();
		deps.forEach(System.out::println);
		
		System.out.println();
		System.out.println("==== TEST 3: department insert ====");
		Department newDep = new Department();
		newDep.setName("Compras");
		departmentDAO.insert(newDep);
		System.out.println("Inserted New Id: " + newDep.getId());
		
		System.out.println();
		System.out.println("==== TEST 4: department update ====");
		dep = departmentDAO.findById(10);
		dep.setName("Logistica");
		departmentDAO.update(dep);
		System.out.println("Update completed!");
		
		System.out.println();
		System.out.println("==== TEST 5: department deleteById ====");
		dep = departmentDAO.findById(10);
		departmentDAO.deleteById(dep.getId());
		System.out.println("Delete completed!");
	}

}
