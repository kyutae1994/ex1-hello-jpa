package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 어플리케이션 로딩 시점에 딱 하나만 만들어야됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 예를 들어 행위를 하고 나가는 경우 db 커넥션을 얻어서 쿼리 날리고 종료할때마다
        // 데이터베이스 커넥션 하나 받았다고 생각하면 된다.
        // 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.close();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUserName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
