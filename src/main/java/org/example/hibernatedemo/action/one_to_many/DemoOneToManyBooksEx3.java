package org.example.hibernatedemo.action.one_to_many;

import org.example.hibernatedemo.model.one_to_many.BookUsers;
import org.example.hibernatedemo.model.one_to_many.Books;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.LinkedList;

/**
 * 2 號 user 想多借一本 Microsoft 365 2022。
 * */
public class DemoOneToManyBooksEx3 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            BookUsers user2 = session.find(BookUsers.class, 2);

            // 若希望從「一對多」那邊管理書籍清單，就必須操作集合，但要留意不覆蓋原本的書單。
            // 若只關注將新書加入資料庫，直接設定「多對一」關聯（程式碼二）即可。

            /* 可能會覆蓋原有的書單
            Books book3 = new Books();
            book3.setTitle("Microsoft 365 2022");
            book3.setYear("2022");
            book3.setBookUsers(user2);

            LinkedList<Books> newBook = new LinkedList<>();
            newBook.add(book3);

            user2.setBooks(newBook);
            */

            Books book4 = new Books();
            book4.setTitle("Blender 2022");
            book4.setYear("2022");
            session.persist(book4);

            book4.setBookUsers(user2); // 多 set 1

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
