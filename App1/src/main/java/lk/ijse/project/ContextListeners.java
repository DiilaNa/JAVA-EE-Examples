package lk.ijse.project;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class ContextListeners implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/eventdb");
        ds.setUsername("root");
        ds.setPassword("Ijse@1234");
        ds.setInitialSize(50);
        ds.setMaxTotal(100);

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("dataSource", ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();
            BasicDataSource ds = (BasicDataSource) sc.getAttribute("dataSource");
            ds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
