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
            // 비영속
            Member member = new Member();
            member.setId(1L);
            member.setName("Seul");

            // 영속 (아직 DB에 저장x, 1차 캐시에 저장됨)
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            // 준영속 (영속성 컨텍스트에서 지움)
            em.detach(member);
            */

            /* 회원 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            /* 회원 삭제
            // 삭제 (DB 삭제 요청)
            em.remove(findMember);
            */

            /* 회원 수정
            findMember.setName("yu");
            */

            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(0).setMaxResults(8).getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            */

            /* 1차 캐시에서 조회 확인
            Member findMember1 = em.find(Member.class, 10L);
            Member findMember2 = em.find(Member.class, 10L);
            System.out.println("findMember1.id = " + findMember1.getId());
            System.out.println("findMember1.name = " + findMember1.getName());
            System.out.println("result = " + (findMember1 == findMember2));
            */

            /*
            em.flush();
            -> 영속성 컨텍스트의 변경 내용을 DB에 반영(동기화)
            -> 데이터베이스 트랜잭션이 커밋될 때 자동으로 일어난다. em.flush(); 직접 사용 거의 안함

            준영속 상태로 만들기 (더이상 JPA 에서 관리 안함) 직접 사용 거의 안함
            -> 영속 상태의 엔티티 영속성 컨텍스트에서 분리
            em.detach(entity);
            em.clear(); -> 영속성 컨텍스트 초기화
            em.close(); -> 영속성 컨텍스트 종료
            */

            Member member = em.find(Member.class, 20L);
            member.setName("Y");

            System.out.println("========="); // 트랜잭션 커밋 후 insert쿼리 보냄

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
