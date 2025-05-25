package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("userA");

            em.persist(member);

            em.flush(); // 영속성 컨텍스트 -> db 반영
            em.clear(); // 영속성 컨텍스트 초기화

//            Member findMember = em.find(Member.class, member.getId());
            Member refMember = em.getReference(Member.class, member.getId()); // proxy 객체 조회
            System.out.println("refMember : " + refMember.getClass()); // proxy

//            System.out.println("refMember instanceof Member = " + (refMember instanceof Member)); // 타입 체크 시 instanceof 사용
//            refMember.getUsername(); // proxy 초기화 됨
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // 프록시 인스턴스의 초기화 여부
//            Hibernate.initialize(refMember); // 강제 초기화

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
