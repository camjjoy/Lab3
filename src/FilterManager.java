import java.util.ArrayList;
import java.util.List;

public class FilterManager
{
    private final List<FastestSolves> originalSolvesList;
    private final List<FilterStrategy> strategies = new ArrayList<>();

    //observers for observer pattern
    private final List<ChartPanel> observers = new ArrayList<>();


    public FilterManager(List<FastestSolves> solvesList)
    {
        this.originalSolvesList = solvesList;
    }

    //observer pattern
    public void registerObserver(ChartPanel observer)
    {
        observers.add(observer);
    }
    public void notifyObservers()
    {
        for(ChartPanel observer : observers)
        {
            observer.updateChart(getFilteredSolvesList());
        }
    }

    //strategy pattern
    public void addStrategy(FilterStrategy strategy)
    {
        strategies.add(strategy);
    }

    public void clearStrategies()
    {
        strategies.clear();
    }

    public List<FastestSolves> getFilteredSolvesList()
    {
        List<FastestSolves> filteredList = originalSolvesList;
        for (FilterStrategy strategy : strategies)
        {
            filteredList = strategy.apply(filteredList);
        }
        return filteredList;
    }
}





/*import java.util.List;
import java.util.stream.Collectors;

public class FilterManager {
    private final List<FastestSolves> originalSolvesList;
    private boolean filterUSCompetitors = false;
    private boolean filterDNF = false;
    private boolean filterTop25Rank = false;

    public FilterManager(List<FastestSolves> solvesList) {
        this.originalSolvesList = solvesList;
    }

    //filtering for US competitors only
    public void setFilterUSCompetitors(boolean enabled) {
        this.filterUSCompetitors = enabled;
    }

    //filtering to exclude people with DNF solve times
    public void setFilterDNF(boolean enabled) {
        this.filterDNF = enabled;
    }

    //filtering for top 25 ranked
    public void setFilterTop25Rank(boolean enabled) {
        this.filterTop25Rank = enabled;
    }

    //apply active filters and return the filtered list of FastestSolves
    public List<FastestSolves> getFilteredSolvesList() {
        List<FastestSolves> filteredList = originalSolvesList;

        if (filterUSCompetitors) {
            filteredList = filteredList.stream()
                    .filter(solve -> "USA".equals(solve.getCountry())) //include only US competitors
                    .collect(Collectors.toList());
        }

        if (filterDNF) {
            filteredList = filteredList.stream()
                    .filter(solve -> !solve.DNF()) //exclude people with DNF solve times
                    .collect(Collectors.toList());
        }

        if (filterTop25Rank) {
            filteredList = filteredList.stream()
                    .filter(solve -> solve.getRank() <= 25) //include only top 25 ranked
                    .collect(Collectors.toList());
        }

        return filteredList; //return the filtered list based on applied filters
    }
}
*/

