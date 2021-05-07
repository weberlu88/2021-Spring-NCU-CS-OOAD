package mob_movie;

import java.time.LocalDateTime;

/** �q�v **/
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

/** �U�� **/
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

/** �@����������  �q�v:�U�Ȫ�m:n���p�� **/
class Rental {
	private Customer customer;
	private Movie movie;
	private LocalDateTime datetime; // ���O�ɶ�
	private int point; // �浧�ֿn�I��
	private int price; // �浧���O���B
	private int duration; // �Ӥ����ɴX��
	
	public Rental(Customer customer, Movie movie, int duration) {
		this.customer = customer;
		this.movie = movie;
		this.duration = duration;
		datetime = LocalDateTime.now(); // �ٻݭnpoint price
		point = movie.getPoint();
		price = movie.getPrice();
	}
	
	// �p�⯲duration�ѡA�Ӥ��n�h�ֿ�
	private int calculatePrice(Movie movie, int duration) {
		int basic = 0, extra = 0;
		if (basic <= movie.rentDurationLimit()) 
			basic = movie.getNormalPricePerDay() * duration;
		else {
			basic = * movie.rentDurationLimit(); // �򥻤Ѽƪ�����
			extra = // �B�~�Ѽƪ�����
		}
		return basic + extra;	
	}
}

public class Main {

	

	public static void main(String[] args) {

		Customer april = new Customer("April");
		Customer joe = new Customer("Joe");
		
		Movie ���O�k�W�H = new Movie("���O�k�W�H"); // old
		Movie ���q�p�� = new Movie("���q�p��"); // new
		Movie �p������ = new Movie("�p������"); // new

	}

}
