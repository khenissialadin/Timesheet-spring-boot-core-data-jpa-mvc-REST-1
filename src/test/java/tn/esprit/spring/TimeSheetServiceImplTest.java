package tn.esprit.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetServiceImplTest {
	
	Date startDate, endDate;
	SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	
	@Autowired
	ITimesheetService timesheetServ;
	
	@Autowired
	IEmployeService employeService;
	
	@Autowired
    DepartementRepository depRepo;
	
	@Test
	public void ajouterTimesheet() throws ParseException {
		
		
		startDate = df.parse("15/01/1988");
		endDate = df.parse("15/01/2021"); 
		
		Mission mission = new Mission();
		mission.setName("Mission1");
		mission.setDescription("Mission Desc");
		int id = timesheetServ.ajouterMission(mission);	
		
		Employe employe = new Employe();
		employe.setActif(false);
		employe.setEmail("makhcyrin@gmail.com");
		employe.setNom("Makhbouch");
		employe.setPrenom("Cyrin");
		int idEmp = employeService.ajouterEmploye(employe);
		
		Timesheet timeSheet = new Timesheet();
		timeSheet.setMission(mission);		
		timeSheet.setValide(true);
		timesheetServ.ajouterTimesheet(id, idEmp, startDate, endDate);
		
		Assert.assertNotNull(timeSheet.getMission());

		
		
	}
	
//	@Test
//	public void affecterMissionADepartementTest() {
//		
//	}
	
	@Test
	public void validerTimesheetTest() throws ParseException {
		
		startDate = df.parse("15/01/1988");
	    endDate = df.parse("15/01/2021"); 
		
		Employe emp = new Employe();
		int idEmp = employeService.ajouterEmploye(emp);
		
		Mission miss = new Mission();
		int idMiss = timesheetServ.ajouterMission(miss);
		
		timesheetServ.validerTimesheet(idMiss, idEmp, startDate, endDate, idMiss);
		
		Assert.assertNotNull(miss);		
	}
	
	@Test
	public void findAllMissionByEmployeJPQLTest(){
		
		Employe emp = new Employe();
		int idEmp = employeService.ajouterEmploye(emp);
		List<Mission> mission = timesheetServ.findAllMissionByEmployeJPQL(idEmp);
		int missSize = mission.size();		
		Assert.assertEquals(missSize, timesheetServ.findAllMissionByEmployeJPQL(idEmp).size());
		
	} 
	
	@Test
	public void getAllEmployeByMissionTest() {
		
		Mission miss = new Mission();
		int idMiss = timesheetServ.ajouterMission(miss);
		List<Employe> empl = timesheetServ.getAllEmployeByMission(idMiss);
		int emplSize = empl.size();
		Assert.assertEquals(emplSize, timesheetServ.getAllEmployeByMission(idMiss).size());
	}

}
