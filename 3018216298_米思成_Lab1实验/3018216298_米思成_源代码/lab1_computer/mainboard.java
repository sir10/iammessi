package lab1_computer;

public abstract class mainboard {
	public String name;
	public String speed;
	public double price;
	
	public mainboard(String name, String speed, double price){
		this.name = name;
		this.speed = speed;
		this.price = price;
	}
	
	abstract void work();
}


class Asus extends mainboard{
	
    public Asus() {
		super("Asus", "3600GHZ", 700);
	}
    
	@Override
	void work() {
		System.out.print("Asus_mainboard work");
	}
    
}


class Gigabyte extends mainboard{
	
    public Gigabyte() {
		super("Gigabyte", "3600GHZ", 900);	
	}
    
	@Override
	void work() {
		System.out.print("Gigabyte_mainboard work");
	}
    
}
