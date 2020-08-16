package foundation.inheritance;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import static foundation.util.DoubleComparer.doubleCompare;

/**
 * @Author: NZY
 * @Date: 2020/3/19 16:05
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AbstractPerson {
	private String name;
	private double salary;
	private LocalDate hireDay;
	private String company;


	/**
	 * Employee 构造函数
	 *
	 * @param name   姓名
	 * @param salary 薪酬
	 * @param year   年
	 * @param month  月
	 * @param day    日
	 */
	@Builder
	public Employee(String name, double salary, int year, int month, int day) {
		this.name = name;
		this.salary = salary;
		hireDay = LocalDate.of(year, month, day);
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	@Override
	public String getDescription() {
		return String.format("an employee with a salary of ￥%.2f", salary);
	}

	@Override
	public int hashCode() {
		return 7 * Objects.hashCode(name) + 11 * Double.hashCode(salary) + 13 * Objects.hashCode(hireDay);
	}

	@Override
	public boolean equals(Object otherObject) {
		// a quick test to see if the objects are identical
		if (this == otherObject) {
			return true;
		}
		// must return false if the explicit parameter is null
		if (otherObject == null) {
			return false;
		}
		// if the classes don' t match, they can' t be equal
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		// now we know otherObject is a non-null Employee
		Employee other = (Employee) otherObject;
		// test whether the fields have identical values
		return name.equals(other.name) && doubleCompare(salary, other.salary) && hireDay.equals(other.hireDay);
	}
}
