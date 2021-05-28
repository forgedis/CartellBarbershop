package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.ServiceCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UpdateServiceWindow extends JFrame {
	private JButton updateBtn = new JButton("Update a Service");
	private JTextField nameTxt = new JTextField();
	private JTextField newNameTxt = new JTextField();
	private JTextField newDescriptionTxt = new JTextField();
	private JTextField newDurationTxt = new JTextField();
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel newNameLabel = new JLabel("New Name:");
	private JLabel newDescriptionLabel = new JLabel("New Description:");
	private JLabel newDurationLabel = new JLabel("New Duration:");
	private ServiceCtr ServiceCtr;
	private final JTextField newPriceTxt = new JTextField();
	private final JLabel priceLabel = new JLabel("New Price:");
	
	public UpdateServiceWindow() {
		newPriceTxt.setBounds(120, 131, 120, 19);
		newPriceTxt.setColumns(10);
		newPriceTxt.setBorder(new LineBorder(Color.BLACK));
		newPriceTxt.setBackground(Color.decode("#FFFFFF"));
		setTitle("Update a Service");
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
		
		nameTxt.setBounds(120,11,120,20);
		nameTxt.setBorder(new LineBorder(Color.BLACK));
		nameTxt.setBackground(Color.decode("#FFFFFF"));
		newNameTxt.setBounds(120,41,120,20);
		newNameTxt.setBorder(new LineBorder(Color.BLACK));
		newNameTxt.setBackground(Color.decode("#FFFFFF"));
		newDescriptionTxt.setBounds(120,71,120,20);
		newDescriptionTxt.setBorder(new LineBorder(Color.BLACK));
		newDescriptionTxt.setBackground(Color.decode("#FFFFFF"));
		newDurationTxt.setBounds(120,101,120,20);
		newDurationTxt.setBorder(new LineBorder(Color.BLACK));
		newDurationTxt.setBackground(Color.decode("#FFFFFF"));
		
		nameLabel.setBounds(20,10,100,20);
		newNameLabel.setBounds(20,40,100,20);
		newDescriptionLabel.setBounds(20,71,100,20);
		newDurationLabel.setBounds(20,101,100,20);
		
		getContentPane().add(updateBtn);
		getContentPane().add(nameTxt);
		getContentPane().add(newNameTxt);
		getContentPane().add(newDescriptionTxt);
		getContentPane().add(newDurationTxt);
		getContentPane().add(nameLabel);
		getContentPane().add(newNameLabel);
		getContentPane().add(newDescriptionLabel);
		getContentPane().add(newDurationLabel);
		
		getContentPane().add(newPriceTxt);
		priceLabel.setBounds(20, 134, 85, 13);
		
		getContentPane().add(priceLabel);
	}
	
	private void initEvent() {
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateService();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void updateService() throws DataAccessException {
		String name = nameTxt.getText().toString();
		String newName = newNameTxt.getText().toString();
		String newDescription = newDescriptionTxt.getText().toString();
		String newDurationString = newDurationTxt.getText().toString();
		
		
		double newPrice = Double.parseDouble(newPriceTxt.getText().toString());
		LocalTime newDuration = null;
		
			if(!newDurationString.isEmpty() && newDurationString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
				newDuration = LocalTime.parse(newDurationString);
			}
			else {
				JOptionPane.showMessageDialog(null, "Reservation creating failed.", null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		
		if(newDurationString.isEmpty() || !newDurationString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")){
			JOptionPane.showMessageDialog(null, "Reservation creating failed.", "Error", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					ServiceCtr = new ServiceCtr();
					ServiceCtr.updateService(name, newName, newDescription, newDuration, newPrice);
					setVisible(false);
					dispose();
					JOptionPane.showMessageDialog(null, "Service added successfully.", "Service Added", JOptionPane.INFORMATION_MESSAGE);

				} 
				catch(Exception e) {
					JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}
}