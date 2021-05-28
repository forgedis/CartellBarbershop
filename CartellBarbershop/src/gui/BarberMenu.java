package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class BarberMenu extends JFrame {
	private JButton createBarberBtn = new JButton("Create Barber");
	private JButton findBarberBtn = new JButton("Find Barber");
	private JButton deleteBarberBtn = new JButton("Delete Barber");
	private JButton updateBarberBtn = new JButton("Update Barber");
	private final JLabel barberMenuLabel = new JLabel("Barber Menu");
	
	public BarberMenu() {
		setTitle("Barber Management Menu");
	    setSize(300,350);
	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3"));
	    initComponent(); 
	    initEvent();
	}
	
	private void initComponent() {
		createBarberBtn.setBounds(70, 70, 150, 30);
		createBarberBtn.setBorder(new LineBorder(Color.BLACK));
		createBarberBtn.setBackground(Color.decode("#FFFFFF"));
		createBarberBtn.setFocusPainted(false);
		findBarberBtn.setBounds(70, 130, 150, 30);
		findBarberBtn.setBorder(new LineBorder(Color.BLACK));
		findBarberBtn.setBackground(Color.decode("#FFFFFF"));
		findBarberBtn.setFocusPainted(false);
		deleteBarberBtn.setBounds(70, 190, 150, 30);
		deleteBarberBtn.setBorder(new LineBorder(Color.BLACK));
		deleteBarberBtn.setBackground(Color.decode("#FFFFFF"));
		deleteBarberBtn.setFocusPainted(false);
		updateBarberBtn.setBounds(70, 250, 150, 30);
		updateBarberBtn.setBorder(new LineBorder(Color.BLACK));
		updateBarberBtn.setBackground(Color.decode("#FFFFFF"));
		updateBarberBtn.setFocusPainted(false);
		
		getContentPane().add(createBarberBtn);
		getContentPane().add(findBarberBtn);
		getContentPane().add(deleteBarberBtn);
		getContentPane().add(updateBarberBtn);
		barberMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		barberMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		barberMenuLabel.setBounds(70, 20, 150, 35);
		
		getContentPane().add(barberMenuLabel);
	}
	
	private void initEvent() {
		createBarberBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				CreateBarberWindow CBW = new CreateBarberWindow();
				CBW.setVisible(true);
				}
			});
		
		findBarberBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				FindBarberWindow FBW;
				try {
					FBW = new FindBarberWindow();
					FBW.setVisible(true);
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}			
				}
			});
		
		deleteBarberBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				DeleteBarberWindow DBW = new DeleteBarberWindow();
				DBW.setVisible(true);
				}
			});
		
		updateBarberBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				UpdateBarberWindow UBW = new UpdateBarberWindow();
				UBW.setVisible(true);
				}
			});
	}
}