package com.op.inheritance;

import lombok.*;

/**
 * @Author: NZY
 * @Date: 2020/3/19 16:05
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Manager extends Employee {
	/**
	 * 奖金
	 */
	private double bonus;

	/**
	 * @param name   the employee's name
	 * @param salary the salary
	 * @param year   the hire year
	 * @param month  the hire month
	 * @param day    the hire day
	 */
	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		bonus = 0;
	}

	@Override
	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}

	@Override
	public String getDescription() {
		//在覆盖一个方法的时候，子类方法不能低于超类方法的可见性
		return super.getDescription();
	}
}
