package tn.esprit.spring.requestmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Role;

@Getter
@Setter
@NoArgsConstructor
public class EmployeRequestModel {
	private String prenom;
	private String nom;
	private String email;
	private boolean isActif;
	private Role role;
	private Contrat contrat;
	
}
