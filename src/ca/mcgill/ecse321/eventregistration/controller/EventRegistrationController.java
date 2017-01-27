package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.model.*;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;


public class EventRegistrationController {
	private RegistrationManager rm;
	public EventRegistrationController(RegistrationManager rm){
		this.rm=rm; 
	}
	public void createParticipant(String name) throws InvalidInputException{
		if(name==null||name.trim().length()==0){
			throw new InvalidInputException("Participant name cannot be empty!"); 
		}
		Participant p=new Participant(name); 
		rm.addParticipant(p);
		PersistenceXStream.saveToXMLwithXStream(rm); 
	}
}
