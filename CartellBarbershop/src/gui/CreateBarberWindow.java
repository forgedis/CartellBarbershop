package gui;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctr.BarberCtr;
import db.DataAccessException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateBarberWindow extends JFrame{
	private JButton createBtn = new JButton("Create a Barber");
	private JTextField txtA = new JTextField();
	private JLabel lblA = new JLabel("Name:");
	private BarberCtr barberCtr;
	
	public CreateBarberWindow() {
		setTitle("Create a Barber");
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
		
		createBtn.setBounds(220,130, 150,25);
		createBtn.setBorder(new LineBorder(Color.BLACK));
		createBtn.setBackground(Color.decode("#FFFFFF"));
		createBtn.setFocusPainted(false);
		
		txtA.setBounds(115,10,120,20);
		txtA.setBorder(new LineBorder(Color.BLACK));

		
		lblA.setBounds(20,10,100,20);

		
		getContentPane().add(createBtn);
		getContentPane().add(txtA);
		getContentPane().add(lblA);
	}
	
	private void initEvent() {
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveBarber();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void saveBarber() throws DataAccessException {
		String name = txtA.getText();
		barberCtr = new BarberCtr();
		
		try {
			barberCtr.createBarber(name);
			setVisible(false);
			dispose();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
