import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOB")
    Date dateOfBirth;

    @Embedded
    Address address;


    @ElementCollection
    List<String> subjects = new ArrayList<String>();

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
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

@Embeddable
class Address {

    int streetNumber;
    String location;
    String State;
}

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();

            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            // 3. Get Session object
            Session session = sessionFactory.openSession();

            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();

            Author author1 = new Author();
            Author author2 = new Author();
            Author author3 = new Author();
            Author author4 = new Author();
            author1.setFirstName("Stephen").setLastName("King").setAge(35);
            author2.setFirstName("J.K.").setLastName("Rowling").setAge(39);
            author3.setFirstName("Charles").setLastName("Dickens").setAge(48);
            author4.setFirstName("William").setLastName("Shakespeare").setAge(50);

            session.save(author1);
            session.save(author2);
            session.save(author3);
            session.save(author4);

            transaction.commit();

            sessionFactory.close();

            System.out.println("Done");


        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("error");
        }
    }
}