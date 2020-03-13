package lab1_computer;

public abstract class Computer {
	public String name;
	public String information;
	public double price;
	
	public abstract String setName();
	
	public abstract String setInformation();
	
	public abstract double setPrice();
	
	public abstract void work();
	
}


class computer1 extends Computer{

	CPU intel = new Intel();
	memory samsung = new Samsung();
	disk seagate = new Seagate();
	mainboard asus = new Asus();
	
	@Override
	public String setName(){
		return name = "COMPUTER1";
	}

	@Override
	public String setInformation() {
		return information = "CPU is " + intel.name + ", memory is " + samsung.name +
				", disk is " + seagate.name + ", mainboard is " + asus.name + ".";
	}

	@Override
	public double setPrice() {
		return price = intel.price + samsung.price + seagate.price + asus.price;		
	}

	@Override
	public void work() {
		intel.work();
		System.out.print(", ");
		samsung.work();
		System.out.print(", ");
		seagate.work();
		System.out.print(", ");
		asus.work();
		System.out.println("!");
	}
}


class computer2 extends Computer{

	CPU intel = new Intel();
	memory kingston = new Kingston();
	disk seagate = new Seagate();
	mainboard gigabyte = new Gigabyte();
	
	@Override
	public String setName(){
		return name = "COMPUTER2";
	}

	@Override
	public String setInformation() {
		return information = "CPU is " + intel.name + ", memory is " + kingston.name +
				", disk is " + seagate.name + ", mainboard is " + gigabyte.name + ".";
	}

	@Override
	public double setPrice() {
		return price = intel.price + kingston.price + seagate.price + gigabyte.price;		
	}

	@Override
	public void work() {
		intel.work();
		System.out.print(", ");
		kingston.work();
		System.out.print(", ");
		seagate.work();
		System.out.print(", ");
		gigabyte.work();
		System.out.println("!");
	}
}


class computer3 extends Computer{

	CPU amd = new AMD();
	memory samsung = new Samsung();
	disk westDigitals = new WestDigitals();
	mainboard asus = new Asus();
	
	@Override
	public String setName(){
		return name = "COMPUTER3";
	}

	@Override
	public String setInformation() {
		return information = "CPU is " + amd.name + ", memory is " + samsung.name +
				", disk is " + westDigitals.name + ", mainboard is " + asus.name + ".";
	}

	@Override
	public double setPrice() {
		return price = amd.price + samsung.price + westDigitals.price + asus.price;		
	}

	@Override
	public void work() {
		amd.work();
		System.out.print(", ");
		samsung.work();
		System.out.print(", ");
		westDigitals.work();
		System.out.print(", ");
		asus.work();
		System.out.println("!");
	}	
}