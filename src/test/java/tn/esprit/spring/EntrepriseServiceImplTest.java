package tn.esprit.spring;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
		entreprise.setRaisonSocial("raisonSocial");
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

	@Test
	public void testGetEntrepriseById() {
		//*******ADD ENTREPRISE **********//
		Entreprise entreprise = new Entreprise();
		entreprise.setName("Vermeg");
		entreprise.setRaisonSocial("raisonSocial");
		int savedEntrepriseID= entrepriseService.ajouterEntreprise(entreprise);
		//*******TEST GET EntrepriseById**********//
		Entreprise entreprise1 = entrepriseService.getEntrepriseById(savedEntrepriseID);
		assertNotNull(entreprise1);	
		//***** DELETE ENTREPRISE  *******//
		entrepriseService.deleteEntrepriseById(savedEntrepriseID);	
	}
	
	
	@Test
	public void testAffecterDepartementAEntreprise() {
		
	//*******ADD ENTREPRISE + DEPARTEMENT**********//
				Entreprise entreprise = new Entreprise();
				entreprise.setName("Vermeg");
				entreprise.setRaisonSocial("raisonSocial");
				int savedEntrepriseID= entrepriseService.ajouterEntreprise(entreprise);
				Departement departement= new Departement();
				departement.setName("Support");
				int savedDepartementID= entrepriseService.ajouterDepartement(departement);
	//**************AffecterDepartementAEntreprise****************//
				entrepriseService.affecterDepartementAEntreprise(savedDepartementID, savedEntrepriseID);
				assertNotNull(entreprise.getDepartements());	
	//***** DELETE DEPARTEMENT + ENTREPRISE *******//
				entrepriseService.deleteDepartementById(savedDepartementID);
				entrepriseService.deleteEntrepriseById(savedEntrepriseID);	
	}

	
	@Test
	public void testGetAllDepartementsNamesByEntreprise() {
		//*******ADD ENTREPRISE + DEPARTEMENTS**********//
		Entreprise entreprise = new Entreprise();
		entreprise.setName("Vermeg");
		entreprise.setRaisonSocial("raisonSocial");
		int savedEntrepriseID= entrepriseService.ajouterEntreprise(entreprise);
		Departement departement1= new Departement();
		departement1.setName("Support");
		int savedDepartementID1= entrepriseService.ajouterDepartement(departement1);
		Departement departement2= new Departement();
		departement2.setName("Dev");
		int savedDepartementID2= entrepriseService.ajouterDepartement(departement2);
		entrepriseService.affecterDepartementAEntreprise(savedDepartementID1, savedEntrepriseID);
		entrepriseService.affecterDepartementAEntreprise(savedDepartementID2, savedEntrepriseID);
//**************GetAllDepartementsNamesByEntreprise****************//
		List<String> departmentsNames = entrepriseService.getAllDepartementsNamesByEntreprise(savedEntrepriseID);
		assertNotNull(departmentsNames);
//***** DELETE DEPARTEMENT + ENTREPRISE *******//
		entrepriseService.deleteDepartementById(savedDepartementID1);
		entrepriseService.deleteDepartementById(savedDepartementID2);
		entrepriseService.deleteEntrepriseById(savedEntrepriseID);	
}
		
		
	}

	



