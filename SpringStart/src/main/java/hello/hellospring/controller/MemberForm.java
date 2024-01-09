package hello.hellospring.controller;

public class MemberForm {
    private String name;
    //html에서 name="name"을 보고 이 name에 넣어줌

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
