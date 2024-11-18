import java.util.List;

public interface FilterStrategy
{
    List<FastestSolves> apply(List<FastestSolves> solves);
}
