package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("ys");
            member.setHomeAddress(new Address("city", "street", "12345"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            em.flush(); // 영속성 컨텍스트 -> db 반영
            em.clear(); // 영속성 컨텍스트 초기화

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
