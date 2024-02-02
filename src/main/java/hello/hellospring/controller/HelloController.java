package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // 스프링에서는 해당 어노테이션을 추가해야한다.
public class HelloController {

    @GetMapping("hello")  // /hello로 엔드 포인트 지정하면 해당 메서드를 호출한다.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");  // key : data, value = hello!!
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {  // url을 파라미터로 받도록 해준다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  // http header와 body 중 body에 데이터를 직접 넣기 위해 사용한다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();  // 객체 생성
        hello.setName(name);
        return hello;  // 객체를 반환.
    }

    static class Hello {
        private String name;

        public String getName() {  // 데이터를 꺼내온다.
            return name;
        }

        public void setName(String name) {  // 데이터를 넣어준다.
            this.name = name;
        }
    }
}
