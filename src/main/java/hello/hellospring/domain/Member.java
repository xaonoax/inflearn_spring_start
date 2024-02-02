package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity  // jpa가 관리하는 엔티티.
public class Member {

    @Id  // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id의 값을 자동으로 생성해준다. (이런 걸 Identity 전략이라고 함)
    private Long id;  // 고객이 정하는 id가 아니라 시스템(DB 등)에 저장하는 임의의 값.

    // @Column(name = "username")  // 만약 DB의 컬럼명이 username일 경우 이렇게 설정하면 동일한 DB의 컬럼과 매핑해준다.
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

}