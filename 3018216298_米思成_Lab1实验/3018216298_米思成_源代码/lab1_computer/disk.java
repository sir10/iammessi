package lab1_computer;

public abstract class disk {
	public String name;
	public String volume;
	public double price;
	
	public disk(String name, String volume, double price){
		this.name = name;
		this.volume = volume;
		this.price = price;
	}
	
	abstract void work();
}


class Seagate extends disk{
	
    public Seagate() {
		super("Seagate", "1TB", 300);
	}
    
	@Override
	void work() {
		System.out.print("Seagate_disk work");
	}
    
}


class WestDigitals extends disk{
	
    public WestDigitals() {
		super("WestDigitals", "2TB", 400);	
	}
    
	@Override
	void work() {
		System.out.print("WestDigitals_disk work");
	}
    
}
