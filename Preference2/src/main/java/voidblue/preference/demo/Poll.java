package voidblue.preference.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class Poll {
    @GetMapping("/")
    public  String poll(Model model, @RequestParam("id") String id) throws SQLException, ClassNotFoundException, IOException {



        return "poll";

    }
}