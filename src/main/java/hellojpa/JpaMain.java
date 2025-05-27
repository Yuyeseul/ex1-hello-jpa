package hellojpa;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("ys");
            member.setHomeAddress(new Address("homeCity", "street", "12345"));

            member.getFavoritesFoods().add("족발");
            member.getFavoritesFoods().add("고기");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "12345"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "12345"));

            em.persist(member);

            em.flush(); // 영속성 컨텍스트 -> db 반영
            em.clear(); // 영속성 컨텍스트 초기화

            System.out.println("============== START ==============");
            Member findMember = em.find(Member.class, member.getId());

//            List<AddressEntity> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoritesFoods = findMember.getFavoritesFoods();
//            for (String favoritesFood : favoritesFoods) {
//                System.out.println("favoritesFood = " + favoritesFood);
//            }
//
//            // homeCity -> newCity
//            Address address = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", address.getStreet(), address.getZipcode()));
//
//            // 치킨 -> 한식
//            findMember.getFavoritesFoods().remove("치킨");
//            findMember.getFavoritesFoods().add("한식");
//
//            // old1 -> newCity1
//            findMember.getAddressHistory().remove(new Address("old1", "street", "12345"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "12345"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
