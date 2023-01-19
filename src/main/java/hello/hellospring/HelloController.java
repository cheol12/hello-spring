package hello.hellospring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        log.info("hello컨트롤러");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String hello1(Model model, @RequestParam(value = "name",required = false) String name){
    // @RequestParam(value = "name",required = false)에서
    // 파라미터 값이 안넘어와도 작동함
    // required의 기본값은 true => 해당 파라미터 값이 없으면 오류남.
        model.addAttribute("name",name);
        log.info("hello1컨트롤러");
        return "hello-template";
    }

    // ResponseBody 설명
    // http://localhost:8080/hello-mvc?name=spring 에서
    // ?name= 뒤에 들어갈 데이터를 직접 넣어주겠다.
    @GetMapping("/hello-string")
    @ResponseBody
    public String helloString(Model model, @RequestParam(value = "name") String name){
        model.addAttribute("name",name);
        log.info("hello1컨트롤러");
        return "hello " +name;  // => hello spring
    }   // ResponseBody에 넣을 것이 기본 문자라면 위(name)처럼 그대로 붙이면 됨

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // = return hello(name:spring)
    }   // ResponseBody에 넣을 것이 기본 객체라면 json형식으로 반환됨.
    static class Hello {
        private String name;

        // getter setter : 자바 bean 규약
        // property 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
