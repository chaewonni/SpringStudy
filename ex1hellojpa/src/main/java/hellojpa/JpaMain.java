package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

//            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            //영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member); //이 때 db에 저장되는 건 아님
//            System.out.println("=== AFTER ===");
//
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = " + (findMember1 == findMember2));

//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            //영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZ");
            //알아서 update 쿼리 날라감
            //em.persist(member)할 필요 없음.

//            em.persist(member1);
//            em.persist(member2);

            Member member = new Member(200L, "member200");
            em.persist(member); //실제 데이터베이스에 이 쿼리 안날라감

            em.flush(); //강제로 미리 데이터베이스에 반영 or 쿼리를 미리 보고 싶을 때

            System.out.println(" ========== ");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
