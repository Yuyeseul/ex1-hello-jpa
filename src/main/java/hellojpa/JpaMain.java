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
            /* 회원 등록
            Member member = new Member();
            member.setId(1L);
            member.setName("Seul");
            em.persist(member);

            /* 회원 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            /* 회원 수정
            findMember.setName("yu");
            */

            /* 회원 삭제
            em.remove(findMember);
            */

            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(0).setMaxResults(8).getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
