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
import model.Price;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class FindPriceWindow extends JFrame {
	private JPanel panel = new JPanel();
	private JTextField txtA = new JTextField();
	private JTable table = new JTable();
	private JLabel lblNewLabel = new JLabel("Enter date:");
	private ServiceCtr priceCtr;
	
	public FindPriceWindow() throws DataAccessException {
		priceCtr = new ServiceCtr();
		setTitle("Find Price");
		setSize(400,391);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel, BorderLayout.NORTH);
	    getContentPane().setBackground(Color.decode("#C3C3C3")); 
	    panel.setBackground(Color.decode("#C3C3C3")); 
		panel.add(lblNewLabel);
		panel.add(txtA);
		txtA.setColumns(10);
		txtA.setBorder(new LineBorder(Color.BLACK));
		txtA.setBackground(Color.decode("#FFFFFF"));
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBorder(new LineBorder(Color.BLACK));
		btnNewButton.setBackground(Color.decode("#FFFFFF"));
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
					
					
					String dateString = txtA.getText().toString();
					
					if(!dateString.isEmpty()) {
						DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate ld = LocalDate.parse(dateString, DATEFORMATTER);
						List<Price> Prices = null;
						
						if(ld != null) {
							Prices = priceCtr.findPriceByDate(ld);
						} else {
							Prices = priceCtr.printAllPrices();
						}
						PriceTabelModel model = new PriceTabelModel(Prices);
						table.setModel(model);
					}
					else {
						List<Price> Prices = null;
						Prices = priceCtr.printAllPrices();
						PriceTabelModel model = new PriceTabelModel(Prices);
						table.setModel(model);
					}
					

					
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

	public void refreshPriceView() {
		try {
			List<Price> Prices = priceCtr.printAllPrices();

			// create the model and update the "table"
			PriceTabelModel model = new PriceTabelModel(Prices);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);

		
	}
}
}
