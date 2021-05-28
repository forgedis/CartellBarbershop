package gui;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctr.ReservationCtr;
import ctr.ServiceCtr;
import db.DataAccessException;
import model.Service;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CreateServiceWindow extends JFrame{
	private JButton createBtn = new JButton("Create a Service");
	private JTextField nameTxt = new JTextField();
	private JLabel nameLabel = new JLabel("Name ");
	private JLabel descriptionLabel = new JLabel("Description");
	private JLabel durationLabel = new JLabel("Duration");
	private JTextField descriptionTxt = new JTextField();
	private JTextField txtC = new JTextField();
	private JTextField durationTxt;

	private ServiceCtr ServiceCtr;
	private JTextField txtD = new JTextField();
	private JTextField priceTxt;
	private final JLabel priceLabel = new JLabel("Price");
	
	
	public CreateServiceWindow() {
		setTitle("Create a Service");
	    setSize(457,293);
	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3")); 
	    initComponent();     
	    initEvent();
	}
	
	private void initComponent() {
		createBtn.setBounds(273,221, 160,25);
		createBtn.setBorder(new LineBorder(Color.BLACK));
		createBtn.setBackground(Color.decode("#FFFFFF"));
		createBtn.setFocusPainted(false);
		
		nameTxt.setBounds(160,10,160,20);
		nameTxt.setBorder(new LineBorder(Color.BLACK));
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

		
		nameLabel.setBounds(23,10,111,20);

		
		getContentPane().add(createBtn);
		getContentPane().add(nameTxt);
		getContentPane().add(nameLabel);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		descriptionLabel.setBounds(23, 40, 111, 20);
		getContentPane().add(descriptionLabel);
		
		
		descriptionTxt.setBounds(160, 40, 160, 20);
		getContentPane().add(descriptionTxt);
		descriptionTxt.setColumns(10);
		descriptionTxt.setBorder(new LineBorder(Color.BLACK));
		
		durationTxt = new JTextField();
		durationTxt.setBounds(160, 70, 160, 20);
		getContentPane().add(durationTxt);
		durationTxt.setColumns(10);
		durationTxt.setBorder(new LineBorder(Color.BLACK));
		durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		durationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		durationLabel.setBounds(23, 70, 111, 20);
		getContentPane().add(durationLabel);
		
		priceTxt = new JTextField();
		priceTxt.setBounds(160, 100, 160, 19);
		priceTxt.setColumns(10);
		priceTxt.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(priceTxt);
		priceTxt.setColumns(10);
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		priceLabel.setBounds(23, 100, 111, 20);
		
		getContentPane().add(priceLabel);
		
		
	}
	
	private void initEvent() {
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createService();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void createService() throws DataAccessException  {
		String name = nameTxt.getText().toString();
		String description = descriptionTxt.getText().toString();
		String durationString = durationTxt.getText().toString();
		
		
		double price = Double.parseDouble(priceTxt.getText());
		LocalTime duration = null;
		
			if(!durationString.isEmpty() && durationString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
				duration = LocalTime.parse(durationString);
			}
			else {
				JOptionPane.showMessageDialog(null, "Reservation creating failed.", null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		
		if(durationString.isEmpty() || !durationString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")){
			JOptionPane.showMessageDialog(null, "Reservation creating failed.", "Error", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					ServiceCtr = new ServiceCtr();
					ServiceCtr.createService(name, description, duration, price);
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

