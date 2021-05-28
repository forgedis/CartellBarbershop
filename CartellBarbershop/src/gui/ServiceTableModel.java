package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Service;



class ServiceTabelModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int DESC_COL = 2;
	private static final int DUR_COL = 3;
	private static final int PR_COL = 4;


	private String[] columnNames = { "ID", "Name", "Description", "Duration", "Price ID"};
	private List<Service> Services;

	public ServiceTabelModel(List<Service> theServices) {
		Services = theServices;
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Services.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Service tempService = Services.get(row);

		switch (col) {
		case ID_COL:
			return tempService.getId();
		case NAME_COL:
			return tempService.getName();
		case DESC_COL:
			return tempService.getDescription();
		case DUR_COL:
			return tempService.getDuration();
		case PR_COL:
			return tempService.getPrice_id();
		}
		return tempService;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
