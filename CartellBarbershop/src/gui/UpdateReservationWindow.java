package gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctr.ReservationCtr;
import db.DataAccessException;

public class UpdateReservationWindow extends JFrame {
	private JButton updateBtn = new JButton("Update a Reservation");
	private JTextField resNoTxt = new JTextField();
	private JTextField newDateTxt = new JTextField();
	private JTextField newTimeTxt = new JTextField();
	private JTextField customerPhoneTxt = new JTextField();
	private JTextField barberNameTxt = new JTextField();
	private JLabel lblA = new JLabel("Reservation No:");
	private JLabel lblB = new JLabel("New Date:");
	private JLabel lblC = new JLabel("New Time:");
	private JLabel lblD = new JLabel("Phone number:");
	private ReservationCtr reservationCtr;
	

	
	public UpdateReservationWindow() {
		setTitle("Update a Reservation");
	    setSize(500,250);
	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3"));
	    initComponent();    
	    initEvent();    
	}
	
	private void initComponent() {
		updateBtn.setBounds(320,180,150,25);
		updateBtn.setBorder(new LineBorder(Color.BLACK));
		updateBtn.setBackground(Color.decode("#FFFFFF"));
		updateBtn.setFocusPainted(false);
		
		resNoTxt.setBounds(125,35,130,20);
		resNoTxt.setBorder(new LineBorder(Color.BLACK));
		resNoTxt.setBackground(Color.decode("#FFFFFF"));
		newDateTxt.setBounds(125,60,130,20);
		newDateTxt.setBorder(new LineBorder(Color.BLACK));
		newDateTxt.setBackground(Color.decode("#FFFFFF"));
		newTimeTxt.setBounds(125,85,130,20);
		newTimeTxt.setBorder(new LineBorder(Color.BLACK));
		newTimeTxt.setBackground(Color.decode("#FFFFFF"));
		customerPhoneTxt.setBounds(125,110,130,20);
		customerPhoneTxt.setBorder(new LineBorder(Color.BLACK));
		customerPhoneTxt.setBackground(Color.decode("#FFFFFF"));
		
		lblA.setBounds(20,35,120,20);
		lblB.setBounds(20,60,120,20);
		lblC.setBounds(20,85,120,20);
		lblD.setBounds(20,110,120,20);
		
		getContentPane().add(updateBtn);
		getContentPane().add(resNoTxt);
		getContentPane().add(newDateTxt);
		getContentPane().add(newTimeTxt);
		getContentPane().add(customerPhoneTxt);
		getContentPane().add(lblA);
		getContentPane().add(lblB);
		getContentPane().add(lblC);
		getContentPane().add(lblD);
		
		barberNameTxt = new JTextField();
		barberNameTxt.setBounds(125, 135, 130, 20);
		barberNameTxt.setBorder(new LineBorder(Color.BLACK));
		barberNameTxt.setBackground(Color.decode("#FFFFFF"));
		getContentPane().add(barberNameTxt);
		
		JLabel lblBarberName = new JLabel("Barber name:");
		lblBarberName.setBounds(20, 135, 120, 20);
		getContentPane().add(lblBarberName);
	}
	
	private void initEvent() {
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateReservation();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void updateReservation() throws DataAccessException{
		
		String resNo = resNoTxt.getText().toString();
		String newDateString = newDateTxt.getText().toString();
		String newTimeString = newTimeTxt.getText().toString();
		DateTimeFormatter df = null;
		LocalDate newDate = null;
		LocalTime newTime = null;
		String customerPhone = customerPhoneTxt.getText().toString();
		if(!newDateString.isEmpty() && validDate(newDateString)) {
			df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			newDate = LocalDate.parse(newDateString, df);
			if(!newTimeString.isEmpty() && newTimeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
				newTime = LocalTime.parse(newTimeString);
			}
			else {
				JOptionPane.showMessageDialog(null, "Customer updating failed.", null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Customer updating failed.", null, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		String barberName = barberNameTxt.getText().toString();
		reservationCtr = new ReservationCtr();
		
		if(resNo.isEmpty() || newDateString.isEmpty() || !validDate(newDateString) || newTimeString.isEmpty() || !newTimeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$") || customerPhone.isEmpty() || customerPhone.matches("[a-zA-Z]") || barberName.isEmpty()){
			JOptionPane.showMessageDialog(null, "Customer updating failed.", "Error", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					reservationCtr.updateReservation(newDate, newTime, barberName, customerPhone, resNo);
					setVisible(false);
					dispose();
					JOptionPane.showMessageDialog(null, "Customer updated successfully.", "Customer Updated", JOptionPane.INFORMATION_MESSAGE);

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
