package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDAO;

	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}

	public List<Corso>getCorsiByPeriodo(int periodo) {
		return corsoDao.getCorsiByPeriodo(periodo);
	}

	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		return corsoDao.getIscrittiByPeriodo(periodo);
	}

	public List<Studente> getStudentiByCorso(String codice){
		return studenteDAO.getStudentiByCorso(new Corso(codice, null, null, null));
	}

	public Map<String, Integer> getDivisioneCDS (String codice){
		return studenteDAO.getDivisioneCDS(new Corso(codice, null, null, null));
	}

	public boolean esisteCorso(String codice) {
		return corsoDao.esisteCorso(new Corso(codice, null, null, null));
	}
}
