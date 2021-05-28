package gui;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctr.CustomerCtr;
import db.DataAccessException;
import model.Customer;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CreateCustomerWindow extends JFrame implements Runnable{
	private JButton createBtn = new JButton("Create a Customer");
	private JTextField txtA = new JTextField();
	private JLabel firstNameLabel = new JLabel("First Name:");
	private JLabel lastNameLabel = new JLabel("Last Name:");
	private JLabel phoneNoLabel = new JLabel("Phone Number:");
	private JTextField txtB = new JTextField();
	private JTextField txtC = new JTextField();
	private CustomerCtr customerCtr;
	
	
	public CreateCustomerWindow() {
		setTitle("Create a Customer");
	    setSize(361,208);
	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3")); 
	    initComponent();     
	    initEvent();
	}
	
	private void initComponent() {
		createBtn.setBounds(160,118, 160,25);
		createBtn.setBorder(new LineBorder(Color.BLACK));
		createBtn.setBackground(Color.decode("#FFFFFF"));
		createBtn.setFocusPainted(false);
		
		txtA.setBounds(160,10,160,20);
		txtA.setBorder(new LineBorder(Color.BLACK));
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		firstNameLabel.setHorizontalAlignment(SwingConstants.LEFT);

		
		firstNameLabel.setBounds(23,9,111,20);

		
		getContentPane().add(createBtn);
		getContentPane().add(txtA);
		getContentPane().add(firstNameLabel);
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lastNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		lastNameLabel.setBounds(23, 40, 111, 20);
		getContentPane().add(lastNameLabel);
		
		
		txtB.setBounds(160, 40, 160, 20);
		getContentPane().add(txtB);
		txtB.setColumns(10);
		txtB.setBorder(new LineBorder(Color.BLACK));
		
		txtC = new JTextField();
		txtC.setBounds(160, 69, 160, 20);
		getContentPane().add(txtC);
		txtC.setColumns(10);
		txtC.setBorder(new LineBorder(Color.BLACK));
		phoneNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		phoneNoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		phoneNoLabel.setBounds(23, 71, 111, 20);
		getContentPane().add(phoneNoLabel);
		
		
	}
	
	private void initEvent() {
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createCustomer();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void createCustomer() throws DataAccessException  {
		String firstName = txtA.getText().toString();
		String lastName = txtB.getText().toString();
		String phoneNo = txtC.getText().toString();
		
		if(firstName.isEmpty() || lastName.isEmpty() || phoneNo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Customer adding failed.", "Error", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					customerCtr = new CustomerCtr();
					customerCtr.createCustomer(firstName, lastName, phoneNo);
					setVisible(false);
					dispose();
					JOptionPane.showMessageDialog(null, "Customer added successfully.", "Customer Added", JOptionPane.INFORMATION_MESSAGE);

				} 
				catch(Exception e) {
					JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
