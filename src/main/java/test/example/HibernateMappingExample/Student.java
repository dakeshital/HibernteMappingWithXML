package test.example.HibernateMappingExample;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String sname;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher tid;

    @OneToOne(mappedBy = "sid", cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    private Set<Course> courses_id;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Teacher getTid() {
        return tid;
    }

    public void setTid(Teacher tid) {
        this.tid = tid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

   
	public Set<Course> getCourses_id() {
		return courses_id;
	}

	public void setCourses_id(Set<Course> courses_id) {
		this.courses_id = courses_id;
	}

	@Override
    public String toString() {
        return "Student [sid=" + sid + ", sname=" + sname + "]";
    }
}
