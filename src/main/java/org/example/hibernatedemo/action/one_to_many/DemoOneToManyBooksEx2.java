package org.example.hibernatedemo.action.one_to_many;

import org.example.hibernatedemo.model.one_to_many.Books;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 3 號借閱紀錄、歸還。<br>
 * 從使用者的借閱中清單移除。
 */
public class DemoOneToManyBooksEx2 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Books book3 = session.find(Books.class, 3);
            session.remove(book3);

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
