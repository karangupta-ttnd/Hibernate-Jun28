import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;




@Embeddable
class Address {

    int streetNumber;
    String location;
    String State;

    public Address setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public Address setLocation(String location) {
        this.location = location;
        return this;
    }

    public Address setState(String state) {
        State = state;
        return this;
    }


}


@Entity
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Author_ID")
    int id;

    @Column(name = "FirstName")
    String firstName;

    @Transient
    @Column(name = "LastName")
    String lastName;

    @Column(name = "Age")
    int age;

    @Temporal(TemporalType.DATE)
    @Column(name = "DOB")
    Date dateOfBirth;

    @Embedded
    Address address;

    @ElementCollection
    List<String> subjects = new ArrayList<String>();

    public List<String> getSubjects() {
        return subjects;
    }

    public Author setSubjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;

    }


    public Date getdateOfBirth() {
        return dateOfBirth;
    }

    public void setdateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getFirstName() {
        return firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Author setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName +
                "', lastName='" + lastName + '\'' + ", age='" + age + "'}";
    }

}


public class Main {

    public static void main(String[] args) throws Exception {

        Author author1 = new Author();
        Address address1 =new Address();

        Author author2 = new Author();
        Address address2 =new Address();

        Author author3 = new Author();
        Address address3 =new Address();

        Author author4 = new Author();
        Address address4 =new Address();

        List<String> someSubjects =new ArrayList<String>();


        try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();

            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            // 3. Get Session object
            Session session = sessionFactory.openSession();

            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();

            address1.setStreetNumber(35);
            address1.setLocation("Malviya Nagar");
            address1.setState("Delhi");
            someSubjects.add("Fiction");
            someSubjects.add("Sci-Fi");
            someSubjects.add("Horror");
            author1.setFirstName("Stephen").setLastName("King").setAge(35).setSubjects(someSubjects).setAddress(address1);

            someSubjects.clear();
            address2.setStreetNumber(25);
            address2.setLocation("Ludhiana");
            address2.setState("Punjab");
            someSubjects.add("Magic");
            someSubjects.add("Thriller");
            someSubjects.add("Comedy");
            author2.setFirstName("J.K.").setLastName("Rowling").setAge(39).setSubjects(someSubjects).setAddress(address2);

            someSubjects.clear();
            address3.setStreetNumber(36);
            address3.setLocation("Malviya Nagar");
            address3.setState("Delhi");
            someSubjects.add("Sci-Fi");
            someSubjects.add("Thriller");
            someSubjects.add("Comedy");
            author3.setFirstName("Charles").setLastName("Dickens").setAge(48).setSubjects(someSubjects).setAddress(address3);

            someSubjects.clear();
            address4.setStreetNumber(13);
            address4.setLocation("Subash Nagar");
            address4.setState("Delhi");
            someSubjects.add("Love");
            someSubjects.add("Drama");
            someSubjects.add("Poetry");
            author4.setFirstName("William").setLastName("Shakespeare").setAge(50).setSubjects(someSubjects).setAddress(address4);

            session.save(author1);
            session.save(author2);
            session.save(author3);
            session.save(author4);

            transaction.commit();

//            sessionFactory.close();

            System.out.println("Done");


        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("error");
        }
    }
}