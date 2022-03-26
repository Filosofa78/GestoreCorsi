package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	public List<Studente> getStudentiByCorso(Corso corso){
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		
		List<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),
						rs.getString("nome"),
						rs.getString("cognome"),
						rs.getString("CDS"));

				result.add(s);
			}

			st.close();
			rs.close();
			conn.close();

			return result;


		} catch (SQLException e) {
			System.err.println("Errore di connessione");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public Map<String, Integer> getDivisioneCDS (Corso corso){
		String sql = "SELECT s.CDS, COUNT(*) AS tot "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.CDS <> '' "
				+ "GROUP BY s.CDS";
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.put(rs.getString("CDS"), rs.getInt("tot"));
			}

			st.close();
			rs.close();
			conn.close();

			return result;


		} catch (SQLException e) {
			System.err.println("Errore di connessione");
			e.printStackTrace();
			return null;
		}
	}
}
