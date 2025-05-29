package lk.ijse.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/lifecycles")
public class ServletLifeCycle extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Servlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet doGet");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroy");
    }
}
