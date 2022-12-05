package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            // 영속
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

//            em.persist(member);

//            영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화
//            em.flush();

//            특정 엔티티만 준영속 상태로 전환(더 이상 JPA에서 관리 X)
//            em.detach(member);
//            영속성 컨텍스트를 완전히 초기화
//            em.clear();
//            영속성 컨텍스트를 종료
//            em.close();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
