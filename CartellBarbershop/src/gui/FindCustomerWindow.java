package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctr.CustomerCtr;
import db.DataAccessException;
import model.Customer;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FindCustomerWindow extends JFrame {
	private JPanel panel = new JPanel();
	private JTextField phoneNoTextField = new JTextField();
	private JTable table = new JTable();
	private JLabel enterPhoneNumberLabel = new JLabel("Enter Phone Number:");
	private CustomerCtr customerCtr;
	private final JPanel panel_1 = new JPanel();
	
	public FindCustomerWindow() throws DataAccessException {
		customerCtr = new CustomerCtr();
		setTitle("Find Customer");
		setSize(446,473);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.decode("#C3C3C3")); 
		getContentPane().add(panel, BorderLayout.NORTH);
	    getContentPane().setBackground(Color.decode("#C3C3C3")); 

		
		panel.add(enterPhoneNumberLabel);
		
		panel.add(phoneNoTextField);
		phoneNoTextField.setColumns(10);
		phoneNoTextField.setBorder(new LineBorder(Color.BLACK));
		phoneNoTextField.setBackground(Color.decode("#FFFFFF"));
		
		JButton searchButton = new JButton("Search");
		
		searchButton.setBorder(new LineBorder(Color.BLACK));
		searchButton.setBackground(Color.decode("#FFFFFF"));
		searchButton.setFocusPainted(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
					String phoneNo = phoneNoTextField.getText().toString();

					List<Customer> customers = null;
					
					if(phoneNo != null && phoneNo.trim().length() > 0) {
						customers = customerCtr.findCustomer(phoneNo);
					} else {
						customers = customerCtr.printAllCustomers();
					}
					CustomerTabelModel model = new CustomerTabelModel(customers);
					table.setModel(model);
					
					} catch(DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(searchButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.decode("#C3C3C3"));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		table = new JTable();
		table.setBackground(Color.decode("#FFFFFF"));
		scrollPane.setViewportView(table);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
	}

	public void refreshCustomerView() {
		try {
			List<Customer> customers = customerCtr.printAllCustomers();

			// create the model and update the "table"
			CustomerTabelModel model = new CustomerTabelModel(customers);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);

		
	}
}
}
