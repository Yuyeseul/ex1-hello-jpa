package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE") // DTYPE 에 엔티티 명이 들어감
public abstract class Item {

    /* 상속 관계 매핑
    JOINED 조인 전략
    SINGLE_TABLE 단일 테이블 전략
    TABLE_PER_CLASS 구현 클래스마다 테이블 전략
    */

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
