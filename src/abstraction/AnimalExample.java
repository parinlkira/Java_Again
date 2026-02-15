package abstraction;


abstract class Animal{
	
	void legs() {
		System.out.println("Every Animal HAs 4 legs!");
	}
	
	abstract void eat();
	abstract void sound();
}

class Dog extends Animal{

	@Override
	void eat() {
		System.out.println("Dog is Eating!");
	}

	@Override
	void sound() {
		System.out.println("Dog is Barking!");
		
	}
	
	
	
}

public class AnimalExample {

	public static void main(String[] args) {
		
		Animal dog = new Dog();

		dog.legs();
		dog.eat();
		dog.sound();
	}

}
