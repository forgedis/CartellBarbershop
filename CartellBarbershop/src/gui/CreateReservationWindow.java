package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.ReservationCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CreateReservationWindow extends JFrame {
	private JButton createBtn = new JButton("Create a Reservation");
		
	private JTextField resNoTxt = new JTextField();
	private JTextField dateTxt = new JTextField();
	private JTextField timeTxt = new JTextField();
	private JTextField customerPhoneTxt = new JTextField();
	private JTextField barberNameTxt = new JTextField();
		
	private JLabel lblA = new JLabel("Reservation No:");
	private JLabel lblB = new JLabel("Date:");
	private JLabel lblC = new JLabel("Time:");
	private JLabel lblD = new JLabel("Customer Phone:");
	private JLabel lblE = new JLabel("Barber name:");
	private ReservationCtr reservationCtr;
	private final JTextField serviceNameTxt = new JTextField();
	private final JLabel lblServiceName = new JLabel("Service name:");
		
	public CreateReservationWindow() {
		setTitle("Create a Reservation");
 	    setSize(450,300);
 	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3")); 
	    initComponent();    
	    initEvent();    
	}
		
	private void initComponent() {
		createBtn.setBounds(250,230, 170,25);
		createBtn.setBorder(new LineBorder(Color.BLACK));
		createBtn.setBackground(Color.decode("#FFFFFF"));
		createBtn.setFocusPainted(false);
			
		resNoTxt.setBounds(150,10,120,20);
		resNoTxt.setBorder(new LineBorder(Color.BLACK));
		dateTxt.setBounds(150,35,120,20);
		dateTxt.setBorder(new LineBorder(Color.BLACK));
		timeTxt.setBounds(150,60,120,20);
		timeTxt.setBorder(new LineBorder(Color.BLACK));
		customerPhoneTxt.setBounds(150,85,120,20);
		customerPhoneTxt.setBorder(new LineBorder(Color.BLACK));
		barberNameTxt.setBounds(150,110,120,20);
		barberNameTxt.setBorder(new LineBorder(Color.BLACK));
			
		lblA.setBounds(20,10,100,20);
		lblB.setBounds(20,35,100,20);
		lblC.setBounds(20,60,100,20);
		lblD.setBounds(20,85,120,20);
		lblE.setBounds(20,110,100,20);
			
		getContentPane().add(createBtn);
		getContentPane().add(resNoTxt);
		getContentPane().add(dateTxt);
		getContentPane().add(timeTxt);
		getContentPane().add(customerPhoneTxt);
		getContentPane().add(barberNameTxt);
		getContentPane().add(lblA);
		getContentPane().add(lblB);
		getContentPane().add(lblC);
		getContentPane().add(lblD);
		getContentPane().add(lblE);
		serviceNameTxt.setBounds(150, 135, 120, 20);
		serviceNameTxt.setBorder(new LineBorder(Color.BLACK));
		
		getContentPane().add(serviceNameTxt);
		lblServiceName.setBounds(20, 135, 100, 20);
		
		getContentPane().add(lblServiceName);
	}
		
	private void initEvent() {
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createReservation();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void createReservation() throws DataAccessException{
		String resNo = resNoTxt.getText().toString();
		String dateString = dateTxt.getText().toString();
		String timeString = timeTxt.getText().toString();
		String serviceName = serviceNameTxt.getText().toString();
		DateTimeFormatter df = null;
		LocalDate localDate = null;
		LocalTime localTime = null;
		String customerPhone = customerPhoneTxt.getText().toString();
		if(!dateString.isEmpty() && validDate(dateString)) {
			df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			localDate = LocalDate.parse(dateString, df);
			if(!timeString.isEmpty() && timeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
				localTime = LocalTime.parse(timeString);
			}
			else {
				JOptionPane.showMessageDialog(null, "Time is empty or use correct syntax (00:00 - 23:59)", null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Date is empty or use correct syntax (yyyy-mm-dd)", null, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		String barberName = barberNameTxt.getText().toString();
		reservationCtr = new ReservationCtr();
		
		if(resNo.isEmpty() || dateString.isEmpty() || timeString.isEmpty() ||  customerPhone.isEmpty()|| barberName.isEmpty()){
			JOptionPane.showMessageDialog(null, "Fill up blank spaces.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		else if(customerPhone.matches("[a-zA-Z]")) {
			JOptionPane.showMessageDialog(null, "Dont use alphabet in Customer Phone.", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else {
				try {
					reservationCtr.createReservation(resNo, localDate, localTime, customerPhone, barberName, serviceName);
					setVisible(false);
					dispose();
					JOptionPane.showMessageDialog(null, "Reservation created successfully.", "Reservation created", JOptionPane.INFORMATION_MESSAGE);

				} 
				catch(Exception e) {
					JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}

	public boolean validDate(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date BOD = null;
		df.setLenient(false);
	    try {
	        BOD = df.parse(dateStr);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	   
	}

}
