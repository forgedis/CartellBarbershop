package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.CustomerCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class CustomerMenu extends JFrame {
	private JButton createCustomerBtn = new JButton("Create Customer");
	private JButton findCustomerBtn = new JButton("Find Customer");
	private JButton deleteCustomerBtn = new JButton("Delete Customer");
	private JButton updateCustomerBtn = new JButton("Update Customer");
	private JTextField createCustomerText = new JTextField();
	private final JLabel customerMenuLabel = new JLabel("Customer Menu");
	
	public CustomerMenu() {
		setTitle("Customer Management Menu");
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
		createCustomerBtn.setBounds(70, 70, 150, 30);
		createCustomerBtn.setBorder(new LineBorder(Color.BLACK));
		createCustomerBtn.setBackground(Color.decode("#FFFFFF"));
		createCustomerBtn.setFocusPainted(false);
		findCustomerBtn.setBounds(70, 130, 150, 30);
		findCustomerBtn.setBorder(new LineBorder(Color.BLACK));
		findCustomerBtn.setBackground(Color.decode("#FFFFFF"));
		findCustomerBtn.setFocusPainted(false);
		deleteCustomerBtn.setBounds(70, 190, 150, 30);
		deleteCustomerBtn.setBorder(new LineBorder(Color.BLACK));
		deleteCustomerBtn.setBackground(Color.decode("#FFFFFF"));
		deleteCustomerBtn.setFocusPainted(false);
		updateCustomerBtn.setBounds(70, 250, 150, 30);
		updateCustomerBtn.setBorder(new LineBorder(Color.BLACK));
		updateCustomerBtn.setBackground(Color.decode("#FFFFFF"));
		updateCustomerBtn.setFocusPainted(false);
		
		createCustomerText.setBounds(100,10,100,20);
		
		getContentPane().add(createCustomerBtn);
		getContentPane().add(findCustomerBtn);
		getContentPane().add(deleteCustomerBtn);
		getContentPane().add(updateCustomerBtn);
		customerMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		customerMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		customerMenuLabel.setBounds(0, 20, 294, 35);
		
		getContentPane().add(customerMenuLabel);
	}
	
	private void initEvent() {
		createCustomerBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				CreateCustomerWindow CCW = new CreateCustomerWindow();
				CCW.setVisible(true);
				}
			});
		
		findCustomerBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				FindCustomerWindow FCW;
				try {
					FCW = new FindCustomerWindow();
					FCW.setVisible(true);
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
				
				}
			});
		
		deleteCustomerBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				DeleteCustomerWindow DCW = new DeleteCustomerWindow();
				DCW.setVisible(true);
				}
			});
		
		updateCustomerBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				UpdateCustomerWindow UCW = new UpdateCustomerWindow();
				UCW.setVisible(true);
				}
			});
	}
}