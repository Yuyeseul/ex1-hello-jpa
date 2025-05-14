package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id // 기본키 매핑 - IDENTITY, SEQUENCE(@SequenceGenerator), TABLE(@TableGenerator), AUTO
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 연관관계 매핑 <일대일>
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // 연관관계 매핑 <일대다 양방향>
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 연관관계 주인 아님 -> 읽기 전용 필드 사용
    private Team team;

//    // 연관관계 매핑 <다대일>
//    @ManyToOne // 연관관계의 주인 (등록 수정)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
//
//    public Team getTeam() {
//        return team;
//    }
//
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this); // (연관관계 편의 메소드)
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}