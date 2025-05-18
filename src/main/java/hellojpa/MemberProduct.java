package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MemberProduct {

    // 다대다 매핑 한계 극복 -> 연결 테이블용 엔티티 추가하기

    @Id
    @GeneratedValue
    private Long id;

    // 연관관계 매핑 <다대다 - 다대일>
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;

    private LocalDateTime orderDateTime;
}
