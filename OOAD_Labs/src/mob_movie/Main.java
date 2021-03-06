package mob_movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** 電影有分三種類別，有不同的計價、租時: 新片、舊片、兒童片。以strategy pattern表達。 **/
class Category {
	protected int normalPrice;
	protected int extraPrice;
	protected int rentDurationLimit;
	protected double point;
	
	// getter 
	public int getNormalPrice() {
		return normalPrice;
	}
	public int getExtraPrice() {
		return extraPrice;
	}
	public int getRentDurationLimit() {
		return rentDurationLimit;
	}
	protected void setNormalPrice(int normalPrice) {
		this.normalPrice = normalPrice;
	}
	protected void setExtraPrice(int extraPrice) {
		this.extraPrice = extraPrice;
	}
	protected void setRentDurationLimit(int rentDurationLimit) {
		this.rentDurationLimit = rentDurationLimit;
	}
	public double getPoint() {
		return point;
	}
	protected void setPoint(double point) {
		this.point = point;
	}
	
}

/* 新片$3 可以租一天，每多一天，一天是$2。新片每租一片，常客基點 2.0 */
class NewFilm extends Category {
	public NewFilm() {
		setNormalPrice(3);
		setExtraPrice(2);
		setRentDurationLimit(1);
		setPoint(2.0);
	}
}

/* 舊片$2 可以租三天，每多一天，一天是$1，常客基點 1.0 */
class OldFilm extends Category {
	public OldFilm() {
		setNormalPrice(2);
		setExtraPrice(1);
		setRentDurationLimit(3);
		setPoint(1.0);
	}
}

/* 兒童片$2 可以租5天，每多一天，一天是$1，常客積點 0.5 */
class KidFilm extends Category {
	public KidFilm() {
		setNormalPrice(2);
		setExtraPrice(1);
		setRentDurationLimit(5);
		setPoint(0.5);
	}
}

/** 電影 **/
class Movie {
	protected String name;
	protected Category category;
	
	public Movie(String name, Category category) {
		this.name = name;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getNormalPrice() {
		return category.getNormalPrice();
	}
	public int getExtraPrice() {
		return category.getExtraPrice();
	}
	public int getRentDurationLimit() {
		return category.getRentDurationLimit();
	}
	public double getPoint() {
		return category.getPoint();
	}
}

/** 顧客 **/
class Customer {
	private String name;
	
	public Customer(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

/** 一筆租片紀錄  電影:顧客的m:n關聯表 **/
class Rental {
	private Customer customer;
	private Movie movie;
	private LocalDateTime datetime; // 消費時間
	private double point; // 單筆累積點數
	private int price; // 單筆消費金額
	private int duration; // 該片租借幾天
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public Rental(Customer customer, Movie movie, int duration) {
		this.customer = customer;
		this.movie = movie;
		this.duration = duration;
		datetime = LocalDateTime.now(); // 何時租的
		point = movie.getPoint(); // 獲得點數
		price = calculatePrice(movie, duration); // 租此片花了多少錢
	}
	
	@Override
	public String toString() {
//		String formattedDateTime = duration.format(formatter);
		return String.format("Rental: %s rented %s for %s days, in tatal of $%d. Get %.1f points on %s.", customer.getName(), 
				movie.getName(),
				duration,
				price,
				point,
				datetime.format(formatter));
	}
	
	// 計算租duration天，該片要多少錢
	private int calculatePrice(Movie movie, int duration) {
		int basic = 0, extra = 0;
		if (duration <= movie.getRentDurationLimit()) 
			basic = movie.getNormalPrice() * duration; // 基本天數的價格
		else {
			basic = movie.getNormalPrice() * movie.getRentDurationLimit(); // 基本天數的價格
			extra = movie.getExtraPrice() * (duration - movie.getRentDurationLimit()); // 額外天數的價格
		}
		return basic + extra;	
	}

	// getter
	public Customer getCustomer() {
		return customer;
	}
	public Movie getMovie() {
		return movie;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public double getPoint() {
		return point;
	}
	public int getPrice() {
		return price;
	}
	public int getDuration() {
		return duration;
	}
}

/** Data Access Object Pattern or DAO pattern **/
class RentalDao {
	
	//list is working as a database table
	private static List<Rental> rents = new ArrayList<Rental>();
	
	public List<Rental> findAll() {
		return rents;
	}
	
	public List<Rental> findByName(String name) {
		return rents.stream().filter(r -> r.getCustomer().getName() == name).collect(Collectors.toList());
    }
	
	public List<Rental> findByCustomer(Customer customer) {
		return rents.stream().filter(r -> r.getCustomer().equals(customer)).collect(Collectors.toList());
    }
	
	public void insertRental(Rental... rentals) { // zero or more
		for(Rental rental : rentals){
			rents.add(rental);
	    }
	} 
}

/** 列印報表的helper class **/
class ReportTool {
	// class method
	public static void printReport(List<Rental> rentals) {
		int totalPrice = 0; 
		double totalPoint = 0;
		for(Rental rental : rentals){
			System.out.println(rental.toString()); // print 單筆rental資料
			totalPrice += rental.getPrice();
			totalPoint += rental.getPoint();
	    }
		System.out.println("消費總金額是: $"+totalPrice+" 累積點數為: "+totalPoint); // print 總額
	}
}

public class Main {

	public static void main(String[] args) {

		// 有2個客戶 偷懶存在main裡
		Customer april = new Customer("April");
		Customer joe = new Customer("Joe");
		
		// 有3個電影 偷懶存在main裡
		Movie 神力女超人 = new Movie("神力女超人", new OldFilm()); // old
		Movie 正義聯盟 = new Movie("正義聯盟", new NewFilm()); // new
		Movie 雷神索爾 = new Movie("雷神索爾", new NewFilm()); // new
		
//		List<Rental> rents = new ArrayList<Rental>();
//		rents.add(new Rental(april, 神力女超人, 3));
//		rents.add(new Rental(joe, 神力女超人, 2));
//		rents.add(new Rental(april, 正義聯盟, 5));
//		rents.add(new Rental(joe, 雷神索爾, 1));
		
//		for (Rental r: rents) {
//			System.out.println(r.toString());
//		}
		
		// 消費紀錄存在 RentalDao
		RentalDao rentalDao = new RentalDao();
		rentalDao.insertRental(new Rental(april, 神力女超人, 3));
		rentalDao.insertRental(new Rental(joe, 神力女超人, 2));
		rentalDao.insertRental(new Rental(april, 正義聯盟, 5));
		rentalDao.insertRental(new Rental(joe, 雷神索爾, 1));
		
		// 印報表
		ReportTool.printReport(rentalDao.findByName("April"));
		ReportTool.printReport(rentalDao.findByCustomer(joe));

	}

}
