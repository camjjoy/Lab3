import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    //loads data from a CSV file into a list of FastestSolves objects
    public static List<FastestSolves> readFile(String filename) {

        List<FastestSolves> solvesList = new ArrayList<>(); //list to store each FastestSolves entry

        try {
            solvesList = Files.lines(Paths.get(filename))
                    .skip(1) //skip the header line

                    .map(line -> line.split(",")) //split each line by commas

                    .map(data -> { //convert each line to a FastestSolves object
                        int rank = Integer.parseInt(data[0]); //parse rank
                        String person = data[1]; //person's name

                        //parse solve times, marking "DNF" as -1
                        int[] solves = new int[5];
                        for (int i = 0; i < 5; i++) {
                            solves[i] = data[i + 2].equals("DNF") ? -1 : Integer.parseInt(data[i + 2]);
                        }

                        int fastestSolve = Integer.parseInt(data[7]); //parse fastest solve time
                        String country = data[8]; //parse country

                        return new FastestSolves(rank, person, solves, fastestSolve, country);
                    })
                    .collect(Collectors.toList()); //collect results into a List<FastestSolves>

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return solvesList; //return the list of parsed FastestSolves objects
    }
}
