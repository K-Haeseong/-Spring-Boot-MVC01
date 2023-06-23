package hello.servlet.basic.respones;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.request.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", value = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData data = new HelloData();
        data.setUsername("kim");
        data.setAge(25);

        // Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString() 를사용하면 객체를 JSON 문자로변경할 수 있다.
        String result = objectMapper.writeValueAsString(data);

        response.getWriter().print(result);


    }
}
