package test.example.HibernateMappingExample;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		System.out.println("hellooo");
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");

		try (SessionFactory factory = con.buildSessionFactory(); Session session = factory.openSession()) {
			Transaction t = session.beginTransaction();

			Teacher teacher1 = new Teacher();
			teacher1.setTname("Vaibhav Sir");

			Teacher teacher2 = new Teacher();
			teacher2.setTname("Megha Mam");

			Student student1 = new Student();
			student1.setSname("shital");
			student1.setTid(teacher1);

			Address address1 = new Address();
			address1.setCity("Pune");
			address1.setStreet("Pimple Gurav");
			address1.setSid(student1);
			student1.setAddress(address1);// address set to student

			Student student2 = new Student();
			student2.setSname("shubham");
			student2.setTid(teacher1);

			Address address2 = new Address();
			address2.setCity("Pune");
			address2.setStreet("Wagholi");
			address2.setSid(student2);
			student2.setAddress(address2);

			Student student3 = new Student();
			student3.setSname("Arnav");
			student3.setTid(teacher2);

			Address address3 = new Address();
			address3.setCity("Pune");
			address3.setStreet("Kothrud");
			address3.setSid(student3);
			student3.setAddress(address3);

			Student student4 = new Student();
			student4.setSname("Raj");
			student4.setTid(teacher2);

			Address address4 = new Address();
			address4.setCity("Pune");
			address4.setStreet("Sangvi");
			address4.setSid(student4);
			student4.setAddress(address4);

			Course course1 = new Course();
			course1.setCname("BE");

			Course course2 = new Course();
			course2.setCname("MCA");

			Set<Student> course1Students = new HashSet<>();
			course1Students.add(student1);
			course1Students.add(student2);
			course1.setStudents(course1Students);

			Set<Student> course2Students = new HashSet<>();
			course2Students.add(student3);
			course2Students.add(student4);
			course2.setStudents(course2Students);

			session.save(course1);
			session.save(course2);

			// add courses
			Set<Course> student1Courses = new HashSet<>();
			student1Courses.add(course1);
			student1Courses.add(course2);
			student1.setCourses_id(student1Courses);

			Set<Course> student2Courses = new HashSet<>();
			student2Courses.add(course1);
			student2.setCourses_id(student2Courses);

			Set<Course> student3Courses = new HashSet<>();
			student3Courses.add(course2);
			student3.setCourses_id(student3Courses);

			Set<Course> student4Courses = new HashSet<>();
			student4Courses.add(course2);
			student4.setCourses_id(student4Courses);

			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(student4);

			teacher1.setStudents(Arrays.asList(student1, student2));
			session.save(teacher1);

			teacher2.setStudents(Arrays.asList(student3, student4));
			session.save(teacher2);

			t.commit();

			// Teacher-student one-to-many mapping
			List<Teacher> teachers = session.createQuery("FROM Teacher", Teacher.class).list();
			for (Teacher teacher : teachers) {
				System.out.println("Teacher: " + teacher.getTname());
				System.out.println("Students with Address:");
				for (Student student : teacher.getStudents()) {
					System.out.println(" " + student + ", Address: " + student.getAddress());
				}
				System.out.println();
			}

			//// Student-course many-to-many mapping
			// Print courses in each student
			List<Student> students = session.createQuery("FROM Student", Student.class).list();
			for (Student student : students) {
			    System.out.println("Student: " + student.getSname());
			    System.out.println("Courses:");
			    for (Course course : student.getCourses_id()) {
			        System.out.println(course);
			    }
			    System.out.println();
			}
			
			// Print students in each course
			List<Course> courses = session.createQuery("FROM Course", Course.class).list();
			for (Course course : courses) {
				System.out.println("Course: " + course);
				System.out.println("With Students:");
				for (Student student : course.getStudents()) {
					System.out.println( student);
				}
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
