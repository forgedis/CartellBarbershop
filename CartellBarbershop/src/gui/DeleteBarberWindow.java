package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.BarberCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class DeleteBarberWindow extends JFrame {
	private JButton deleteBtn = new JButton("Delete Barber");
	private JTextField txtA = new JTextField();
	private JLabel lblA = new JLabel("Name:");
	private BarberCtr barberCtr;
	
	public DeleteBarberWindow() {
		setTitle("Delete a Barber");
	    setSize(400,200);
	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3"));
	    initComponent();    
	    initEvent();    
	}
	
	private void initComponent() {
		
		deleteBtn.setBounds(220,120,160,25);
		deleteBtn.setBorder(new LineBorder(Color.BLACK));
		deleteBtn.setBackground(Color.decode("#FFFFFF"));
		deleteBtn.setFocusPainted(false);
		
		txtA.setBounds(120,35,120,20);
		txtA.setBorder(new LineBorder(Color.BLACK));
		txtA.setBackground(Color.decode("#FFFFFF"));
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblA.setBounds(20,35,100,20);
		
		getContentPane().add(deleteBtn);
		getContentPane().add(txtA);
		getContentPane().add(lblA);
	}
	
	private void initEvent() {
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteBarber();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void deleteBarber() throws DataAccessException {
		String name = txtA.getText();
		barberCtr = new BarberCtr();
		try {
			barberCtr.removeBarber(name);
			setVisible(false);
			dispose();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
