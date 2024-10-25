import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader
{

    // Method to load the data from a CSV file
    public static List<FastestSolves> readFile(String filename) {

        List<FastestSolves> solvesList = new ArrayList<>(); // array list of each person

        try {

            solvesList = Files.lines(Paths.get(filename))
                    .skip(1) // skips header line

                    .map(line -> line.split(",")) //splits at each comma

                    .map(data -> { //converts each line to FastestSolves object

                        //person
                        String person = data[1];

                        //solves
                        int[] solves = new int[5];
                        for (int i = 0; i < 5; i++) {
                            solves[i] = data[i + 2].equals("DNF") ? -1 : Integer.parseInt(data[i + 2]);
                        }

                        //fastest solve
                        int fastestSolve = Integer.parseInt(data[7]);

                        //country
                        String country = data[8];

                        return new FastestSolves(person, solves, fastestSolve, country);
                    })
                    .collect(Collectors.toList()); //results go into List<FastestSolves>

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return solvesList;
    }
}
