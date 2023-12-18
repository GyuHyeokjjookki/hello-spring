package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
        //home.html을 호출, 기존에 만들었던 static의 index.html은 무시됨. 우선순위가 있기 때문.
    }
}
