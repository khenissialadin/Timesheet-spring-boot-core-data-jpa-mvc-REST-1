package tn.esprit.spring.requestmodels;

import java.util.List;

import tn.esprit.spring.entities.Departement;

public class EntrepriseRequestModel {
	private int id;
	private String nameRM;
	private String raisonSocialRM;
	private List<Departement> departementsRM;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameRM() {
		return nameRM;
	}
	public void setNameRM(String nameRM) {
		this.nameRM = nameRM;
	}
	public String getRaisonSocialRM() {
		return raisonSocialRM;
	}
	public void setRaisonSocialRM(String raisonSocialRM) {
		this.raisonSocialRM = raisonSocialRM;
	}
	public List<Departement> getDepartementsRM() {
		return departementsRM;
	}
	public void setDepartementsRM(List<Departement> departementsRM) {
		this.departementsRM = departementsRM;
	}
	
	


}
