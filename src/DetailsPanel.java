import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsPanel extends JPanel {
    private JLabel nameLabel, rankLabel, countryLabel, solvesLabel, fastestSolveLabel, titleLabel;

    public DetailsPanel() {
        setLayout(new GridLayout(6, 1)); //6 rows for each label
        titleLabel = new JLabel("Details Panel");
        nameLabel = new JLabel("Name: ");
        rankLabel = new JLabel("Rank: ");
        countryLabel = new JLabel("Country: ");
        solvesLabel = new JLabel("Solve Times: ");
        fastestSolveLabel = new JLabel("Fastest Solve: ");

        //add labels to the panel
        add(titleLabel);
        add(nameLabel);
        add(rankLabel);
        add(countryLabel);
        add(solvesLabel);
        add(fastestSolveLabel);
    }

    public void displayDetails(FastestSolves solve) {
        //displays each label
        nameLabel.setText("Name: " + solve.getPerson());
        rankLabel.setText("Rank: " + solve.getRank());
        countryLabel.setText("Country: " + solve.getCountry());
        solvesLabel.setText("Solve Times: " + formatSolves(solve.getSolves()));
        fastestSolveLabel.setText("Fastest Solve: " + solve.getFastestSolve());
    }

    private String formatSolves(List<Integer> solves) {
        //convert solve times to a string, with "DNF" for -1 values
        return solves.stream()
                .map(s -> s == -1 ? "DNF" : s.toString())
                .collect(Collectors.joining(", "));
    }
}

