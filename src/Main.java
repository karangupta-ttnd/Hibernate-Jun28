import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    String firstName, lastName;
    int age;

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

            transaction=session.beginTransaction();
            Author someAuthor = session.get(Author.class, 4);
            System.out.println(someAuthor.toString());
            someAuthor.setAge(45);
            System.out.println(someAuthor.toString());
            session.update(someAuthor);
            transaction.commit();

            transaction= session.beginTransaction();
            Author someOtherAuthor = session.get(Author.class, 1);
            session.delete(someOtherAuthor);
            transaction.commit();

            System.out.println("Done");


        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("error");
        }
    }
}