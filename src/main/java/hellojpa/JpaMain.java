package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("memberA");
//            member.setTeamId(team.getId()); // 외래 키 식별자를 직접 다룸
            member.setTeam(team); // 단방향 연관관계 설정, 참조 저장
            em.persist(member);

            // 영속성 컨텍스트 말고 db에서 가져오는 쿼리
//            em.flush(); // 영속성 컨텍스트 -> db 반영
//            em.clear(); // 영속성 컨텍스트 초기화

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam(); // 참조를 사용해서 연관관계 조회
            System.out.println("findTeam = " + findTeam.getName());

            // 연관관계 수정 (100번인 팀으로 change)
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
