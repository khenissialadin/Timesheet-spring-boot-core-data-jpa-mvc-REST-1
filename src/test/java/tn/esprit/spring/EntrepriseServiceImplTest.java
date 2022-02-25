package tn.esprit.spring;
import org.junit.Assert;
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
		Entreprise entreprise = new Entreprise();
		entreprise.setName("Vermeg");
		entreprise.setRaisonSocial("raisonSocial");
		int savedEntrepriseID= entrepriseService.ajouterEntreprise(entreprise);
		Assert.assertNotEquals(0, savedEntrepriseID);
		//***** DELETE ENTREPRISE TEST *******//
		entrepriseService.deleteEntrepriseById(savedEntrepriseID);
		Assert.assertNull(entrepriseService.getEntrepriseById(savedEntrepriseID));
	}
	
	@Test
	public void testAjouterDepartement() {
		//******ADD DEPARTEMENT TEST*******//
		Departement departement= new Departement();
		departement.setName("Support");
		int savedDepartementID= entrepriseService.ajouterDepartement(departement);
		Assert.assertNotEquals(0,savedDepartementID);
		//***** DELETE DEPARTEMENT TEST *******//
		entrepriseService.deleteDepartementById(savedDepartementID);
		Assert.assertNull(entrepriseService.getDepartementById(savedDepartementID));
	}

	@Test
	public void testGetEntrepriseById() {
		//*******ADD ENTREPRISE **********//
		Entreprise entreprise = new Entreprise();
		entreprise.setName("Vermeg");
		entreprise.setRaisonSocial("raisonSocial");
		int savedEntrepriseID= entrepriseService.ajouterEntreprise(entreprise);
		//*******TEST GET EntrepriseById**********//
		Assert.assertNotEquals(0,savedEntrepriseID);	
		entrepriseService.deleteEntrepriseById(savedEntrepriseID);	
		Assert.assertNull(entrepriseService.getEntrepriseById(savedEntrepriseID));
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
				Assert.assertNotNull(entreprise.getDepartements());	
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
		Departement departement= new Departement();
		departement.setName("Support");
		int savedDepartementID= entrepriseService.ajouterDepartement(departement);
		entrepriseService.affecterDepartementAEntreprise(savedDepartementID,savedEntrepriseID);
	
//**************GetAllDepartementsNamesByEntreprise****************//
		List<String> departmentsNames = entrepriseService.getAllDepartementsNamesByEntreprise(savedEntrepriseID);
		Assert.assertNotNull(departmentsNames);
//***** DELETE DEPARTEMENT + ENTREPRISE *******//
		entrepriseService.deleteDepartementById(savedDepartementID);
		entrepriseService.deleteEntrepriseById(savedEntrepriseID);	
}
		
		
	}

	



