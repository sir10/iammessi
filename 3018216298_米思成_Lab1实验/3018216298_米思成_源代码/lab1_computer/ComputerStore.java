package lab1_computer;

public class ComputerStore implements show{
	
	@Override
	public void display() {
		System.out.println("Welcome to our computer store! We have 3 kinds of computers now.");
		System.out.println();
		
		Computer computer1 = new computer1();
		System.out.println(computer1.setName() + ":");
		System.out.println("Information: " + computer1.setInformation());
		System.out.println("Price: " + computer1.setPrice() + " RMB");
		System.out.print("Does it work? ");
		computer1.work();
		System.out.println();
		
		Computer computer2 = new computer2();
		System.out.println(computer2.setName() + ":");
		System.out.println("Information: " + computer2.setInformation());
		System.out.println("Price: " + computer2.setPrice() + " RMB");
		System.out.print("Does it work? ");
		computer2.work();
		System.out.println();
		
		Computer computer3 = new computer3();
		System.out.println(computer3.setName() + ":");
		System.out.println("Information: " + computer3.setInformation());
		System.out.println("Price: " + computer3.setPrice() + " RMB");
		System.out.print("Does it work? ");
		computer3.work();
		
	}
	
	
	public static void main(String[] args) {
		ComputerStore store = new ComputerStore();
		store.display();
	}
}
