package lk.ijse.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
@MultipartConfig
@WebServlet("/customer")
public class MimeTypes extends HttpServlet {
    //      read text / plain data from http request body
   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String body = new BufferedReader(new InputStreamReader(
                req.getInputStream()))
                .lines().collect(Collectors.joining("/n"));
        resp.setContentType("text/plain");
        resp.getWriter().write(body);
    }*/

    /*Read x-www-form-urlencoded data from a http req*/
   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        resp.setContentType("text/plain");
        resp.getWriter().println(name + " " + address);
    }*/


    /*Read multipart/form data from a http request*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Part part = req.getPart("file");
        String filename = part.getSubmittedFileName();

        resp.setContentType("text/plain");
        resp.getWriter().write(name + " " + filename);

    }
}