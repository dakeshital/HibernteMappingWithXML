package test.example.HibernateMappingExample;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String city;
	private String street;

	@OneToOne
	@JoinColumn(name = "student_id")
	private Student sid; // map name with Student address

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Student getSid() {
		return sid;
	}

	public void setSid(Student sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "Address [aid=" + aid + ", city=" + city + ", street=" + street + "]";
	}

}
