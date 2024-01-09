package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // spring framework 를 사용하기 위해
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 /hello로 들어오면 해당 메서드 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        // attribute 의 이름이 "data"인 곳에 value 로 "hello!!" 가 들어감
        return "hello"; //hello.html가서 실행시켜라, 렌더링해라
    }

    @GetMapping("hello-mvc") //웹 브라우저에 칠 때 /hello-mvc로 들어오는 것
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API방식, 템플릿 엔진과 다르게 template이 필요없이 값을 그대로 내보내 준다.
    @GetMapping("hello-string")
    @ResponseBody //http의 body부에 "hello" + name; 이 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // String name에서 name에 spring을 넣으면 "hello spring"으로 바뀜
        //문자가 요청한 client에 그냥 내려감 (view 이런게 없음)
    }

    @GetMapping("hello-api")
    @ResponseBody // http의 body에 param으로 받은 data를 직접 넣어줌
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // hello 객체를 return
    }
    //Hello는 반환 타입을 나타냅니다. 이 메서드는 hello라는 객체를 반환합니다.
    // 즉, 클라이언트로부터 받은 "name" 파라미터를 사용하여 hello 객체를 생성하고, 이 객체를 반환합니다.
    // 이렇게 반환된 hello 객체는 JSON 형식으로 변환되어 HTTP 응답의 body에 넣어 클라이언트에게 반환됩니다.

    static class Hello{
        private String name; // private 라서 메서드를 사용해 활용

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




}
