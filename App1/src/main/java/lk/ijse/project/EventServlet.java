package lk.ijse.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/www")
public class EventServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/eventdb", "root","Ijse@1234");
            ResultSet resultSet=connection.prepareStatement("select * from events").executeQuery();
            List<Map<String,String>> elist=new ArrayList<>();
            while (resultSet.next()) {
                Map<String,String> event=new HashMap<String,String>();
                event.put("eid",resultSet.getString("eid"));
                event.put("ename",resultSet.getString("ename"));
                event.put("edescription",resultSet.getString("edescription"));
                event.put("edate",resultSet.getString("edate"));
                event.put("eplace",resultSet.getString("eplace"));
                elist.add(event);

            }
            resp.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), elist);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> event = mapper.readValue(req.getInputStream(), Map.class);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO events (eid,ename, edescription, edate, eplace) VALUES (?, ?, ?, ?,?)"
            );
            stmt.setString(1, event.get("eid"));
            stmt.setString(2, event.get("ename"));
            stmt.setString(3, event.get("edescription"));
            stmt.setString(4, event.get("edate"));
            stmt.setString(5, event.get("eplace"));

            int rows = stmt.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> event = mapper.readValue(req.getInputStream(), Map.class);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE events SET ename = ?, edescription = ?, edate = ?, eplace = ? WHERE eid = ?"
            );
            stmt.setString(1, event.get("ename"));
            stmt.setString(2, event.get("edescription"));
            stmt.setString(3, event.get("edate"));
            stmt.setString(4, event.get("eplace"));
            stmt.setString(5, event.get("eid"));

            int rows = stmt.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> body = mapper.readValue(req.getInputStream(), Map.class);
        String eid = body.get("eid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");



            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            PreparedStatement stmt = connection.prepareStatement("DELETE FROM events WHERE eid = ?");
            stmt.setString(1, eid);

            int rows = stmt.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
