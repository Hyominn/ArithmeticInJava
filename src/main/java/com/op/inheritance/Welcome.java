package com.op.inheritance;

import java.util.ArrayList;

import static com.op.util.Print.println;

/**
 * @Author: NZY
 * @Date: 2020/3/19 16:06
 */
public class Welcome {
	public static void main(String[] args) {
		// construct a Manager object
		Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);

		ArrayList<Employee> staff = new ArrayList<>();

		// fill the staff array with Manager and Employee objects
		staff.add(boss);
		staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
		staff.add(new Employee("Tommy Tester", 40000, 1990, 3, 15));
		staff.add(new Employee("Carl Cracker", 80000, 1987, 12, 15));
		staff.add(Employee.builder().name("Kris").salary(100).year(2020).month(3).day(23).build());

		staff.set(4, Employee.builder().name("Kris Nie").salary(100).year(2020).month(3).day(23).build());
		//强制类型转换
		Manager boss1 = (Manager) staff.get(0);

		// println out information about all Employee objects
		for (Employee e : staff) {
			System.out.println("name：" + e.getName() + ",salary：" + e.getSalary());
		}

		println();
		Student liu = new Student("liu", "CS");
		liu.undress();
		liu.shower();
		liu.vest();

		println();
		Student kris = new Student("kris", "CS");
		kris.undress();
		kris.shower();
		kris.vest();
	}
}
