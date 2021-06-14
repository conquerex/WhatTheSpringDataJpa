package what.the.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    // protected를 쓰는 이유
    // proxy를 쓸때 private로 막혀있으면 안되므로
    protected Member() { }

    public Member(String username) {
        this.username = username;
    }

    @Id @GeneratedValue
    private Long id;
    private String username;
}
