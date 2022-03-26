package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	private CorsoDAO corsoDao;
	
	public Model() {
		this.corsoDao = new CorsoDAO();
	}

	public List<Corso>getCorsiByPeriodo(int periodo) {
		return corsoDao.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo){
		return corsoDao.getIscrittiByPeriodo(periodo);
	}
}
