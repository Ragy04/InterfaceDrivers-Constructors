package demo_jdbc.respositories;

import demo_jdbc.models.Circuit;
import Conn.ConexionSql;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CircuitRepository {
	
	
	private ConexionSql conexionSql = new ConexionSql ();
	
	
	public List<Circuit> getCircuitsByCountry(String countryParam){
		
		List<Circuit> circuits = new ArrayList<Circuit>();
		
		try {
			// Obtener la conexion
			Connection conn = conexionSql.getConexion();
			
			// Crear una sentencia
			Statement statement = conn.createStatement();
			
			// Ejecutar la consulta
			String sql = "select * from circuits where country = '" + countryParam + "'";
			ResultSet rs = statement.executeQuery(sql);
			
			// Procesar los resultados
			while(rs.next()) {
				int circuitid = rs.getInt("circuitid");
				String circuitref = rs.getString("circuitref");
				String name = rs.getString("name");
				String location = rs.getString("location");
				String country = rs.getString("country");
				
				Circuit circuit = new Circuit(circuitid, circuitref, name, location, country);
				circuits.add(circuit);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return circuits;
		
	}
	
	
	public List<Circuit> getCircuits(){
		
		List<Circuit> circuits = new ArrayList<Circuit>();
		
		try {
			// Obtener la conexion
			Connection conn = conexionSql.getConexion();
			System.out.println("Se conecto a la base de datos");
			
			// Crear una sentencia
			Statement statement = conn.createStatement();
			
			// Ejecutar la consulta
			String sql = "SELECT * FROM circuits";
			ResultSet rs = statement.executeQuery(sql);
			
			// Procesar los resultados
			while(rs.next()) {
				int circuitid = rs.getInt("circuitid");
				String circuitref = rs.getString("circuitref");
				String name = rs.getString("name");
				String location = rs.getString("location");
				String country = rs.getString("country");
				
				Circuit circuit = new Circuit(circuitid, circuitref, name, location, country);
				circuits.add(circuit);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return circuits;
	}

}
