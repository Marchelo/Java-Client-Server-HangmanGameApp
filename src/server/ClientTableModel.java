package server;

import domm.GameState;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClientTableModel extends AbstractTableModel {
    // Za svakog klijenta prikazuje se 
        // ime igrača, 
        // otkrivena reč,
        // preostali pokušaji 
        // aktivnost
    List<ClientThread> clients = new ArrayList<>();
    String[] cols = {"Igrač", "Otkriveno", "Preostali pokušaji", "Aktivan"};

    public List<ClientThread> getClients() {
        return clients;
    }

    public void setClients(List<ClientThread> clients) {
        this.clients = clients;
        fireTableDataChanged(); // javlja tabeli da su se podaci promenili
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClientThread client = clients.get(rowIndex);
        GameState state = client.gameState;

        switch (columnIndex) {
            case 0: return state.getPlayerName();
            case 1: return state.getRevealed();
            case 2: return state.getPreostaliPokusaji();
            case 3: return client.isOver ? "Ne" : "Da";
            default: throw new AssertionError();
        }
    }
}