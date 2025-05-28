package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("yu");
            em.persist(member);

            /* JPQL
            List<Member> result = em.createQuery("select m From Member m where m.username like '%hello%'",
                    Member.class).getResultList();
             */

            /* Criteria
            // Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            // 루트 클래스 (조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);

            // 쿼리 생성
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "hello"));
            List<Member> resultList = em.createQuery(cq).getResultList();
             */

            /* native sql
            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME, endDate, startDate from MEMBER",
                    Member.class).getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }
             */

            // flush -> commit, query

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
