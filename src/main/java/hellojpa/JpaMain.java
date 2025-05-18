package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("무비");
            movie.setPrice(10000);

            em.persist(movie);

            Book book = new Book();
            book.setAuthor("zzz");
            book.setIsbn("ll");
            book.setName("book");
            book.setPrice(10000);

            em.persist(book);

            em.flush(); // 영속성 컨텍스트 -> db 반영
            em.clear(); // 영속성 컨텍스트 초기화

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

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
