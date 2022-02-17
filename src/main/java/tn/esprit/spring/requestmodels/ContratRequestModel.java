package tn.esprit.spring.requestmodels;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Employe;

@Getter
@Setter
@NoArgsConstructor
public class ContratRequestModel {
	private Date dateDebut;
	private String typeContrat;
	private float salaire;
	private Employe employe;
}
