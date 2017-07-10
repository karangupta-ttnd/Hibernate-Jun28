import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    @Column(name = "itsAuthor")
    @ManyToMany(mappedBy = "authorBook")
    private List<Author> authorList = new ArrayList<Author>();


    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}


@Embeddable
class Address {

    private int streetNumber;
    private String location;
    private String State;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Author_ID")
    private int id;

    @Column(name = "FirstName")
    private String firstName;

    @Transient
    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Age")
    private int age;

    @Temporal(TemporalType.DATE)
    @Column(name = "DOB")
    private Date dateOfBirth;

    @Embedded
    private Address address;

    @ElementCollection
    private List<String> subjects = new ArrayList<String>();

    @Column(name = "hisBooks")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> authorBook = new ArrayList<Book>();

    public List<Book> getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(List<Book> authorBook) {
        this.authorBook = authorBook;
    }

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

    public Author setAddress(Address address) {
        this.address = address;
        return this;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
        Address address1 = new Address();
        Book book1 = new Book();
        Book book2 = new Book();

        Author author2 = new Author();
        Address address2 = new Address();

        Author author3 = new Author();
        Address address3 = new Address();
        Book book3 = new Book();

        Author author4 = new Author();
        Address address4 = new Address();


        List<String> listOfSubjects1 = new ArrayList<String>();
        List<String> listOfSubjects2 = new ArrayList<String>();
        List<String> listOfSubjects3 = new ArrayList<String>();
        List<String> listOfSubjects4 = new ArrayList<String>();
        List<Book> listOfBooks1 = new ArrayList<Book>();
        List<Book> listOfBooks3 = new ArrayList<Book>();
        List<Author> listOfAuthors1 = new ArrayList<Author>();
        List<Author> listOfAuthors3 = new ArrayList<Author>();


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
            listOfSubjects1.add("Fiction");
            listOfSubjects1.add("Sci-Fi");
            listOfSubjects1.add("Horror");

            book1.setBookName("GenericTitle1");
            book2.setBookName("GenericTitle2");

            listOfBooks1.add(book1);
            listOfBooks1.add(book2);

            listOfAuthors1.add(author1);
            book1.setAuthorList(listOfAuthors1);
            book2.setAuthorList(listOfAuthors1);

            author1.setFirstName("Stephen").setLastName("King").setAge(35).setSubjects(listOfSubjects1).setAddress(address1).setAuthorBook(listOfBooks1);


            address2.setStreetNumber(25);
            address2.setLocation("Ludhiana");
            address2.setState("Punjab");

            listOfSubjects2.add("Magic");
            listOfSubjects2.add("Thriller");
            listOfSubjects2.add("Comedy");

            author2.setFirstName("J.K.").setLastName("Rowling").setAge(39).setSubjects(listOfSubjects2).setAddress(address2);


            address3.setStreetNumber(36);
            address3.setLocation("Malviya Nagar");
            address3.setState("Delhi");

            listOfSubjects3.add("Sci-Fi");
            listOfSubjects3.add("Thriller");
            listOfSubjects3.add("Comedy");

            book3.setBookName("GenericTitle3");

            listOfAuthors3.add(author3);
            listOfBooks3.add(book3);

            book3.setAuthorList(listOfAuthors3);

            author3.setFirstName("Charles").setLastName("Dickens").setAge(48).setSubjects(listOfSubjects3).setAddress(address3).setAuthorBook(listOfBooks3);


            address4.setStreetNumber(13);
            address4.setLocation("Subash Nagar");
            address4.setState("Delhi");

            listOfSubjects4.add("Love");
            listOfSubjects4.add("Drama");
            listOfSubjects4.add("Poetry");

            author4.setFirstName("William").setLastName("Shakespeare").setAge(50).setSubjects(listOfSubjects4).setAddress(address4);

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