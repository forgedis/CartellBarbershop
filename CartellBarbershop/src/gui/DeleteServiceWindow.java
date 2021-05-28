package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.ServiceCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;


public class DeleteServiceWindow extends JFrame {
	private JButton deleteBtn = new JButton("Delete Service");
	private JTextField phoneNoTxt = new JTextField();
	private JLabel lblA = new JLabel("Enter Service Name:");
	private ServiceCtr ServiceCtr;
	
	public DeleteServiceWindow() {
		setTitle("Delete a Service");
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
		
		deleteBtn.setBounds(211,124,160,25);
		deleteBtn.setBorder(new LineBorder(Color.BLACK));
		deleteBtn.setBackground(Color.decode("#FFFFFF"));
		deleteBtn.setFocusPainted(false);
		
		phoneNoTxt.setBounds(154,35,120,20);
		phoneNoTxt.setBorder(new LineBorder(Color.BLACK));
		phoneNoTxt.setBackground(Color.decode("#FFFFFF"));
		
		
		lblA.setBounds(20,35,124,20);
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		getContentPane().add(deleteBtn);
		getContentPane().add(phoneNoTxt);
		getContentPane().add(lblA);
	}
	
	private void initEvent() {
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteService();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void deleteService() throws DataAccessException {
        String name = phoneNoTxt.getText();
        ServiceCtr = new ServiceCtr();
        try {
            if(name == "" ) {
                JOptionPane.showMessageDialog(null, "Error", "Service Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ServiceCtr.deleteService(name);
                setVisible(false);
                dispose();
                JOptionPane.showMessageDialog(null, "Service removed successfully.", "Service Removed", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
}