package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.CustomerCtr;
import db.DataAccessException;
import model.Customer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateCustomerWindow extends JFrame {
	private JButton updateBtn = new JButton("Update a Customer");
	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	private JTextField txtC = new JTextField();
	private JTextField txtD = new JTextField();
	private JLabel lblA = new JLabel("Phone Number:");
	private JLabel lblB = new JLabel("New First name:");
	private JLabel lblC = new JLabel("New Last name:");
	private JLabel lblD = new JLabel("New PhoneNo:");
	private CustomerCtr customerCtr;
	
	public UpdateCustomerWindow() {
		setTitle("Update a Customer");
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
		txtC.setBounds(115,85,120,20);
		txtC.setBorder(new LineBorder(Color.BLACK));
		txtC.setBackground(Color.decode("#FFFFFF"));
		txtD.setBounds(115,110,120,20);
		txtD.setBorder(new LineBorder(Color.BLACK));
		txtD.setBackground(Color.decode("#FFFFFF"));
		
		lblA.setBounds(20,35,100,20);
		lblB.setBounds(20,60,100,20);
		lblC.setBounds(20,85,100,20);
		lblD.setBounds(20,110,100,20);
		
		getContentPane().add(updateBtn);
		getContentPane().add(txtA);
		getContentPane().add(txtB);
		getContentPane().add(txtC);
		getContentPane().add(txtD);
		getContentPane().add(lblA);
		getContentPane().add(lblB);
		getContentPane().add(lblC);
		getContentPane().add(lblD);
	}
	
	private void initEvent() {
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateCustomer();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void updateCustomer() throws DataAccessException {
		String phoneNo = txtA.getText().toString();
		String newFirstName = txtB.getText().toString();
		String newLastName = txtC.getText().toString();
		String newPhoneNo = txtD.getText().toString();
		customerCtr = new CustomerCtr();
		
		List<Customer> customers = new ArrayList<Customer>();
        customers = customerCtr.printAllCustomers();
        int info = -1;

        if (phoneNo.isEmpty() || newFirstName.isEmpty() || newLastName.isEmpty() || newPhoneNo.isEmpty()) {
        	JOptionPane.showMessageDialog(null, "Please fill the blank spaces.", "Customer Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (!newFirstName.matches("^[a-zA-Z]*$") || !newLastName.matches("^[a-zA-Z]*$") || newPhoneNo.matches("^[a-zA-Z]*$")) {
        	JOptionPane.showMessageDialog(null, "Wrong firstname, lastname, or phonenumber", "Customer Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
        	for(int i = 0; i < customers.size(); i++) {
            	try {
                    if(phoneNo.equals(customers.get(i).getPhoneNo())) {
                    	customerCtr.updateCustomer(phoneNo, newFirstName, newLastName, newPhoneNo);
    					setVisible(false);
    					dispose();
    					JOptionPane.showMessageDialog(null, "Customer updated successfully.", "Customer Updated", JOptionPane.INFORMATION_MESSAGE);
                        info = 0;
                        break;
                        
                    }
                } catch(Exception e) {
					JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
            }
        	if(info == -1) {
            	JOptionPane.showMessageDialog(null, "Customer with this number was not found.", "Customer Removed", JOptionPane.INFORMATION_MESSAGE);
        	}
        }
	}
}
