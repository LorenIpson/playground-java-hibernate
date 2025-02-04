package org.example.hibernatedemo.action.one_to_many;

import org.example.hibernatedemo.model.one_to_many.BookUsers;
import org.example.hibernatedemo.model.one_to_many.Books;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedList;

/**
 * 新增多筆資料。
 */
public class DemoOneToManyBooksEx1 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            BookUsers user1 = new BookUsers();
            user1.setName("Trump");

            Books book1 = new Books();
            book1.setTitle("Java 21");
            book1.setYear("2022");
            book1.setBookUsers(user1); // 多 set 1

            Books book2 = new Books();
            book2.setTitle("SQL Server 2022");
            book2.setYear("2024");
            book2.setBookUsers(user1); // 多 set 1

            Books book3 = new Books();
            book3.setTitle("MySQL");
            book3.setYear("2020");
            book3.setBookUsers(user1); // 多 set 1

            session.persist(user1);

            LinkedList<Books> books = new LinkedList<>();
            books.add(book1);
            books.add(book2);
            books.add(book3);

            user1.setBooks(books);

            session.getTransaction().commit();
            System.out.println("=======================================");
            System.out.println("YOS, committing");
            System.out.println("=======================================");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("=======================================");
            System.out.println("Nah, I'd rollback");
            System.out.println("=======================================");
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }
}
