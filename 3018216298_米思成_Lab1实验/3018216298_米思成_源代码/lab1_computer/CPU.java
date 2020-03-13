package lab1_computer;

public abstract class CPU {
	public String name;
	public int coreNum;
	public double price;
	
	public CPU(String name, int coreNum, double price){
		this.name = name;
		this.coreNum = coreNum;
		this.price = price;
	}
	
	abstract void work();

}


class Intel extends CPU{
	
    public Intel() {
		super("Intel", 6, 2000);
	}
    	
	@Override
	void work() {
		System.out.print("Intel_CPU work");
	}
    
}


class AMD extends CPU{
	
    public AMD() {
		super("AMD", 4, 1000);
	}
    
	@Override
	void work() {
		System.out.print("AMD_CPU work");
	}
    
}