package ca.mcgill.ecse321.eventregistration.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

public class TestEventRegistrationController {
	private RegistrationManager rm;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml"); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		rm=new RegistrationManager(); 
	}

	@After
	public void tearDown() throws Exception {
		rm.delete();
	}

	@Test
	public void testCreateParticipant() {
		assertEquals(0,rm.getParticipants().size()); 
		String name="Oscar"; 
		EventRegistrationController erc=new EventRegistrationController(rm); 
		try{
		erc.createParticipant(name);
		}catch(InvalidInputException iie){
			fail(); 
		}
		checkResultParticipant(name,rm);
		
		rm = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		// check file contents
		checkResultParticipant(name,rm);
		
	}
	@Test
	public void testCreateParticipantNull(){
		assertEquals(0,rm.getParticipants().size());
		String name=null;
		String error=null;
		 EventRegistrationController erc=new EventRegistrationController(rm); 
		 try{
			 erc.createParticipant(name);
		 }catch(InvalidInputException iie){
			 error=iie.getMessage(); 
		 }
		 // check error
		 assertEquals("Participant name cannot be empty!",error); 
		 //check no change in memory
		 assertEquals(0,rm.getParticipants().size()); 
	}
	
	@Test
	public void testCreateParticipantEmpty(){
		assertEquals(0,rm.getParticipants().size());
		String name="";
		String error=null;
		
		EventRegistrationController erc=new EventRegistrationController(rm); 
		try{
			erc.createParticipant(name);
		}catch(InvalidInputException e){
			error=e.getMessage(); 
		}
		
		assertEquals("Participant name cannot be empty!",error); 
		// check no change in memory
		assertEquals(0,rm.getParticipants().size()); 
	}
	
	@Test
	public void testCreateParticipantSpaces(){
		assertEquals(0,rm.getParticipants().size()); 
		String name=" "; 
		String error=null;
		EventRegistrationController erc=new EventRegistrationController(rm); 
		try{
			erc.createParticipant(name);
		}catch(InvalidInputException iie){
			error=iie.getMessage(); 
		}
		// check error
		assertEquals("Participant name cannot be empty!",error); 
		// check memory
		assertEquals(0,rm.getParticipants().size()); 
		
	}

	private void checkResultParticipant(String name, RegistrationManager rm2) {
		assertEquals(1, rm.getParticipants().size());
		assertEquals(name, rm.getParticipant(0).getName());
		assertEquals(0, rm.getEvents().size());
		assertEquals(0, rm.getRegistrations().size());
	}
	
	

}