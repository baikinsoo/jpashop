package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 스프링 빈으로 스프링이 등록해준다 @Component가 속해있다.
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
    //위의 어노테이션이 JPA EntityManager를 스프링이 생성해서 주입해준다.

    //스프링 부트에 의해서 @Autowired Entity에도 @Autowired 사용이 가능하다. 때문에 일관성을 위해 @RequriedArgsConstructor를 사용할 수 있다.
    private final EntityManager em; // 생성자 주입 방식이 가능하다. EntityManager는 원래 @PersistenceContext를 사용해야 하는데 스프링 데이터 JPA가 @Autowired를 허용해준다.
    // 위 두 줄(@PersistenceContext를 포함했을 때, 하지만 추후에 @RAC를 작성함)을 작성하면 스프링이 엔티티 매니저를 생성해서 주입해준다. @PersistenceContext 덕분인듯

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // createQuery를 사용하여 첫번째 파라미터에는 jpql을 두번째 파라미터에는 반환값을 넣고, getResultList()로 결과값을 List로 받는다.
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                // :name => "name"
                .setParameter("name", name)
                .getResultList();
    }
}
