package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", value = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 파라미터 이름이 다르면 다 뽑아줌 BUT 이름이 같으면 하나만 조회
        System.out.println("[전체 파라미터 조회] - start"); 
        request.getParameterNames().asIterator().forEachRemaining(paramName -> {
            System.out.println(paramName + "= " + request.getParameter(paramName));
        });
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();


        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();


        // 이름이 같은 파라미터의 값을 조회
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("name = " + name);
        }
        System.out.println();

        String[] ages = request.getParameterValues("age");
        for (String num : ages) {
            System.out.println("num = " + num);
        }

        response.getWriter().print("OK");

    }
}
