package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.repository.EmployeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTests {

	@Autowired
	IEmployeService employeService;

	@Autowired
	IEntrepriseService entrepriseService;

	@Autowired
	ContratRepository contratRepository;

	@Autowired
	ITimesheetService timesSheetService;

	@Autowired
	MissionRepository missionRepository;
	
	@Autowired
	EmployeRepository employeRepository;
	
	@Test
	public void ajouterEmployeTest() {
		Employe employe = new Employe();
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
		Departement dep = new Departement();
		Integer id = entrepriseService.ajouterDepartement(dep);
		Double sal = employeService.getSalaireMoyenByDepartementId(id);
		Assert.assertNull(sal);
		entrepriseService.deleteDepartementById(id);
	}

	@Test
	public void getSalaireByEmployeIdJPQLTest() {
		Employe emp = new Employe();
		Integer id = employeService.ajouterEmploye(emp);
		Float sal = employeService.getSalaireByEmployeIdJPQL(id);
		Assert.assertNull(sal);
		employeService.deleteEmployeById(id);
	}

	@Test
	public void deleteAllContratJPQLTest() {
		Contrat ctr = new Contrat();
		int id = employeService.ajouterContrat(ctr);
		Assert.assertNotEquals(0, id);
		employeService.deleteContratById(id);
		Contrat c = employeService.getContratById(id);
		Assert.assertNull(c);
	}

	@Test
	public void getContratByIdTest() {
		Contrat contrat = new Contrat();
		contratRepository.save(contrat);
		Contrat contrat_ = employeService.getContratById(contrat.getReference());
		Assert.assertNotNull(contrat_);
		contratRepository.delete(contrat);
	}

	@Test
	public void getEmployePrenomByIdTest() {
		Employe employe = new Employe();
		employe.setPrenom("Aziz");
		employeService.ajouterEmploye(employe);
		String empoyePrenom = employeService.getEmployePrenomById(employe.getId());
		assertNotNull(empoyePrenom);
		Assert.assertEquals("Aziz", empoyePrenom);
		employeService.deleteEmployeById(employe.getId());
	}

	@Test
	public void getNombreEmployeJPQLTest() {
		Integer countEmp = employeService.getNombreEmployeJPQL();
		Assert.assertNotNull(countEmp);
	}

	@Test
	public void getTimesheetsByMissionAndDate() {
		Timesheet timesSheet = new Timesheet();
		Employe emp = new Employe();
		employeService.ajouterEmploye(emp);
		Mission mission = new Mission();
		missionRepository.save(mission);
		TimesheetPK pk = new TimesheetPK();
		pk.setDateDebut(new Date(122, 02, 22));
		pk.setDateFin(new Date(122, 02, 22));
		pk.setIdEmploye(emp.getId());
		pk.setIdMission(mission.getId());
		timesSheet.setEmploye(emp);
		timesSheet.setMission(mission);
		timesSheet.setTimesheetPK(pk);
		List<Timesheet> timesheets = null;
		timesSheetService.ajouterTimesheet(timesSheet.getMission().getId(), timesSheet.getEmploye().getId(),
				timesSheet.getTimesheetPK().getDateDebut(), timesSheet.getTimesheetPK().getDateFin());
		timesheets = employeService.getTimesheetsByMissionAndDate(timesSheet.getEmploye(), timesSheet.getMission(),
				timesSheet.getTimesheetPK().getDateDebut(), timesSheet.getTimesheetPK().getDateFin());
		Assert.assertNotNull(timesSheet);
		missionRepository.delete(mission);
		employeService.deleteEmployeById(emp.getId());
		timesSheetService.removeTimeSheet(timesSheet);

	}

	@Test
	public void mettreAjourEmailByEmployeIdTest() {
		Employe emp = new Employe();
		String email = "MyMail@myDomain.com";
		employeService.ajouterEmploye(emp);
		employeService.mettreAjourEmailByEmployeId(email, emp.getId());
		Employe assetEmp = employeService.findEmployeById(emp.getId());
		Assert.assertEquals(assetEmp.getEmail(), email);
		employeService.deleteEmployeById(emp.getId());
	}

	@Test
	public void mettreAjourEmailByEmployeIdJPQLTest() {
		Employe emp = new Employe();
		String email = "MyMail@myDomain.com";
		employeService.ajouterEmploye(emp);
		employeService.mettreAjourEmailByEmployeIdJPQL(email, emp.getId());
		Employe assetEmp = employeService.findEmployeById(emp.getId());
		Assert.assertEquals(assetEmp.getEmail(), email);
		employeService.deleteEmployeById(emp.getId());
	}
	
	@Test
	public void affecterContractEmployeTest() {
		Employe employe= new Employe();
		employe.setActif(false);
		employe.setEmail("efsffs");
		employe.setNom("aaaa");
		employe.setPrenom("sfsfe");
		
		Contrat contrat = new Contrat();
		contrat.setReference(new Random().nextInt(999));
		contrat.setDateDebut(null);
		contrat.setEmploye(employe);
		contrat.setSalaire(100);
		contrat.setTypeContrat(null);
		
		employeService.ajouterEmploye(employe);
		//int ctrId = employeService.ajouterContrat(contrat);
		
		employeService.affecterContratAEmploye(contrat.getReference(), employe.getId());
		Contrat ctr = employeService.getContratById(contrat.getReference());
			
		assertNull(ctr);
	}

	@Test
	public void ajouterContratTest(){
		Employe employe= new Employe();
		employe.setActif(false);
		employe.setEmail("efsffs");
		employe.setNom("aaaa");
		employe.setPrenom("sfsfe");
		
		Contrat contrat = new Contrat();
		contrat.setReference(new Random().nextInt(999));
		contrat.setDateDebut(null);
		contrat.setEmploye(employe);
		contrat.setSalaire(100);
		contrat.setTypeContrat(null);
		
		employeService.ajouterEmploye(employe);
		int idocntract = employeService.ajouterContrat(contrat);
		Optional<Contrat> ctr = contratRepository.findById(idocntract);
		if(ctr.isPresent()) {
			assertEquals(idocntract, contrat.getReference());
		}		
	}
	@Test
	public void deleteContratByIdTest() {
		Contrat contrat = new Contrat();
		contrat.setReference(new Random().nextInt(999));
		contrat.setDateDebut(null);
		contrat.setEmploye(null);
		contrat.setSalaire(100);
		contrat.setTypeContrat(null);
		
		int idocntract = employeService.ajouterContrat(contrat);
		
		employeService.deleteContratById(idocntract);
		Optional<Contrat> ctr = contratRepository.findById(contrat.getReference());
		assertEquals(false, ctr.isPresent());
		
	}
	@Test
	public void deleteEmployeByIdTest() {
		Employe employe= new Employe();
		employe.setId(new Random().nextInt(999));
		employe.setActif(false);
		employe.setEmail("efsffs");
		employe.setNom("aaaa");
		employe.setPrenom("sfsfe");
		int empId = employeService.ajouterEmploye(employe);
		
		employeService.deleteEmployeById(empId);		
		Optional<Employe> employesAfterDeleteAction = employeRepository.findById(employe.getId());
		assertEquals(false, employesAfterDeleteAction.isPresent());
		
	}
	/*@Test
	public void desaffecterEmployeDuDepartementTest() {
		List<Employe> empls = new ArrayList<>();
		Employe employe= new Employe();
		employe.setId(new Random().nextInt(999));
		employe.setActif(false);
		employe.setEmail("efsffs");
		employe.setNom("aaaa");
		employe.setPrenom("sfsfe");
		employeService.ajouterEmploye(employe);
		empls.add(employe);
		
		Departement departement = new Departement();
		departement.setId(101);
		departement.setName(null);
		departement.setEntreprise(null);
		departement.setEmployes(empls);
		entrepriseService.ajouterDepartement(departement);
		
		employeService.affecterEmployeADepartement(employe.getId(),departement.getId());
		assertNotNull(departement.getEmployes());
	
		employeService.desaffecterEmployeDuDepartement(employe.getId(), departement.getId());
		assertNull(departement.getEmployes());
		
	}*/
	@Test
	public void getAllEmployeByEntreprise() {
		    Entreprise entreprise = new Entreprise();
	        entreprise.setId(1);
	        entreprise.setName("e1");
	        entreprise.setRaisonSocial("s1");
	        
	        Employe employe = new Employe();
	        employe.setNom("aaaa");
	        employe.setId(01);
	        
	        List<Employe> employeList= new ArrayList<>();
	        employeList.add(employe);
	        
	        List<Departement> departements= new ArrayList<>();
	        Departement depart1 = new Departement();
	        depart1.setEmployes(employeList);
	        List<Departement> departList= new ArrayList<>();
	        departList.add(depart1);
	        entreprise.setDepartements(departList);
	        
	        List<Employe> employeNames = employeService.getAllEmployeByEntreprise(entreprise);
	        assertNotNull(employeNames);
		
	}
}
