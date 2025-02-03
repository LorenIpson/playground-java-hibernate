package org.example.hibernatedemo.action;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 沒有 cascade 狀況下新增資料且做關聯。
 */
public class DemoOneToOneInstructorEx6 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor ins1 = new Instructor();
            InstructorDetail ins1Detail = new InstructorDetail();

            /* fk_instructorDetail_id 是 null。
            ins1.setName("Carlos");
            session.persist(ins1);

            ins1Detail.setInstructor(ins1); // 這一行的關係。
            ins1Detail.setPhone("0987123456");
            ins1Detail.setEmail("Carlos@mail.com");
            session.persist(ins1Detail);
             */

            ins1.setName("Giovanni");
            session.persist(ins1);

            ins1Detail.setPhone("0987456123");
            ins1Detail.setEmail("Giovanni@mail.com");
            // 要設計在 主表物件 連結至附屬表格。
            // persist 的順序並不重要，只要在 Commit 之前即可。
            ins1.setInstructorDetail(ins1Detail);
            session.persist(ins1Detail);

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
