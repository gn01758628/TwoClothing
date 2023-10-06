package idv.GordonTai.member;



public abstract class  Pan {
	private String brand;
	private int price;
	
	public Pan() {
		
	}
	
	public Pan(String brand,int price) {
		this.brand = brand;
		this.price = price;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public int getPrice() {
		return price;		
	}
	
	public abstract void write();
}

