package mob_movie;

import java.time.LocalDateTime;

/** 電影 **/
class Movie {
	protected String name;
	
	public Movie(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/** 顧客 **/
class Customer {
	private String name;
	
	public Customer(String name) {
		this.name = name;
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
	private int point; // 單筆累積點數
	private int price; // 單筆消費金額
	private int duration; // 該片租借幾天
	
	public Rental(Customer customer, Movie movie, int duration) {
		this.customer = customer;
		this.movie = movie;
		this.duration = duration;
		datetime = LocalDateTime.now(); // 還需要point price
		point = movie.getPoint();
		price = movie.getPrice();
	}
	
	// 計算租duration天，該片要多少錢
	private int calculatePrice(Movie movie, int duration) {
		int basic = 0, extra = 0;
		if (basic <= movie.rentDurationLimit()) 
			basic = movie.getNormalPricePerDay() * duration;
		else {
			basic = * movie.rentDurationLimit(); // 基本天數的價格
			extra = // 額外天數的價格
		}
		return basic + extra;	
	}
}

public class Main {

	

	public static void main(String[] args) {

		Customer april = new Customer("April");
		Customer joe = new Customer("Joe");
		
		Movie 神力女超人 = new Movie("神力女超人"); // old
		Movie 正義聯盟 = new Movie("正義聯盟"); // new
		Movie 雷神索爾 = new Movie("雷神索爾"); // new

	}

}
