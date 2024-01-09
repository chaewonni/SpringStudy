package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member { //jpa가 관리하는 entity

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //임의의 값, 고객이 저장하는 것이 아니라 시스템이 저장하는 값
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //getter setter
}
