import java.util.List;
import java.util.Arrays;

public class FastestSolves {
    private final int rank;
    private final String person;
    private final List<Integer> solves;
    private final int fastestSolve;
    private final String country;

    public FastestSolves(int rank, String person, int[] solves, int fastestSolve, String country) {
        this.rank = rank;
        this.person = person;
        //convert array to list for streams op.
        this.solves = Arrays.asList(solves[0], solves[1], solves[2], solves[3], solves[4]);
        this.fastestSolve = fastestSolve;
        this.country = country;
    }

    //getters
    public int getRank() { return rank; }
    public String getPerson() { return person; }
    public List<Integer> getSolves() { return solves; }
    public int getFastestSolve() { return fastestSolve; }
    public String getCountry() { return country; }

    //calculate the average solve time
    public double getAverageSolveTime() {
        return solves.stream()
                .filter(solve -> solve != -1) //doesnt include DNFs
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN); //error check
    }

    //check if any solve time is marked as DNF (-1)
    public boolean DNF() {
        return solves.contains(-1);
    }

}
