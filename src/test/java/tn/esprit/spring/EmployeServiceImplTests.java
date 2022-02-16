package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTests {
	
	@Autowired
	IEmployeService employeService;
	
	@Test
	public void ajouterEmployeTest() {
		Employe employe= new Employe();
		employe.setActif(false);
		employe.setEmail("efsffs");
		employe.setNom("aaaa");
		employe.setPrenom("sfsfe");
		Assert.assertNotEquals(employeService.ajouterEmploye(employe), 0);
	}


}
