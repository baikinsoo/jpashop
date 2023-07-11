package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    public List<Order> getOrders() {
        return orders;
    }

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //order 테이블에 있는 member 필드에 의해 매핑된 것이라는 뜻이다. - 읽기 전용
    private List<Order> orders = new ArrayList<>();
}
