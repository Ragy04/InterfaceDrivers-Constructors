package demo_jdbc.respositories;

import demo_jdbc.models.Race;
import Conn.ConexionSql;
import java.util.List;
import java.util.Set;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;

public class RaceRepository {
	 private ConexionSql conexionSql = new ConexionSql();

	    public Set<Integer> getUniqueYears() {
	        Set<Integer> years = new HashSet<>();

	        try {
	            Connection conn = conexionSql.getConexion();
	            Statement statement = conn.createStatement();
	            String sql = "SELECT DISTINCT year FROM races";
	            ResultSet rs = statement.executeQuery(sql);

	            while (rs.next()) {
	                years.add(rs.getInt("year"));
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return years;
	    }
	    
	    public List<Race> getRacesByYear(int yearParam) {
	        List<Race> races = new ArrayList<>();

	        try {
	            Connection conn = conexionSql.getConexion();
	            Statement statement = conn.createStatement();
	            String sql = "SELECT * FROM races WHERE year = " + yearParam;
	            ResultSet rs = statement.executeQuery(sql);

	            while (rs.next()) {
	                int raceId = rs.getInt("raceId");
	                int year = rs.getInt("year");
	                int round = rs.getInt("round");
	                int circuitId = rs.getInt("circuitId");
	                String name = rs.getString("name");
	                Date date = rs.getDate("date");
	                Time time = rs.getTime("time");
	                String url = rs.getString("url");
	                Date fp1_date = rs.getDate("fp1_date");
	                Time fp1_time = rs.getTime("fp1_time");
	                Date fp2_date = rs.getDate("fp2_date");
	                Time fp2_time = rs.getTime("fp2_time");
	                Date fp3_date = rs.getDate("fp3_date");
	                Time fp3_time = rs.getTime("fp3_time");
	                Date quali_date = rs.getDate("quali_date");
	                Time quali_time = rs.getTime("quali_time");
	                Date sprint_date = rs.getDate("sprint_date");
	                Time sprint_time = rs.getTime("sprint_time");

	                Race race = new Race(raceId, year, round, circuitId, name, date, time, url,
	                                     fp1_date, fp1_time, fp2_date, fp2_time, fp3_date, fp3_time,
	                                     quali_date, quali_time, sprint_date, sprint_time);
	                races.add(race);
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return races;
	    }
}