package tn.esprit.spring;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	@Autowired
	IEntrepriseService entrepriseService;
	
	@Autowired
	EntrepriseRepository entrepriseRepo;
	
	@Autowired
	DepartementRepository departementRepo;
	
	@Test
	public void testAjouterEntreprise() {
		
		//*******ADD ENTREPRISE TEST**********//
		List<Entreprise> entreprises = (List)entrepriseRepo.findAll();
		int expected = entreprises.size();
		Entreprise entreprise = new Entreprise();
		entreprise.setName("Vermeg");
		int savedEntrepriseID= entrepriseService.ajouterEntreprise(entreprise);
		assertEquals(expected+1,((List)(entrepriseRepo.findAll())).size());
		//***** DELETE ENTREPRISE TEST *******//
		entrepriseService.deleteEntrepriseById(savedEntrepriseID);
		assertEquals(expected, ((List)(entrepriseRepo.findAll())).size());
	}
	
	@Test
	public void testAjouterDepartement() {
		//******ADD DEPARTEMENT TEST*******//
		List<Departement> departements =(List)departementRepo.findAll();
		int expected= departements.size();
		Departement departement= new Departement();
		departement.setName("Support");
		int savedDepartementID= entrepriseService.ajouterDepartement(departement);
		assertEquals(expected+1, ((List)departementRepo.findAll()).size());
		//***** DELETE DEPARTEMENT TEST *******//
		entrepriseService.deleteDepartementById(savedDepartementID);
		assertEquals(expected, ((List)departementRepo.findAll()).size());	
	}

//	@Test
//	public void testAffecterDepartementAEntreprise() {}

	
//	@Test
//	public void testGetAllDepartementsNamesByEntreprise() {}

	

//	@Test
//	public void testGetEntrepriseById() {}
	

}
