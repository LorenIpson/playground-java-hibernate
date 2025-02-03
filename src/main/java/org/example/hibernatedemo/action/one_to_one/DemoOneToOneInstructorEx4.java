package org.example.hibernatedemo.action.one_to_one;

import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 只知道 Detail ID 是 101，想知道名字。
 */
public class DemoOneToOneInstructorEx4 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            InstructorDetail insDetail1 = session.find(InstructorDetail.class, 101);

            if (insDetail1 != null) {
                String name = insDetail1.getInstructor().getName();
                if (!name.isEmpty()) {
                    System.out.print("有使用者，他的名字是：");
                    System.out.println(name);
                } else {
                    System.out.println("這個 ID 有使用者，但沒有名字。");
                }
            } else {
                System.out.println("這個 ID 沒有使用者。");
            }

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
