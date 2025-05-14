package hellojpa;

import jakarta.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    // 연관관계 매핑 <일대일 양방향>
    @OneToOne(mappedBy = "locker")
    private Member member;
}
