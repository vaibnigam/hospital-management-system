package com.hospital.model;

public abstract class Person {
	private String id;
	private String name;
	private String contactNumber;
	private int age;

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", age=" + age + "]";
	}

	public Person(String name, String contactNumber, int age) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public abstract String getRole();
}
