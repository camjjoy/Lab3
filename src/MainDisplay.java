import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainDisplay {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //create the main application frame
            JFrame frame = new JFrame("Fastest Rubik's Cube Solves");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setLayout(new BorderLayout());

            //load the data from CSV file into a list of FastestSolves objects
            List<FastestSolves> solvesList = FileReader.readFile("solves.csv");

            //create the primary panels with the loaded data
            TablePanel tablePanel = new TablePanel(solvesList);
            StatsPanel statsPanel = new StatsPanel(solvesList);
            ChartPanel chartPanel = new ChartPanel(solvesList);
            DetailsPanel detailsPanel = new DetailsPanel();

            //FilterManager and corresponding FilterPanel
            FilterManager filterManager = new FilterManager(solvesList);
            FilterPanel filterPanel = new FilterPanel(filterManager, tablePanel, statsPanel, chartPanel);

            //add a listener to update DetailsPanel when a row is selected in the TablePanel
            tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tablePanel.getTable().getSelectedRow();
                    if (selectedRow >= 0) {
                        //converts table index to filtered index for selection
                        int modelRow = tablePanel.getTable().convertRowIndexToModel(selectedRow);
                        FastestSolves selectedSolve = tablePanel.getSolvesList().get(modelRow);
                        detailsPanel.displayDetails(selectedSolve);
                    }
                }
            });

            //JSplitLane organizes the window
            JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tablePanel, detailsPanel);
            mainSplitPane.setResizeWeight(0.7);  //70% space to the TablePanel

            JSplitPane statsAndChartPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statsPanel, chartPanel);
            statsAndChartPane.setResizeWeight(0.5);  //split space equally between StatsPanel and ChartPanel

            //add panels to the main frame
            frame.add(filterPanel, BorderLayout.NORTH);   //place the filter panel at the top
            frame.add(mainSplitPane, BorderLayout.CENTER); //center main split pane with table and details
            frame.add(statsAndChartPane, BorderLayout.SOUTH); //bottom pane with stats and chart

            //display it
            frame.setVisible(true);
        });
    }
}
