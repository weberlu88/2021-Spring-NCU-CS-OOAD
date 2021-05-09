package mob_movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** �q�v�����T�����O�A�����P���p���B����: �s���B�¤��B�ൣ���C�Hstrategy pattern��F�C **/
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

/* �s��$3 �i�H���@�ѡA�C�h�@�ѡA�@�ѬO$2�C�s���C���@���A�`�Ȱ��I 2.0 */
class NewFilm extends Category {
	public NewFilm() {
		setNormalPrice(3);
		setExtraPrice(2);
		setRentDurationLimit(1);
		setPoint(2.0);
	}
}

/* �¤�$2 �i�H���T�ѡA�C�h�@�ѡA�@�ѬO$1�A�`�Ȱ��I 1.0 */
class OldFilm extends Category {
	public OldFilm() {
		setNormalPrice(2);
		setExtraPrice(1);
		setRentDurationLimit(3);
		setPoint(1.0);
	}
}

/* �ൣ��$2 �i�H��5�ѡA�C�h�@�ѡA�@�ѬO$1�A�`�ȿn�I 0.5 */
class KidFilm extends Category {
	public KidFilm() {
		setNormalPrice(2);
		setExtraPrice(1);
		setRentDurationLimit(5);
		setPoint(0.5);
	}
}

/** �q�v **/
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

/** �U�� **/
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

/** �@����������  �q�v:�U�Ȫ�m:n���p�� **/
class Rental {
	private Customer customer;
	private Movie movie;
	private LocalDateTime datetime; // ���O�ɶ�
	private double point; // �浧�ֿn�I��
	private int price; // �浧���O���B
	private int duration; // �Ӥ����ɴX��
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public Rental(Customer customer, Movie movie, int duration) {
		this.customer = customer;
		this.movie = movie;
		this.duration = duration;
		datetime = LocalDateTime.now(); // ��ɯ���
		point = movie.getPoint(); // ��o�I��
		price = calculatePrice(movie, duration); // ��������F�h�ֿ�
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
	
	// �p�⯲duration�ѡA�Ӥ��n�h�ֿ�
	private int calculatePrice(Movie movie, int duration) {
		int basic = 0, extra = 0;
		if (duration <= movie.getRentDurationLimit()) 
			basic = movie.getNormalPrice() * duration; // �򥻤Ѽƪ�����
		else {
			basic = movie.getNormalPrice() * movie.getRentDurationLimit(); // �򥻤Ѽƪ�����
			extra = movie.getExtraPrice() * (duration - movie.getRentDurationLimit()); // �B�~�Ѽƪ�����
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

public class Main {

	public static void main(String[] args) {

		Customer april = new Customer("April");
		Customer joe = new Customer("Joe");
		
		Movie ���O�k�W�H = new Movie("���O�k�W�H", new OldFilm()); // old
		Movie ���q�p�� = new Movie("���q�p��", new NewFilm()); // new
		Movie �p������ = new Movie("�p������", new NewFilm()); // new
		
		List<Rental> rents = new ArrayList<Rental>();
		rents.add(new Rental(april, ���O�k�W�H, 3));
		rents.add(new Rental(joe, ���O�k�W�H, 2));
		rents.add(new Rental(april, ���q�p��, 5));
		rents.add(new Rental(joe, �p������, 1));
		
		for (Rental r: rents) {
			System.out.println(r.toString());
		}
		
		// �L����

	}

}
