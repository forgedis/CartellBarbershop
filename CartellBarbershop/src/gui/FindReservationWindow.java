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
import ctr.CustomerCtr;
import ctr.ReservationCtr;
import ctr.ServiceCtr;
import db.DataAccessException;
import model.Barber;
import model.Customer;
import model.Reservation;
import model.Service;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FindReservationWindow extends JFrame {
	private JPanel panel = new JPanel();
	private JTextField resNoTextField = new JTextField();
	private JTable table = new JTable();
	private JLabel lblNewLabel = new JLabel("Enter Reservation Number :");
	private ReservationCtr reservationCtr;
	
	public FindReservationWindow() throws DataAccessException {
		reservationCtr = new ReservationCtr();
		setTitle("Find Reservation");
		setSize(400,391);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.decode("#C3C3C3")); 
		getContentPane().add(panel, BorderLayout.NORTH);
		
		panel.add(lblNewLabel);
		
		panel.add(resNoTextField);
		resNoTextField.setColumns(10);
		resNoTextField.setBorder(new LineBorder(Color.BLACK));
		resNoTextField.setBackground(Color.decode("#FFFFFF"));
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBorder(new LineBorder(Color.BLACK));
		btnNewButton.setBackground(Color.decode("#FFFFFF"));
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
					String reservationNo = resNoTextField.getText().toString();

					List<Reservation> reservations = null;
					
					if(reservationNo != null && reservationNo.trim().length() > 0) {
						reservations = reservationCtr.findReservation(reservationNo);
					} else {
						reservations = reservationCtr.printAllReservations();
					}
					ReservationTabelModel model = new ReservationTabelModel(reservations);
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

	public void refreshReservationView() {
		try {
			List<Reservation> reservations = reservationCtr.printAllReservations();

			// create the model and update the "table"
			ReservationTabelModel model = new ReservationTabelModel(reservations);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);

		
	}
}
}
