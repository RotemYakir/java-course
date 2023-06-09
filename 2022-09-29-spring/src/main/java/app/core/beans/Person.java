package app.core.beans;

import org.springframework.stereotype.Component;

@Component //("the-person") // bean name - the default is class name to lower case
// @Scope("prototype") // the default is singelton
public class Person {
	
	private int id;
	private String name;
	private int age;
	
	{
		System.out.println("person created");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
