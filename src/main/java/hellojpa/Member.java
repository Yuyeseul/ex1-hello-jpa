package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member extends BaseEntity {

    @Id // 기본키 매핑 - IDENTITY, SEQUENCE(@SequenceGenerator), TABLE(@TableGenerator), AUTO
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 연관관계 매핑 <일대다 양방향>
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 lazy 즉시로딩 eager
    @JoinColumn
    private Team team;

    // 연관관계 매핑 <다대다>
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    // 연관관계 매핑 <다대다 - 일대다>
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}