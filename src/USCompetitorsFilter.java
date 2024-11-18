import java.util.List;
import java.util.stream.Collectors;

public class USCompetitorsFilter implements FilterStrategy
{
    public List<FastestSolves> apply(List<FastestSolves> solves)
    {
        return solves.stream()
                .filter(solve -> "USA".equals(solve.getCountry()))
                .collect(Collectors.toList());
    }
}
