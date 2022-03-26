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

public class CorsoDAO {
	public List<Corso>getCorsiByPeriodo(int periodo) {
		String sql = "SELECT * FROM corso WHERE pd = ?";

		List<Corso> result = new ArrayList<Corso>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd"));

				result.add(c);
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


	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS tot"
				+ " FROM corso c, iscrizione i"
				+ " WHERE c.codins = i.codins AND c.pd = ?"
				+ " GROUP BY c.codins, c.crediti, c.nome, c.pd";

		Map<Corso, Integer> result = new HashMap<Corso, Integer>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd"));

				Integer n = rs.getInt("tot");

				result.put(c, n);
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


	public boolean esisteCorso(Corso corso) {
		String sql = "SELECT * FROM corso WHERE codins = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				st.close();
				rs.close();
				conn.close();
				return true;
			}else {
				st.close();
				rs.close();
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
