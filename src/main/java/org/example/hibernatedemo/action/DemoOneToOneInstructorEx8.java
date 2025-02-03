package org.example.hibernatedemo.action;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 只想刪除 Detail，但是 instructor 要留下。只知道 instructor ID 是 5。
 */
public class DemoOneToOneInstructorEx8 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor ins5 = session.find(Instructor.class, 5);
            InstructorDetail ins5Detail = ins5.getInstructorDetail();

            // 刪除前需要將主表對應到附表的外鍵設定為 null。
            //ins5Detail.setInstructor(null); 這是錯誤的。
            ins5.setInstructorDetail(null);
            session.remove(ins5Detail);

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
