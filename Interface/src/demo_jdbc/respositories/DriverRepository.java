package demo_jdbc.respositories;

import demo_jdbc.models.Driver;
import Conn.ConexionSql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DriverRepository {
	
	private ConexionSql conexionSql = new ConexionSql();

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();

        try {
            Connection conn = conexionSql.getConexion();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM drivers";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int driverId = rs.getInt("driverId");
                String driverRef = rs.getString("driverRef");
                int number = rs.getInt("number");
                String code = rs.getString("code");
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                Date dob = rs.getDate("dob");
                String nationality = rs.getString("nationality");
                String url = rs.getString("url");

                Driver driver = new Driver(driverId, driverRef, number, code, forename, surname, dob, nationality, url);
                drivers.add(driver);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return drivers;
    }

}
