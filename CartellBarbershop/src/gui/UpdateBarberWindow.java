package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.BarberCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class UpdateBarberWindow extends JFrame {
	private JButton updateBtn = new JButton("Update a Barber");
	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	private JLabel lblA = new JLabel("Name:");
	private JLabel lblB = new JLabel("New name:");
	private BarberCtr barberCtr;
	
	public UpdateBarberWindow() {
		setTitle("Update a Barber");
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
		
		txtA.setBounds(115,35,120,20);
		txtA.setBorder(new LineBorder(Color.BLACK));
		txtA.setBackground(Color.decode("#FFFFFF"));
		txtB.setBounds(115,60,120,20);
		txtB.setBorder(new LineBorder(Color.BLACK));
		txtB.setBackground(Color.decode("#FFFFFF"));
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblA.setBounds(20,35,100,20);
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblB.setBounds(20,60,100,20);
		
		getContentPane().add(updateBtn);
		getContentPane().add(txtA);
		getContentPane().add(txtB);
		getContentPane().add(lblA);
		getContentPane().add(lblB);
	}
	
	private void initEvent() {
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateBarber();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void updateBarber() throws DataAccessException {
		String name = txtA.getText();
		String newName = txtB.getText();
		barberCtr = new BarberCtr();
		
		try {
			barberCtr.updateBarber(name, newName);
			setVisible(false);
			dispose();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
