package tn.esprit.spring.requestmodels;

import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

public class DepartementRequestModel {
  private int id;
  private String nameRM;
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
public List<Employe> getEmployesRM() {
	return employesRM;
}
public void setEmployesRM(List<Employe> employesRM) {
	this.employesRM = employesRM;
}
public List<Mission> getMissionsRM() {
	return missionsRM;
}
public void setMissionsRM(List<Mission> missionsRM) {
	this.missionsRM = missionsRM;
}
public Entreprise getEntrepriseRM() {
	return entrepriseRM;
}
public void setEntrepriseRM(Entreprise entrepriseRM) {
	this.entrepriseRM = entrepriseRM;
}
private List<Employe> employesRM;
  private List<Mission> missionsRM;
  private Entreprise entrepriseRM;

  
}
