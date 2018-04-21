package voidblue.preference.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/")
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }


}
