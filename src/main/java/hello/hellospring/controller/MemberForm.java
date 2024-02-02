package hello.hellospring.controller;

public class MemberForm {

    private String name;  // MemberForm의 name과 createMemberForm의 name="name"이 매칭이 되면서 값이 들어온다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
