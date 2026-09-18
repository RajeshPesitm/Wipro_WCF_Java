package Collndemopkg;

interface MyInterface{
	void method();	
	public default void defMethod(){
		System.out.println("default method in MyInterface");
	}
	public static void statMethod(){
		System.out.println("static method in MyInterface");
	}
}

public class Myclass implements MyInterface {
	
	public Myclass() {
		// TODO Auto-generated constructor stub
	}
	
	public void method(){
		System.out.println("abstract method from MyInterface defined in MyClass");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyInterface.statMethod();
		MyInterface myiterface = new Myclass();
		myiterface.defMethod();
		myiterface.method();
		Myclass myclass = new Myclass();
		myclass.method();
	}

}