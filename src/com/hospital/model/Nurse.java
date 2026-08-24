package com.hospital.model;

public class Nurse extends Person {
	private String department;
	private ShiftType shiftTiming;

	public Nurse(String name, String contactNumber, int age, String department, ShiftType shiftTiming) {
		super(name, contactNumber, age);
		this.department = department;
		this.shiftTiming = shiftTiming;
	}

	@Override
	public String getRole() {
		return "Nurse";
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ShiftType getShiftTiming() {
		return shiftTiming;
	}

	public void setShiftTiming(ShiftType shiftTiming) {
		this.shiftTiming = shiftTiming;
	}

	@Override
	public String toString() {
		return super.toString() + ", Nurse [department=" + department + ", shiftTiming=" + shiftTiming + "]";
	}
}