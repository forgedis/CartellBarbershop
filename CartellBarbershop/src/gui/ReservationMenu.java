package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class ReservationMenu extends JFrame {
	private JButton createReservationBtn = new JButton("Create Reservation");
	private JButton findReservationBtn = new JButton("Find Reservation");
	private JButton deleteReservationBtn = new JButton("Delete Reservation");
	private JButton updateReservationBtn = new JButton("Update Reservation");
	private final JLabel reservatioMenuLabel = new JLabel("Reservation Menu");
	
	public ReservationMenu() {
		setTitle("Reservation Management Menu");
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
		createReservationBtn.setBounds(70, 70, 150, 30);
		createReservationBtn.setBorder(new LineBorder(Color.BLACK));
		createReservationBtn.setBackground(Color.decode("#FFFFFF"));
		createReservationBtn.setFocusPainted(false);
		findReservationBtn.setBounds(70, 130, 150, 30);
		findReservationBtn.setBorder(new LineBorder(Color.BLACK));
		findReservationBtn.setBackground(Color.decode("#FFFFFF"));
		findReservationBtn.setFocusPainted(false);
		deleteReservationBtn.setBounds(70, 190, 150, 30);
		deleteReservationBtn.setBorder(new LineBorder(Color.BLACK));
		deleteReservationBtn.setBackground(Color.decode("#FFFFFF"));
		deleteReservationBtn.setFocusPainted(false);
		updateReservationBtn.setBounds(70, 250, 150, 30);
		updateReservationBtn.setBorder(new LineBorder(Color.BLACK));
		updateReservationBtn.setBackground(Color.decode("#FFFFFF"));
		updateReservationBtn.setFocusPainted(false);
		
		getContentPane().add(createReservationBtn);
		getContentPane().add(findReservationBtn);
		getContentPane().add(deleteReservationBtn);
		getContentPane().add(updateReservationBtn);
		reservatioMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservatioMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		reservatioMenuLabel.setBounds(0, 20, 294, 35);
		
		getContentPane().add(reservatioMenuLabel);
	}
	
	private void initEvent() {
		createReservationBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				CreateReservationWindow CBW = new CreateReservationWindow();
				CBW.setVisible(true);
				
				}
			});
		
		findReservationBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				FindReservationWindow FBW;
				try {
					FBW = new FindReservationWindow();
					FBW.setVisible(true);
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
				}
			});
		
		deleteReservationBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				DeleteReservationWindow DBW = new DeleteReservationWindow();
				DBW.setVisible(true);
				}
			});
		
		updateReservationBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				UpdateReservationWindow UBW = new UpdateReservationWindow();
				UBW.setVisible(true);
				}
			});
	}
}
