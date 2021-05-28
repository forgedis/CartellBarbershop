package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.CustomerCtr;
import db.DataAccessException;
import model.Customer;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeleteCustomerWindow extends JFrame {
	private JButton deleteBtn = new JButton("Delete Customer");
	private JTextField phoneNoTxt = new JTextField();
	private JLabel phoneNoLabel = new JLabel("Phone Number:");
	private CustomerCtr customerCtr;
	
	public DeleteCustomerWindow() {
		setTitle("Delete a Customer");
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
		deleteBtn.setBounds(209,120,160,25);
		deleteBtn.setBorder(new LineBorder(Color.BLACK));
		deleteBtn.setBackground(Color.decode("#FFFFFF"));
		deleteBtn.setFocusPainted(false);
		
		phoneNoTxt.setBounds(120,35,120,20);
		phoneNoTxt.setBorder(new LineBorder(Color.BLACK));
		phoneNoTxt.setBackground(Color.decode("#FFFFFF"));
		phoneNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		phoneNoLabel.setBounds(14,34,100,20);
		
		getContentPane().add(deleteBtn);
		getContentPane().add(phoneNoTxt);
		getContentPane().add(phoneNoLabel);
	}
	
	private void initEvent() {
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						deleteCustomer();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void deleteCustomer() throws DataAccessException, SQLException {
        String phoneNo = phoneNoTxt.getText().toString();
        customerCtr = new CustomerCtr();
        List<Customer> customers = new ArrayList<Customer>();
        customers = customerCtr.printAllCustomers();
        int info = -1;

        if (phoneNo.isEmpty()) {
        	JOptionPane.showMessageDialog(null, "Please write a phone number.", "Customer Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
        	for(int i = 0; i < customers.size(); i++) {
            	try {
                    if(phoneNo.equals(customers.get(i).getPhoneNo())) {
                        customerCtr.deleteCustomer(phoneNo);
                        setVisible(false);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Customer removed successfully.", "Customer Removed", JOptionPane.INFORMATION_MESSAGE);
                        info = 0;
                        break;
                        
                    }
                } catch (DataAccessException e) {
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
