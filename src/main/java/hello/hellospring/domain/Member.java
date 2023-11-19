package hello.hellospring.domain;

public class Member {

    private Long id;
    //데이터 구분을 위한 식별자
    private String name;
    //name은 그냥 이름

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
    //단순한 예제이기에 getter, setter를 생성
}
