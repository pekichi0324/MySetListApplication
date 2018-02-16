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
* date last modified: 02/12/2018
**
****************************************************************/

import java.util.*;

public class Set
{
	private TreeSet<Integer> intSet1;
	private TreeSet<Integer> intSet2;

	public Set()
	{
		intSet1 = new TreeSet<Integer>();
		intSet2 = new TreeSet<Integer>();
	}
	public TreeSet<Integer> getIntSet1()
	{
		return intSet1;
	}
	public TreeSet<Integer> getIntSet2()
	{
		return intSet2;
	}
	public void addToSet1(int value)
	{
		intSet1.add(value);
	}
	public void addToSet2(int value)
	{
		intSet2.add(value);
	}

	public static void main(String[]args)
	{
		Set mySet = new Set();
		promptMenu(mySet);
		mySet.union();
		mySet.intersection();
		 
    }

    // Function to prompt menu and get values for two integer set
    public static void promptMenu(Set mySet)
    {
    	Scanner sc = new Scanner(System.in);

    	System.out.println("\n\nEnter first set of integers. Input non-number to end.");
  		
  		int currentValue;
        while (sc.hasNextInt()) 
        	mySet.addToSet1(sc.nextInt());
        
        sc.next();
        System.out.println("Enter second set of integers. Input non-number to end as well.");
  
        while (sc.hasNextInt()) 
        	mySet.addToSet2(sc.nextInt());
        
    }

    public void printSet(TreeSet<Integer> theList)
    { 
    	for (Integer current: theList)
    	 	System.out.print(current + " ");
    	System.out.println("");
    }

    public void union()
    {
    	TreeSet<Integer> unionValues = new TreeSet<Integer>(intSet1);
		System.out.print("The union of set1 and set2 is: ");
   		unionValues.addAll(intSet2);
    	
    	printSet(unionValues);
    }

    public void intersection()
    {
    	TreeSet<Integer> intersectionValues = new TreeSet<Integer>();
		System.out.print("The intersection of set1 and set2 is: ");

		for (int value: intSet1)
		{
			if ( intSet2.contains(value))
   				intersectionValues.add(value);
   		}
    	
    	printSet(intersectionValues);
    }
    public void difference()
    {
    	// Get a copy of the set that has more elements
    	if (intSet1.size() > intSet2.size())
    	{
    		TreeSet<Integer> diffValues = new TreeSet<Integer>(intSet1);
    		intSet2
    	}
    	else
    	{
    		TreeSet<Integer> diffValues = new TreeSet<Integer>(intSet2);
    	}
    	
		System.out.print("The difference of set1 and set2 is: ");

    	
    	printSet(intersectionValues);
    }

}