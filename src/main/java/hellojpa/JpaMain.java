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

//            Team team = new Team();
//            team.setName("TeamA");
////            team.getMembers().add(member); // 역방향(연관관계 주인이 아닌 team)만 연관관계 값 설정 -> 반영X
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("memberA");
//            member.changeTeam(team); // 연관관계 주인에 값 설정 -> 반영O, 역방향도 해주는게 좋음(연관관계 편의 메소드 사용)
//            em.persist(member);
//
//            // 영속성 컨텍스트 말고 db에서 가져오는 쿼리
//            em.flush(); // 영속성 컨텍스트 -> db 반영
//            em.clear(); // 영속성 컨텍스트 초기화
//
//            // 조회
//            Team findTeam = em.find(Team.class, team.getId());
//            List<Member> members = findTeam.getMembers();
//            for(Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }

            Member member = saveMember(em);

            Team team = new Team();
            team.setName("TeamA");

            team.getMembers().add(member);

            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static Member saveMember(EntityManager em) {
        Member member = new Member();
        member.setUsername("yu");

        em.persist(member);
        return member;
    }
}
