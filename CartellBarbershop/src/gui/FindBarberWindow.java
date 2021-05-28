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

import ctr.BarberCtr;
import db.DataAccessException;
import model.Barber;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FindBarberWindow extends JFrame {
	private JPanel panel = new JPanel();
	private JTextField phoneNoTextField = new JTextField();
	private JTable table = new JTable();
	private JLabel lblNewLabel = new JLabel("Enter Barber Name:");
	private BarberCtr barberCtr;
	
	public FindBarberWindow() throws DataAccessException {
		barberCtr = new BarberCtr();
		setTitle("Find Barber");
		setSize(400,391);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.decode("#C3C3C3")); 
		getContentPane().add(panel, BorderLayout.NORTH);
		
		panel.add(lblNewLabel);
		
		panel.add(phoneNoTextField);
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
					String name = phoneNoTextField.getText();

					List<Barber> Barbers = null;
					
					if(name != null && name.trim().length() > 0) {
						Barbers = barberCtr.findBarberByName(name);
					} else {
						Barbers = barberCtr.printAllBarbers();
					}
					BarberTabelModel model = new BarberTabelModel(Barbers);
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

	public void refreshBarberView() {
		try {
			List<Barber> Barbers = barberCtr.printAllBarbers();

			// create the model and update the "table"
			BarberTabelModel model = new BarberTabelModel(Barbers);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);

		
	}
}
}
