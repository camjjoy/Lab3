import java.util.List;

public class Main {
    public static void main(String[] args) {
        //csv
        String file = "solves.csv";

        //read the file and load the data into a list of FastestSolves objects
        List<FastestSolves> solvesList = FileReader.readFile(file);

        //display the loaded data for testing
        solvesList.forEach(System.out::println); //prints each object
    }
}
