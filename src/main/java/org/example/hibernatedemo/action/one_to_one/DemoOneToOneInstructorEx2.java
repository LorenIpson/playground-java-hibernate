package org.example.hibernatedemo.action.one_to_one;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 知道 1 號是 Andy，想要知道他的 Email。
 */
public class DemoOneToOneInstructorEx2 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Instructor instructor = session.find(Instructor.class, 1);

            if (instructor != null) {
                InstructorDetail instructorDetail = instructor.getInstructorDetail();
                if (instructorDetail.getInstructor() != null) {
                    System.out.print("有這位使用者，他的 Email 是：");
                    System.out.println(instructorDetail.getEmail());
                } else {
                    System.out.println("這個 ID 有使用者，但是沒有填寫 Email。");
                }
            } else {
                System.out.println("沒有這位使用者。");
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
