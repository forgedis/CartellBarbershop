package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.CustomerCtr;
import ctr.ReservationCtr;
import db.DataAccessException;
import model.Customer;
import model.Reservation;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeleteReservationWindow extends JFrame {
	private JButton deleteBtn = new JButton("Delete Reservation");
	private JTextField resNoTxt = new JTextField();

	private JLabel lblA = new JLabel("Reservation number:");
	private ReservationCtr reservationCtr;

	
	public DeleteReservationWindow() {
		setTitle("Delete a Reservation");
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
		
		resNoTxt.setBounds(175,35,120,20);
		resNoTxt.setBorder(new LineBorder(Color.BLACK));
		resNoTxt.setBackground(Color.decode("#FFFFFF"));
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblA.setBounds(35,35,130,20);
		
		getContentPane().add(deleteBtn);
		getContentPane().add(resNoTxt);
		getContentPane().add(lblA);
	}
	
	private void initEvent() {
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						deleteReservation();
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
	
	private void deleteReservation() throws DataAccessException, SQLException {
        String resNo = resNoTxt.getText();
        reservationCtr = new ReservationCtr();
        List<Reservation> reservations = new ArrayList<Reservation>();
        reservations = reservationCtr.printAllReservations();
        int info = -1;
        
        	if (resNo.isEmpty()) {
            	JOptionPane.showMessageDialog(null, "Please write a reservation number.", "Reservation Error", JOptionPane.INFORMATION_MESSAGE);
            }else {
            	for(int i = 0; i < reservations.size(); i++) {
                	try {
                        if(resNo.equals(reservations.get(i).getReservationNo())) {
                        	reservationCtr.deleteReservation(resNo);
                            setVisible(false);
                            dispose();
                            JOptionPane.showMessageDialog(null, "Reservation removed successfully.", "Reservation Removed", JOptionPane.INFORMATION_MESSAGE);
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

        
	

