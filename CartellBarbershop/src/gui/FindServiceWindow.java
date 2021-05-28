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

import ctr.ServiceCtr;
import db.DataAccessException;
import model.Service;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

public class FindServiceWindow extends JFrame {
	private JPanel panel = new JPanel();
	private JTextField phoneNoTextField = new JTextField();
	private JTable table = new JTable();
	private JLabel lblNewLabel = new JLabel("Enter Service Name:");
	private ServiceCtr serviceCtr;
	
	public FindServiceWindow() throws DataAccessException {
		serviceCtr = new ServiceCtr();
		setTitle("Find Service");
		setSize(400,391);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.decode("#C3C3C3")); 
		getContentPane().add(panel, BorderLayout.NORTH);
		
		panel.add(lblNewLabel);
		
		panel.add(phoneNoTextField);
		
		phoneNoTextField.setColumns(10);
		phoneNoTextField.setColumns(10);
		phoneNoTextField.setBorder(new LineBorder(Color.BLACK));
		phoneNoTextField.setBackground(Color.decode("#FFFFFF"));
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBorder(new LineBorder(Color.BLACK));
		btnNewButton.setBackground(Color.decode("#FFFFFF"));
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
					String name = phoneNoTextField.getText().toString();
				

					List<Service> services = null;
					
					if(name != null && name.trim().length() > 0) {
						services = serviceCtr.findServiceByName(name);
					} else {
						services = serviceCtr.printAllServices();
					}
					ServiceTabelModel model = new ServiceTabelModel(services);
					table.setModel(model);
					
					} catch(DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.decode("#C3C3C3"));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(Color.decode("#FFFFFF"));
		scrollPane.setViewportView(table);
		
	}

	public void refreshServiceView() {
		try {
			List<Service> Services = serviceCtr.printAllServices();

			// create the model and update the "table"
			ServiceTabelModel model = new ServiceTabelModel(Services);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);

		
	}
}
}