package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ctr.ServiceCtr;
import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdatePriceWindow extends JFrame {
	private JButton updateBtn = new JButton("Update a Price");
	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	private JLabel lblA = new JLabel("From Date:");
	private JLabel lblB = new JLabel("New Date:");
	private ServiceCtr priceCtr;
	private final JLabel lblNewLabel = new JLabel("New Price:");
	private final JTextField txtC = new JTextField();
	
	public UpdatePriceWindow() {
		txtC.setBounds(135, 95, 120, 20);
		txtC.setColumns(10);
		txtC.setBorder(new LineBorder(Color.BLACK));
		txtC.setBackground(Color.decode("#FFFFFF"));
		
		setTitle("Update a Price");
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
		
		txtA.setBounds(135,35,120,20);
		txtA.setBorder(new LineBorder(Color.BLACK));
		txtA.setBackground(Color.decode("#FFFFFF"));
		txtB.setBounds(135,65,120,20);
		txtB.setBorder(new LineBorder(Color.BLACK));
		txtB.setBackground(Color.decode("#FFFFFF"));
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblA.setBounds(20,35,100,20);
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblB.setBounds(20,65,100,20);
		
		getContentPane().add(updateBtn);
		getContentPane().add(txtA);
		getContentPane().add(txtB);
		getContentPane().add(lblA);
		getContentPane().add(lblB);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 96, 100, 19);
		
		getContentPane().add(lblNewLabel);
		
		getContentPane().add(txtC);
	}
	
	private void initEvent() {
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatePrice();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void updatePrice() throws DataAccessException {

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDate = LocalDate.parse(txtA.getText().toString(), df);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate newFromDate = LocalDate.parse(txtB.getText(),formatter2);	
		double newPrice = Double.parseDouble(txtC.getText());


		priceCtr = new ServiceCtr();
		
		try {
			priceCtr.updatePrice(fromDate, newFromDate, newPrice);
			setVisible(false);
			dispose();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
