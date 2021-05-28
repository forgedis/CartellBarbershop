package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class PriceMenu extends JFrame {
	private JButton createPriceBtn = new JButton("Create Price");
	private JButton findPriceBtn = new JButton("Find Price");
	private JButton deletePriceBtn = new JButton("Delete Price");
	private JButton updatePriceBtn = new JButton("Update Price");
	private final JLabel lblPriceMenu = new JLabel("Price Menu");
	
	public PriceMenu() {
		setTitle("Price Management Menu");
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
		createPriceBtn.setBounds(70, 70, 150, 30);
		createPriceBtn.setBorder(new LineBorder(Color.BLACK));
		createPriceBtn.setBackground(Color.decode("#FFFFFF"));
		createPriceBtn.setFocusPainted(false);
		findPriceBtn.setBounds(70, 130, 150, 30);
		findPriceBtn.setBorder(new LineBorder(Color.BLACK));
		findPriceBtn.setBackground(Color.decode("#FFFFFF"));
		findPriceBtn.setFocusPainted(false);
		deletePriceBtn.setBounds(70, 190, 150, 30);
		deletePriceBtn.setBorder(new LineBorder(Color.BLACK));
		deletePriceBtn.setBackground(Color.decode("#FFFFFF"));
		deletePriceBtn.setFocusPainted(false);
		updatePriceBtn.setBounds(70, 250, 150, 30);
		updatePriceBtn.setBorder(new LineBorder(Color.BLACK));
		updatePriceBtn.setBackground(Color.decode("#FFFFFF"));
		updatePriceBtn.setFocusPainted(false);
		
		getContentPane().add(createPriceBtn);
		getContentPane().add(findPriceBtn);
		getContentPane().add(deletePriceBtn);
		getContentPane().add(updatePriceBtn);
		lblPriceMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceMenu.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPriceMenu.setBounds(70, 17, 150, 35);
		
		getContentPane().add(lblPriceMenu);
	}
	
	private void initEvent() {
		createPriceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				CreatePriceWindow CBW = new CreatePriceWindow();
				CBW.setVisible(true);
				}
			});
		
		findPriceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				FindPriceWindow FBW;
				try {
					FBW = new FindPriceWindow();
					FBW.setVisible(true);
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
				}
			});
		
		deletePriceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				DeletePriceWindow DBW = new DeletePriceWindow();
				DBW.setVisible(true);
				}
			});
		
		updatePriceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				UpdatePriceWindow UBW = new UpdatePriceWindow();
				UBW.setVisible(true);
				}
			}); 
	}
}