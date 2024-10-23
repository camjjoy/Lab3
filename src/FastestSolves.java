import java.util.List;
import java.util.Arrays;

public class FastestSolves
{
    private final String person;
    private final List<Integer> solves;
    private final int fastestSolve;
    private final String country;

    public FastestSolves(String person, int[] solves, int fastestSolve, String country)
    {
       this.person = person;
       this.solves = Arrays.asList(solves[0], solves[1], solves[2], solves[3], solves[4]);
       this.fastestSolve = fastestSolve;
       this.country = country;
    }

    // Getters
    public String getPerson() {return person;}

    public List<Integer> getSolves() {return solves;}

    public int getFastestSolve() {return fastestSolve;}

    public String getCountry() {return country;}

    public double getAverageSolveTime()
    {
        return solves.stream().filter(solve->solve!= -1)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
    }

    public boolean DNF() {return solves.contains(-1);}

    @Override
    public String toString()
    {
        return "Person: " + person + ", Fastest Solve: " + fastestSolve + ", Country:" + country;
    }
}
