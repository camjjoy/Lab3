import java.util.List;
import java.util.stream.Collectors;

public class DNFFilter implements FilterStrategy
{
    public List<FastestSolves> apply(List<FastestSolves> solves)
    {
        return solves.stream()
                .filter(solve -> !solve.DNF())
                .collect(Collectors.toList());
    }
}
