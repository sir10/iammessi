package lab1_computer;

public abstract class memory {
	public String name;
	public String volume;
	public double price;
	
	public memory(String name, String volume, double price){
		this.name = name;
		this.volume = volume;
		this.price = price;
	}
	
	abstract void work();
	
}


class Samsung extends memory{
	
	public Samsung() {
		super("Samsung", "8G", 400);
	}
    
	@Override
	void work() {
		System.out.print("Samsung_memory work");
	}
    
}


class Kingston extends memory{
	
	public Kingston() {
		super("Kingston", "8G", 300);
	}
    
	@Override
	void work() {
		System.out.print("Kingston_memory work");
	}
    
}