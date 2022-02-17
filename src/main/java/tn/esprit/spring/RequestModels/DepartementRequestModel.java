package tn.esprit.spring.RequestModels;

import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

public class DepartementRequestModel {
 public int id;
 public String name;
 public List<Employe> employes;
 public List<Mission> missions;
 public Entreprise entreprise;
}
