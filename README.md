//LAB 4 INFO
*ALL CHANGES ARE COMMENTED OUT*
*ON CLASSES WITH MAJOR CHANGE (FilterManager AND FilterPanel) OLD CODE IS COMMENTED OUT FOR BETTER COMPARISON*

Patterns: Strategy and Observer
  Strategy: 
  - Goal: Make FilterManager more flexible/reusable. Make different filtering into separate classes
  - Implementation:
  -   FilterStrategy Interface with the method apply()
  -   Each filter has their own class: USCompetitorFilter, Top25Filter, DNFFilter
  -   integrate FilterStrategy with FilterManager
  
  Observer:
  - Goal: dynamically update the ChartPanel
  - Implementation:
  -   Modify FilterManager with register() and notify() observer classes
  -   Integrate with ChartPanel: ChartPanel is an observer and is notified when filters are applied



//Data Set includes top 100 ranked competitors from an international rubiks cube competition
// If you are interested the link is here: https://www.kaggle.com/datasets/darshanvss/top-1000-fastest-rubiks-cube-solves 

//Also when selecting data from the tablePanel, the table needs to be sorted in ascending order to display the correct details in the detailPanel
