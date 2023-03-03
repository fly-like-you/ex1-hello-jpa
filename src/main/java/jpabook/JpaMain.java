package jpabook;

import jpabook.domain.Member;
import jpabook.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);

            member.setTeam(team);


            em.flush();
            em.clear();
            System.out.println("-==============1=============");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("-==============2=============");

            List<Member> members = findMember.getTeam().getMembers();
            System.out.println("-==============3=============");

            for (Member m : members) {
                System.out.println("0000000000000000000000000000m = " + m.getName());
            }
            System.out.println("-==============4=============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
