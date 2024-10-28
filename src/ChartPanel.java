import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartPanel extends JPanel {

    private Map<String, Double> averageSolveTimes;

    public ChartPanel(List<FastestSolves> solvesList) {
        updateChart(solvesList); //update the chart with data
        setPreferredSize(new Dimension(600, 500)); // Set panel size

        //add title label
        JLabel titleLabel = new JLabel("Average Fastest Solve Times by Country");
        add(titleLabel);
    }

    public void updateChart(List<FastestSolves> data) {
        //calculate average solve times per country
        averageSolveTimes = data.stream()
                .collect(Collectors.groupingBy(
                        FastestSolves::getCountry, //group by country
                        Collectors.averagingInt(FastestSolves::getFastestSolve) //calculate average solve time
                ));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //panel dimensions
        int width = getWidth();
        int height = getHeight();
        int padding = 50; // Space around the chart

        //draw axes
        g.drawLine(padding, height - padding, padding, padding); //y-axis
        g.drawLine(padding, height - padding, width - padding, height - padding); //x-axis

        //draw bars for each country
        int barWidth = (width - 2 * padding) / averageSolveTimes.size();
        int index = 0;

        //y-axis labels
        double maxAverageTime = averageSolveTimes.values().stream().mapToDouble(Double::doubleValue).max().orElse(1);
        for (int i = 0; i <= 5; i++) {
            double tickValue = (maxAverageTime / 5) * i; //calculate tick values
            int y = height - padding - (int) ((tickValue / maxAverageTime) * (height - 2 * padding)); //position for tick
            g.drawString(String.format("%.1f", tickValue), padding - 30, y); //y-axis labels
        }

        //draw bars for each country
        for (Map.Entry<String, Double> entry : averageSolveTimes.entrySet()) {
            String country = entry.getKey();
            double averageTime = entry.getValue();
            int barHeight = (int) ((averageTime / maxAverageTime) * (height - 2 * padding)); //calc bar height

            int x = padding + index * barWidth + padding / 2; //position of the bar
            int y = height - padding - barHeight; //top position of the bar

            g.setColor(new Color(95, 101, 177)); //color for the bar
            g.fillRect(x, y, barWidth - 10, barHeight); //draw the bar

            //shortened country names for display
            //these countries specifically would always overlap others, so I made them into abbre.
            String displayName = switch (country) {
                case "United Kingdom" -> "UK";
                case "Netherlands" -> "NL";
                default -> country;
            };

            //draw country names on x-axis
            g.setColor(Color.BLACK);
            g.drawString(displayName, x + (barWidth - 10) / 2 - g.getFontMetrics().stringWidth(displayName) / 2, height - padding + 30);

            index++; //move to the next bar
        }
    }
}
