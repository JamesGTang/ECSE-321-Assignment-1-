package ca.mcgill.ecse321.eventregistration.view;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.eventregistration.controller.EventRegistrationController;
import ca.mcgill.ecse321.eventregistration.controller.InvalidInputException;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager; 

public class ParticipantPage extends JFrame {
	// add generated serialVersionUID 
	private static final long serialVersionUID = 6398301441623263485L;
	private JTextField participantNameTextField; 
	private JLabel participantNameLabel; 
	private JButton addParticipantButton; 
	private RegistrationManager rm; 
	
	// constructor of the class
	public ParticipantPage(RegistrationManager rm){
		this.rm=rm; 
		initComponents();
	}
	
	public void initComponents(){
		// init new instance of the components
		participantNameTextField=new JTextField();
		participantNameLabel=new JLabel();
		addParticipantButton=new JButton();
		
		// set the JFrame behavior
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Event Registration Program");
		
		participantNameLabel.setText("Name:");
		addParticipantButton.setText("Add Participant");
		
		// set JFrame layout
		GroupLayout layout=new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addComponent(participantNameLabel)
				.addGroup(layout.createParallelGroup()
				.addComponent(participantNameTextField, 200, 200, 400)
				.addComponent(addParticipantButton))
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
				{addParticipantButton, participantNameTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
				.addComponent(participantNameLabel)
				.addComponent(participantNameTextField))
				.addComponent(addParticipantButton)
				);
		pack();
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		// add actionlisteners using java lampda expression 
		addParticipantButton.addActionListener(evt->addParticipantButtonActionPerformed());
	}
	
	public void refreshData(){
		participantNameTextField.setText("");
		pack(); 
	}
	private void addParticipantButtonActionPerformed(){
		EventRegistrationController erc=new EventRegistrationController(rm);
		try{
			erc.createParticipant(participantNameTextField.getText());
		}catch(InvalidInputException iie){
			// ignored for now
		}
		refreshData();
	}
}
