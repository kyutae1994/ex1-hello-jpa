package hello.jpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP) // DATE 날짜, TIME 시간, TIMESTAMP 날짜+시간
    private LocalDateTime lastModifiedDate;

    @Lob // 글자 긴거 넣을 때
    private String description;

    @Transient // 필드 매핑 X
    private String temp;

    public Member(){}

}
