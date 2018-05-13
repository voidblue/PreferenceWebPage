package voidblue.preference.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.sql.SQLException;

@Controller
public class Poll {
    @GetMapping("/poll")
    public  String poll(@RequestParam("id") String id){
        return "poll";
    }
}