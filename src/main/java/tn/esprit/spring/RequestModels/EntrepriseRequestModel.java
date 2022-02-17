package tn.esprit.spring.RequestModels;

import java.util.List;

import tn.esprit.spring.entities.Departement;

public class EntrepriseRequestModel {
	public int id;
	public String name;
	public String raisonSocial;
	public List<Departement> departements;

}
