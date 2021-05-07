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
	Customer customer;
	Movie movie;
	LocalDateTime datetime; // ���O�ɶ�
	int point; // �浧�ֿn�I��
	int price; // �浧���O���B
	int duration; // �Ӥ����ɴX��
	
	public Rental(Customer customer, Movie movie, int duration) {
		this.customer = customer;
		this.movie = movie;
		this.duration = duration;
		datetime = LocalDateTime.now(); // �ٻݭnpoint price
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
