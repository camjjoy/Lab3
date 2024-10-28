import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TablePanel extends JPanel {
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final List<FastestSolves> originalSolvesList;

    public TablePanel(List<FastestSolves> solvesList) {
        this.originalSolvesList = solvesList; //OG list

        setLayout(new BorderLayout());

        //column names for the table
        String[] columnNames = {"Rank", "Person", "Country", "Fastest Solve (centiseconds)"};

        //create JTable and tableModel
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        //populate table with initial data
        populateTable(solvesList);

        //create filter panel with sorting options
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //rank Sorting Dropdown
        filterPanel.add(new JLabel("Sort by Rank:"));
        JComboBox<String> rankSortDropdown = new JComboBox<>(new String[]{"Ascending", "Descending"});
        rankSortDropdown.addActionListener(e -> sortTableByRank(rankSortDropdown.getSelectedItem().toString()));
        filterPanel.add(rankSortDropdown);

        //name Sorting Dropdown
        filterPanel.add(new JLabel("Sort by Person:"));
        JComboBox<String> nameSortDropdown = new JComboBox<>(new String[]{"Alphabetical"});
        nameSortDropdown.addActionListener(e -> sortTableByName());
        filterPanel.add(nameSortDropdown);

        //country Sorting Dropdown
        filterPanel.add(new JLabel("Sort by Country:"));
        JComboBox<String> countrySortDropdown = new JComboBox<>(new String[]{"Alphabetical"});
        countrySortDropdown.addActionListener(e -> sortTableByCountry());
        filterPanel.add(countrySortDropdown);

        //add filter panel and table to the main panel
        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    //populate the JTable with data from the list of solves
    private void populateTable(List<FastestSolves> solvesList) {
        tableModel.setRowCount(0); //clear existing rows

        //add filtered solves to the table model
        for (FastestSolves solve : solvesList) {
            Object[] rowData = {solve.getRank(), solve.getPerson(), solve.getCountry(), solve.getFastestSolve()};
            tableModel.addRow(rowData);
        }
    }

    public void updateTable(List<FastestSolves> filteredList) {
        populateTable(filteredList);
    }

    //sort the table by rank based on selected order
    private void sortTableByRank(String sortOrder) {
        List<FastestSolves> sortedList = originalSolvesList.stream()
                .sorted(sortOrder.equals("Ascending")
                        ? Comparator.comparingInt(FastestSolves::getRank)
                        : Comparator.comparingInt(FastestSolves::getRank).reversed())
                .collect(Collectors.toList());
        populateTable(sortedList);
    }

    //sort the table by person name alphabetically
    private void sortTableByName() {
        List<FastestSolves> sortedList = originalSolvesList.stream()
                .sorted(Comparator.comparing(FastestSolves::getPerson))
                .collect(Collectors.toList());
        populateTable(sortedList);
    }

    //sort the table by country alphabetically
    private void sortTableByCountry() {
        List<FastestSolves> sortedList = originalSolvesList.stream()
                .sorted(Comparator.comparing(FastestSolves::getCountry))
                .collect(Collectors.toList());
        populateTable(sortedList);
    }

    //getter for the JTable
    public JTable getTable() {
        return table;
    }

    //getter for the OG list of solves
    public List<FastestSolves> getSolvesList() {
        return originalSolvesList.stream()
                .filter(solve -> solve.getRank() <= 100) //only 100
                .collect(Collectors.toList());
    }
}
