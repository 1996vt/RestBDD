package runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Driver {
	public static void main(String[] args) {
		Employee emp1 = new Employee("Aname", 12);
		Employee emp2 = new Employee("Bname", 22);

		// Comparison of two object age
		if (emp1.getAge() == emp2.getAge()) {
			System.out.println(emp1.getName() + "'s age is equal to " + emp2.getName() + "'s age");
		} else {
			System.out.println(emp1.getName() + "'s age is not equal to " + emp2.getName() + "'s age");
		}
		// ArrayList
		ArrayList<Employee> arr = new ArrayList<Employee>();
		arr.add(new Employee("Mname", 12));
		arr.add(new Employee("Pname", 13));
		arr.add(new Employee("Ename", 14));
		arr.add(new Employee("Fname", 15));
		arr.add(new Employee("Lname", 16));
		arr.add(new Employee("Hname", 17));
		arr.add(new Employee("Zname", 18));
		arr.add(new Employee("Jname", 19));
		for (Employee e : arr) {
			System.out.println(e);
		}
		Collections.sort(arr, new SortName());

		System.out.println("<------------------Sorted ArrayList---------------------------->");
		for (Employee e : arr) {
			System.out.println(e + " " + e.getId());
		}

		// ArrayList to HashMap
		HashMap<Integer, Employee> hmap = new HashMap<Integer, Employee>();
		for (Employee e : arr) {
			hmap.put(e.getId(), e);
		}
		// Display Key value
		Set<Integer> set = hmap.keySet();
		for (int key : set) {
			System.out.println("key id: " + key + " value: " + hmap.get(key));
		}

		// Removal of employee below age 15
		ArrayList<Integer> ageArray = new ArrayList<Integer>();
		for (int key : set) {
			if (hmap.get(key).getAge() < 15)
				ageArray.add(key);
		}
		for (int key : ageArray) {
			hmap.remove(key);
		}

		// Display removed hmap
		System.out.println(hmap.entrySet());

	}
}
