import java.util.List;
import java.util.stream.Collectors;

public class Top25Filter implements FilterStrategy
{
    public List<FastestSolves> apply(List<FastestSolves> solves)
    {
        return solves.stream()
                .filter(solve->solve.getRank() <= 25)
                .collect(Collectors.toList());

    }
}
