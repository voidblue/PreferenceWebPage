import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Poll extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp){

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        
        BufferedReader bufferedReader = null;
        try {
            bufferedReader= new BufferedReader(new FileReader("poll.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s;

        try {
            PrintWriter printWriter = resp.getWriter();
            if ((s =bufferedReader.readLine())!=null){
                printWriter.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
