package com.op.inheritance;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author: NZY
 * @Date: 2020/3/19 16:05
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {
    private String Name;
    private double Salary;
    private LocalDate HireDay;
    private String Company;


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
        this.Name = name;
        this.Salary = salary;
        HireDay = LocalDate.of(year, month, day);
    }

    public void raiseSalary(double byPercent) {
        double raise = Salary * byPercent / 100;
        Salary += raise;
    }

    public String getDescription() {
        return String.format("an employee with a salary of ￥%.2f", Salary);
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(Name) + 11 * Double.hashCode(Salary) + 13 * Objects.hashCode(HireDay);
    }

    @Override
    public boolean equals(Object otherObject) {
        // a quick test to see if the objects are identical
        if (this == otherObject) return true;
        // must return false if the explicit parameter is null
        if (otherObject == null) return false;
        // if the classes don' t match, they can' t be equal
        if (getClass() != otherObject.getClass())
            return false;
        // now we know otherObject is a non-null Employee
        Employee other = (Employee) otherObject;
        // test whether the fields have identical values
        return Name.equals(other.Name) && Salary == other.Salary && HireDay.equals(other.HireDay);
    }
}
