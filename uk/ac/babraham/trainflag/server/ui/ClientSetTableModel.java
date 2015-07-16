package uk.ac.babraham.trainflag.server.ui;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import uk.ac.babraham.trainflag.server.ClientInstance;
import uk.ac.babraham.trainflag.server.ClientSet;
import uk.ac.babraham.trainflag.server.ClientSetListener;

public class ClientSetTableModel extends AbstractTableModel implements ClientSetListener {
	
	private ClientSet clients;
	
	public ClientSetTableModel (ClientSet clients) {
		this.clients = clients;
		clients.addClientSetListener(this);
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName (int col) {
		if (col==0) return "Host name";
		if (col==1) return "Student Name";
		if (col==2) return "State";
		return null;
	}
	
	public int getRowCount() {
		return clients.clientCount();
	}

	public Object getValueAt(int row, int col) {
		if (col == 0) {
			String name = clients.clients()[row].address().getHostName();
			String ip = clients.clients()[row].address().getHostAddress();
			if (name.equals(ip)) {
				return ip;
			}
			return name+" ("+ip+")";
		}
		if (col == 1) {
			return clients.clients()[row].studentName();
		}
		if (col == 2) {
			switch (clients.clients()[row].state()) {
				case(ClientInstance.STATE_COMPLETE): return "Complete";
				case(ClientInstance.STATE_HELP): return "Help";
				case(ClientInstance.STATE_READY): return "Ready";
				case(ClientInstance.STATE_WORKING): return "Working";
			}
		}
		return null;
	}

	public void clientAdded(ClientInstance client) {
		fireTableChanged(new TableModelEvent(this));
	}

	public void clientRemoved(ClientInstance client) {
		fireTableChanged(new TableModelEvent(this));		
	}

	public void clientStateChanged(ClientInstance client, int status) {
		fireTableDataChanged();
	}

	public void clientNameChanged(ClientInstance client, String name) {
		fireTableDataChanged();		
	}

}
