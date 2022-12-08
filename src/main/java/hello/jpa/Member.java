package hello.jpa;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매칭할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50 // 미리 db에 50개 올려놓고 메모리에서 1씩 쓴다
)
public class Member {

    @Id @GeneratedValue(generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")
    private String userName;

    public Member(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
