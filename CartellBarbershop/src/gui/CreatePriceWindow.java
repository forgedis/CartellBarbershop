package gui;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctr.ServiceCtr;
import db.DataAccessException;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreatePriceWindow extends JFrame{
	private JButton createBtn = new JButton("Create a Price");
	private JTextField txtA = new JTextField();
	private JLabel lblA = new JLabel("From date:");
	private ServiceCtr priceCtr;
	private final JLabel lblNewLabel = new JLabel("Price:");
	private final JTextField txtB = new JTextField();
	
	public CreatePriceWindow() {
		txtB.setBounds(115, 40, 120, 20);
		txtB.setColumns(10);
		txtB.setBorder(new LineBorder(Color.BLACK));
		setTitle("Create a Price");
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
		
		createBtn.setBounds(220,130, 150,25);
		createBtn.setBorder(new LineBorder(Color.BLACK));
		createBtn.setBackground(Color.decode("#FFFFFF"));
		createBtn.setFocusPainted(false);
		
		txtA.setBounds(115,10,120,20);
		txtA.setBorder(new LineBorder(Color.BLACK));
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 13));

		
		lblA.setBounds(20,10,100,20);

		
		getContentPane().add(createBtn);
		getContentPane().add(txtA);
		getContentPane().add(lblA);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 40, 100, 20);
		
		getContentPane().add(lblNewLabel);
		
		getContentPane().add(txtB);
	}
	
	private void initEvent() {
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createPrice();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void createPrice() throws DataAccessException {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDate = LocalDate.parse(txtA.getText().toString(), df);
		
		double price = Double.parseDouble(txtB.getText().toString());
		priceCtr = new ServiceCtr();
		
		try {
			priceCtr.createPrice(fromDate, price);
			setVisible(false);
			dispose();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}