package demo_jdbc.respositories;

import demo_jdbc.models.Constructor;
import Conn.ConexionSql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConstructorRepository {
	
	private ConexionSql conexionSql = new ConexionSql();

    public List<Constructor> getAllConstructors() {
        List<Constructor> constructors = new ArrayList<>();

        try {
            Connection conn = conexionSql.getConexion();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM constructors";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int constructorId = rs.getInt("constructorId");
                String constructorRef = rs.getString("constructorRef");
                String name = rs.getString("name");
                String nationality = rs.getString("nationality");
                String url = rs.getString("url");

                Constructor constructor = new Constructor(constructorId, constructorRef, name, nationality, url);
                constructors.add(constructor);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return constructors;
    }

}
