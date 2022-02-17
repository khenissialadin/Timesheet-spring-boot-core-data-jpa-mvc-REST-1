package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import org.junit.Assert;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTests {
	
	@Autowired
	IEmployeService employeService;
	
	@Autowired
	IEntrepriseService entrepriseService;
	
	@Test
	public void ajouterEmployeTest() {
		Employe employe= new Employe();
		employe.setActif(false);
		employe.setEmail("efsffs");
		employe.setNom("aaaa");
		employe.setPrenom("sfsfe");
		int id = employeService.ajouterEmploye(employe);
		Assert.assertEquals(employeService.getEmployePrenomById(id), employe.getPrenom());
		employeService.deleteEmployeById(id);
		String prenom = employeService.getEmployePrenomById(id);
		Assert.assertNull(prenom);
	}
	
	@Test
	public void getAllEmployeNamesJPQLTest() {
		List<String> names = employeService.getAllEmployeNamesJPQL();
		Assert.assertNotNull(names);
	}
	
	@Test
	public void getAllEmployesTest() {
		List<Employe> emps = employeService.getAllEmployes();
		Assert.assertNotNull(emps);
	}
	
	@Test
	public void getSalaireMoyenByDepartementIdTest() {
		Departement dep =new Departement();
		Integer id = entrepriseService.ajouterDepartement(dep);
		Double sal = employeService.getSalaireMoyenByDepartementId(id);
		Assert.assertNull(sal);
		entrepriseService.deleteDepartementById(id);
	}
	
	@Test
	public void getSalaireByEmployeIdJPQLTest() {
		Employe emp =new Employe();
		Integer id = employeService.ajouterEmploye(emp);
		Float sal = employeService.getSalaireByEmployeIdJPQL(id);
		Assert.assertNull(sal);
		employeService.deleteEmployeById(id);
	}
	
	@Test
	public void deleteAllContratJPQLTest() {
		Contrat ctr =new Contrat();
		int id = employeService.ajouterContrat(ctr);
		Assert.assertNotEquals(0, id);
		employeService.deleteContratById(id);
		Contrat c = employeService.getContratById(id);
		Assert.assertNull(c);
	}


}
