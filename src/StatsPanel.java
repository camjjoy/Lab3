import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class StatsPanel extends JPanel {
    private final JLabel averageSolveLabel;
    private final JLabel maxSolveLabel;
    private final JLabel minSolveLabel;
    private final JLabel titleLabel;
    private List<FastestSolves> filteredData; //data after applying filters

    public StatsPanel(List<FastestSolves> data) {
        this.filteredData = data; //OG unfiltered data

        //4 rows for each label
        setLayout(new GridLayout(4, 1));

        //labels
        titleLabel = new JLabel();
        averageSolveLabel = new JLabel();
        maxSolveLabel = new JLabel();
        minSolveLabel = new JLabel();

        //add labels
        add(titleLabel);
        add(averageSolveLabel);
        add(maxSolveLabel);
        add(minSolveLabel);

        //update stats
        updateStats();
    }

    //updates Stats using filtered data
    public void updateStats(List<FastestSolves> data) {
        //update filtered data based on rank and call updateStats
        this.filteredData = data.stream()
                .filter(solve -> solve.getRank() <= 100)//there's only 100 ranks
                .collect(Collectors.toList());
        updateStats();
    }

    //update stats
    private void updateStats() {
        //calculate average, maximum, and minimum solve times
        double avgSolve = filteredData.stream()
                .mapToInt(FastestSolves::getFastestSolve)
                .average().orElse(0); //default to 0 if no values are present

        int maxSolve = filteredData.stream()
                .mapToInt(FastestSolves::getFastestSolve)
                .max().orElse(0); //default to 0 if no values are present

        int minSolve = filteredData.stream()
                .mapToInt(FastestSolves::getFastestSolve)
                .min().orElse(0); //default to 0 if no values are present

        //update labels with new stats
        titleLabel.setText("Stats Panel");
        averageSolveLabel.setText("Average Fastest Solve: " + avgSolve);
        maxSolveLabel.setText("Max Fastest Solve: " + maxSolve);
        minSolveLabel.setText("Min Fastest Solve: " + minSolve);
    }
}


