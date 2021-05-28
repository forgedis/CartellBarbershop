package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;



class CustomerTabelModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int LAST_NAME_COL = 1;
	private static final int FIRST_NAME_COL = 2;
	private static final int PHONE_COL = 3;

	private String[] columnNames = { "ID", "Last Name", "First Name", "PhoneNo" };
	private List<Customer> customers;

	public CustomerTabelModel(List<Customer> theCustomers) {
		customers = theCustomers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Customer tempCustomer = customers.get(row);

		switch (col) {
		case ID_COL:
			return tempCustomer.getId();
		case LAST_NAME_COL:
			return tempCustomer.getLastName();
		case FIRST_NAME_COL:
			return tempCustomer.getFirstName();
		case PHONE_COL:
			return tempCustomer.getPhoneNo();
		default:
			return tempCustomer.getLastName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

