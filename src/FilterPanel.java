import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterPanel extends JPanel {
    private final FilterManager filterManager;
    private final TablePanel tablePanel;
    private final StatsPanel statsPanel;
    private final ChartPanel chartPanel;

    public FilterPanel(FilterManager filterManager, TablePanel tablePanel, StatsPanel statsPanel, ChartPanel chartPanel) {
        this.filterManager = filterManager;
        this.tablePanel = tablePanel;
        this.statsPanel = statsPanel;
        this.chartPanel = chartPanel;

        setLayout(new GridLayout(3, 1)); //lays out 3 spots for 3 sorts

        //checkbox: filter for US Competitors only
        JCheckBox usCompetitorsCheckBox = new JCheckBox("Show Only US Competitors");
        usCompetitorsCheckBox.addActionListener(new FilterActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterManager.setFilterUSCompetitors(usCompetitorsCheckBox.isSelected());
                updateAllPanels(); // Apply filters and update panels
            }
        });
        add(usCompetitorsCheckBox);

        //checkbox: filters out competitors with DNF results
        JCheckBox dnfCheckBox = new JCheckBox("Filter Out People with DNF");
        dnfCheckBox.addActionListener(new FilterActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterManager.setFilterDNF(dnfCheckBox.isSelected());
                updateAllPanels();
            }
        });
        add(dnfCheckBox);

        //checkbox: filters for only Top 25 ranked competitors
        JCheckBox top25RankCheckBox = new JCheckBox("Show Only Top 25 Ranks");
        top25RankCheckBox.addActionListener(new FilterActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterManager.setFilterTop25Rank(top25RankCheckBox.isSelected());
                updateAllPanels();
            }
        });
        add(top25RankCheckBox);
    }

    //updates all panels based on the current filters in FilterManager
    private void updateAllPanels() {
        tablePanel.updateTable(filterManager.getFilteredSolvesList());
        statsPanel.updateStats(filterManager.getFilteredSolvesList());
        chartPanel.updateChart(filterManager.getFilteredSolvesList());
    }

    //abstract inner class for checkbox action listeners
    //idk intelliJ told me I needed this
    private abstract class FilterActionListener implements ActionListener {
        public abstract void actionPerformed(ActionEvent e);
    }
}
