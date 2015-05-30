package sorting;

import java.lang.List;
import java.lang.ArrayList;

public class SelectionSorter
{
    public void SelectionSort(out List<int> a)
    {
        if (a == null)
            return null;
        if (a.length() < 2)
            return a;

        List<int> newList = new ArrayList<>();
        while (a.length() > 0)
        {
            Iterator<List<int>> it = a.first();
            int currentMin = it.value;
            Iterator<List<int>> itMin = it;
            while (it.hasNext())
            {
                if (it.peekNext().value < currentValue)
                {
                    currentMin = it.peekNext.value;
                    itMin = it.getNext();
                }
                it = it.Next();
            }
            newList.append(currentMin);
            itMin.remove();
        }
        a = newList;
    }
}
