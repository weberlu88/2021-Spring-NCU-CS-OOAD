package lab6_db2class;

import java.util.List;

class Color {
	public int id;
	public int color;
}

class Part {
	public int id;
	public String partName;
	public int weight;
	public Color color; // fk
}

class City {
	public int id;
	public String cityName;
}

class Supplier {
	public int id;
	public String supplierName;
	public City city;
	public List<Part> productList; // fk m:n
}

class Warehouse {
	public int id;
	public String warehouseName;
	public City city; // fk
	public Supplier supplier; // fk
	public List<Inventory> inventoryList; // fk m:n
}

class Inventory {
	public int id;
	public Part part;
	public int amount;
}




