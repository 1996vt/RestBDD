package runner;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

class Employee {
	String name;
	int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	// Auto increment ID
	int id;
	private static AtomicInteger atomicInt = new AtomicInteger(0);

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
		this.id = atomicInt.incrementAndGet();
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", id=" + id + "]";
	}

}

class SortName implements Comparator<Employee> {

	public int compare(Employee o1, Employee o2) {

		return o1.name.compareTo(o2.name);
	}
}