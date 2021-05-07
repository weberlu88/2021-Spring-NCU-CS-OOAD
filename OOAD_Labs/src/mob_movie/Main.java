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
	Customer customer;
	Movie movie;
	LocalDateTime datetime; // 消費時間
	int point; // 單筆累積點數
	int price; // 單筆消費金額
	int duration; // 該片租借幾天
	
	public Rental(Customer customer, Movie movie, int duration) {
		this.customer = customer;
		this.movie = movie;
		this.duration = duration;
		datetime = LocalDateTime.now(); // 還需要point price
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
