/***************************************************************
* file: Set.java
* author: Jinjing Lee
* class: CS 408 Programming Languages
*
* Implement two versions of a set data structure and write a driver to test all set operations. 
* Union, Intersection and Difference should be included in opreations. 
*
*
* assignment: project1
* date last modified: 02/15/2018
**
****************************************************************/

import java.util.*;

public class Set
{
    private ArrayList<Integer> intSet;

    public Set()
    {
        intSet = new ArrayList<Integer>();
    
    }
    public Set(ArrayList<Integer>addingSet)
    {
        intSet = new ArrayList<Integer>();
        
        for (int i =0; i < addingSet.size(); i++)
            intSet.add(addingSet.get(i));
    
    }
    public ArrayList<Integer> getSet()
    {
        return intSet;
    }
    public int getSetAt(int index)
    {
        return intSet.get(index);
    }
    public void addInt(int value)
    {
        intSet.add(value);
    }
    public int getNumOfElement()
    {
        return intSet.size();
    }
    public void addAll(ArrayList<Integer> addingSet)
    {
        for (int i =0; i < addingSet.size(); i++)
        {
            intSet.add(addingSet.get(i));

        }
    }

    // Function to prompt menu and get values for two integer set
    public static void promptMenu(Set set1, Set set2)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\nEnter first set of integers. Input non-number to end.");
    
        while (sc.hasNextInt()) 
            set1.addInt(sc.nextInt());
        
        sc.next();
        System.out.println("Enter second set of integers. Input non-number to end as well.");
  
        while (sc.hasNextInt()) 
            set2.addInt(sc.nextInt());
        
    }

    public static Set cloneSet(Set set)
    {
        Set newSet = new Set();
        for (int i : set.getSet())
            newSet.getSet().add(i);

        return newSet;
    }

    public void append(Set anotherSet)
    {
        for (int i : anotherSet.getSet())
            this.getSet().add(i);
    }

    public void printSet()
    { 
        System.out.print("[");
        for (Integer current: this.getSet())
            System.out.print(current + " ");
        System.out.println("]");
    }

    public void deleteDuplicate()
    {
        for (int i = 0; i < intSet.size(); i++)
        {
            boolean found = false;
            int j = i + 1;
            while (j < intSet.size() && !found)
            {
                if ( intSet.get(i) ==  intSet.get(j))
                {
                    found = true;

                    // move the last element to the j position
                    intSet.remove(j);
                }
                j++;
            }
        }

    }
    public Set union(Set anotherSet)
    {
        // Put all int in the result list from the original list
        Set unionValues = new Set();

        unionValues.append(this);

        // Loop through all int in anotherSet
        for (int a : anotherSet.getSet())
        {
            boolean isDuplicate = false;

            int i = 0;

            // Compare each value in another list with original, and add the value to result when no duplicates exists in original
            while (i <  unionValues.getSet().size() && !isDuplicate)
            {
                if ( a == unionValues.getSetAt(i))
                    isDuplicate = true;
                i++;
            }

            // Append the unique value to the result from anotherList
            if (!isDuplicate)
                unionValues.addInt(a);
        
        }

        System.out.print("The union of set1 and set2 is: ");
        unionValues.printSet();
        return unionValues;
    }

    public Set intersection(Set anotherSet)
    {
        Set intersection = new Set();

         // Loop through all int in anotherSet
        for (int a : anotherSet.getSet())
        {
            boolean isDuplicate = false;

            int i = 0;

            // Compare each value in another list with original
            while (i <  this.intSet.size() && !isDuplicate)
            {
                if ( a == this.intSet.get(i))
                {   
                    intersection.addInt(a); 
                    isDuplicate = true;
                }

                i++;
            }
        }
        System.out.print("The intersection of set1 and set2 is: ");
        intersection.printSet();
        return intersection;
    }

    public Set difference(Set anotherSet)
    {

        Set union = this.union(anotherSet);
        Set intersection = this.intersection(anotherSet);
        Set difference = new Set();
        
        for (int i : union.getSet())
        {
            boolean isExist = false;
            int j = 0;
            while ( j < intersection.getSet().size() && !isExist)
            {
                if ( i == intersection.getSetAt(j))
                {

                    isExist = true;
                }
                j++;
            }
            if (!isExist)
                difference.addInt(i);

        }
        
        System.out.print("The difference of set1 and set2 is: ");
        difference.printSet();

        return difference;
    }

    

}