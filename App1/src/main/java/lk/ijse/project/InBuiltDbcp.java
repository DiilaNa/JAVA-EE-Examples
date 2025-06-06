package lk.ijse.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/inbuild")
public class InBuiltDbcp extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = ds.getConnection();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
