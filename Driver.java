
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
* date last modified: 02/20/2018
**
****************************************************************/

import java.util.*;

public class Driver
{

	public static void main(String[]args)
	{
		run();
	}
	
	public static void run()
	{
		Scanner sc = new Scanner(System.in);

		int choice = getSelect(sc);

		if (choice == 1)
			studentSearchApplication(sc);
		else 
			testSetImplementation(sc);

	}

	public static void studentSearchApplication(Scanner sc)
	{

		System.out.println("\nThis is an search application to find student that are registered in certain classes.");
		System.out.println("There are two student lists, one for CS408, and one for CS301.");


		ArrayList<Integer> list408 = new ArrayList<Integer>();
		ArrayList<Integer> list301 = new ArrayList<Integer>();
		list408.addAll(Arrays.asList(1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		list301.addAll(Arrays.asList(1, 13, 4, 17, 14, 8, 16, 12));

		HashMap<Integer, String> students408 = new HashMap<Integer, String>();
		HashMap<Integer, String> students301 = new HashMap<Integer, String>();
		fillStudentHashTable(students408, students301);


		int version = getVersion(sc);
		switch (version)
		{
			case 1:
				studentSearch1(sc, students408, students301, list408, list301);
				break;

			case 2:
				studentSearch2(sc, students408, students301, list408, list301);
				break;
				
			default:
				System.out.println("\nPlease enter either 1 or 2.");
		}

	}
	public static void testSetImplementation(Scanner sc)
	{

		int version = getVersion(sc);
		switch (version)
		{
			case 1:
				version1(sc);
				break;

			case 2:
				version2(sc);
				break;

			default:
				System.out.println("\nPlease enter either 1 or 2.");
		}

	}
	public static int getSelect(Scanner sc)
	{

		System.out.println("\n\nPress 1 to Test the Application of Set implementation.");
		System.out.println("Press 2 to test Set implementation itself.");

		int choice = sc.nextInt();

		switch (choice)
		{
			case 1:
				return 1;

			case 2:
				return 2;

			default:
				System.out.println("\nPlease enter either 1 or 2.");

		}
		return choice;
	}
	
	public static void fillStudentHashTable(HashMap<Integer, String> studentsForCS408, HashMap<Integer, String> studentsForCS301)
	{

   		studentsForCS408.put(1, "Jinjing");
   		studentsForCS408.put(2, "Ryuzo");
   		studentsForCS408.put(3, "Jason");
   		studentsForCS408.put(4, "Dimitri");
   		studentsForCS408.put(5, "Vivian");
   		studentsForCS408.put(6, "Daivid");
   		studentsForCS408.put(7, "Raheja");
   		studentsForCS408.put(8, "Mickey");
   		studentsForCS408.put(9, "Lily");
   		studentsForCS408.put(10, "Marc");
   		studentsForCS408.put(11, "Michael");
   		studentsForCS408.put(12, "Sue");

   		studentsForCS301.put(1, "Jinjing");
   		studentsForCS301.put(13, "Miharu");
   		studentsForCS301.put(4, "Dimitri");
   		studentsForCS301.put(17, "Wesley");
   		studentsForCS301.put(14, "Katy");
   		studentsForCS301.put(8, "Mickey");
   		studentsForCS301.put(15, "Henery");
   		studentsForCS301.put(16, "Paul");
   		studentsForCS301.put(12, "Sue");

	}

	// Function to test student search with non-ADT 
	public static void studentSearch1(Scanner sc, HashMap<Integer, String>students408, HashMap<Integer, String>students301, ArrayList<Integer> list408, ArrayList<Integer> list301)
	{

		boolean isContinue = true;
		while (isContinue)
		{
			System.out.println("\nPress 1 to see a list of students who are taking either or both CS301 and CS408.");
			System.out.println("Press 2 to see a list of students who are registered both in CS408 and CS301.");
			System.out.println("Press 3 to see a list of students who are registered only in CS408 and not CS301.");

			int choice = sc.nextInt();

			switch (choice)
			{
				case 1:
					findEither(students408, students301, list408, list301);
					break;
				case 2:
					findBoth(students408, students301, list408, list301);
					break;
				case 3:
					findOnly408(students408, students301, list408, list301);
					break;
				default:
					System.out.println("\nPlease enter either 1 or 2.");



			}
			isContinue = askIfRepeat(sc);
		}
		
	}
	public static void studentSearch2(Scanner sc, HashMap<Integer, String>students408, HashMap<Integer, String>students301, ArrayList<Integer> list408, ArrayList<Integer> list301 )
	{
		Set set408 = new Set();
		Set set301 = new Set();

		set408.addAll(list408);
		set301.addAll(list301);
		

		boolean isContinue = true;
		while (isContinue)
		{
			System.out.println("\nPress 1 to see a list of students who are taking either or both CS301 and CS408.");
			System.out.println("Press 2 to see a list of students who are registered both in CS408 and CS301.");
			System.out.println("Press 3 to see a list of students who are registered only in CS408 and not CS301.");

			int choice = sc.nextInt();

			switch (choice)
			{
				case 1:
					findEither(students408, students301, set408, set301);
					break;
				case 2:
					findBoth(students408, students301, set408, set301);
					break;
				case 3:
					findOnly408(students408, students301, set408, set301);
					break;
				default:
					System.out.println("\nPlease enter either 1 or 2.");



			}
			isContinue = askIfRepeat(sc);
		}
	}

	// Non-ADT version of function to find a student that are registered either or both classes
	public static void findEither( HashMap<Integer,String> students408, HashMap<Integer,String> students301, ArrayList<Integer>list408, ArrayList<Integer>list301 )
	{
		ArrayList<Integer> result = union(list408, list301);
		System.out.println("List of students who are taking either or both CS301 and CS408.");

		for (int i = 0; i < result.size(); i++)
		{
		 	int id = result.get(i);
		 	String name = students408.get(id);
		 	if (name == null)
		 		name = students301.get(id);
		 	System.out.println(name);
		}

	}

	// ADT version of function to find a student that are registered either or both classes (Operator overloading)
	public static void findEither( HashMap<Integer,String> students408, HashMap<Integer,String> students301, Set set408, Set set301 )
	{
		Set result = set408.union(set301);
		result.printSet();
		System.out.println("A list of students who are registered either or both in CS408 and CS301.");

		for (int i = 0; i < result.getNumOfElement(); i++)
		{
		 	int id = result.getSetAt(i);
		 	String name = students408.get(id);
		 	if (name == null)
		 		name = students301.get(id);
		 	System.out.println(name);
		}

	}

	// Non-ADT version of function to find a student that are registered both classes
	public static void findBoth( HashMap<Integer,String> students408, HashMap<Integer,String> students301, ArrayList<Integer>list408, ArrayList<Integer>list301 )
	{

		ArrayList<Integer> result = intersection(list408, list301);
		System.out.println("A list of students who are registered both in CS408 and CS301.");

		for (int i = 0; i < result.size(); i++)
		{
		 	int id = result.get(i);
		 	String name = students408.get(id);
		 	if (name == null)
		 		name = students301.get(id);
		 	System.out.println(name);
		}
	}

	// ADT version of function to find a student that are registered both classes
	public static void findBoth( HashMap<Integer,String> students408, HashMap<Integer,String> students301, Set set408, Set set301  )
	{

		Set result = set408.intersection(set301);
		System.out.println("Print hereeeeeeeee");
		set301.printSet();
		System.out.println("A list of students who are registered both in CS408 and CS301.");

		for (int i = 0; i < result.getNumOfElement(); i++)
		{
		 	int id = result.getSetAt(i);
		 	String name = students408.get(id);
		 	if (name == null)
		 		name = students301.get(id);
		 	System.out.println(name);
		}
	}

	// Non-ADT version of function to find a student that are registered only in CS408
	public static void findOnly408( HashMap<Integer,String> students408, HashMap<Integer,String> students301, ArrayList<Integer>list408, ArrayList<Integer>list301 )
	{
		ArrayList<Integer> result = difference(list408, list301);
		System.out.println("A list of students who are registered only in CS408.");

		for (int i = 0; i < result.size(); i++)
		{
		 	int id = result.get(i);
		 	String name = students408.get(id);
		 	if (name == null)
		 		name = students301.get(id);
		 	System.out.println(name);
		}

	}
	// ADT version of function to find a student that are registered only in CS408
	public static void findOnly408( HashMap<Integer,String> students408, HashMap<Integer,String> students301, Set set408, Set set301  )
	{
		Set result = set408.difference(set301);
		System.out.println("A list of students who are registered only in CS408.");
		System.out.println("Print hereeeeeeeee");
		result.printSet();
		for (int i = 0; i < result.getNumOfElement(); i++)
		{
		 	int id = result.getSetAt(i);
		 	String name = students408.get(id);
		 	if (name == null)
		 		name = students301.get(id);
		 	System.out.println(name);
		}

	}

	public static int getVersion(Scanner sc)
	{
		while (true)
		{
			System.out.println("\n\nPress 1 to test non-ADT version.");
			System.out.println("Press 2 to test ADT version.");

			int choice = sc.nextInt();

			switch (choice)
			{
				case 1:
					return 1;

				case 2:
					return 2;

				default:
					System.out.println("\nPlease enter either 1 or 2.");

			}

		}

	}
	public static void version1(Scanner sc)
	{

		ArrayList<Integer> myList1 = new ArrayList<Integer>();
		ArrayList<Integer> myList2 = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();

		getListValues(sc, myList1, myList2);
		deleteDuplicateItem(myList1);
		deleteDuplicateItem(myList2);

		boolean isContinue = true;

		while (isContinue)
		{
			
			System.out.println( "\n\nPress 1 for Union, 2 for intersection, and 3 for difference.");
			int operation = sc.nextInt();

			switch (operation)
			{
			case 1:
	
				result = union(myList1, myList2);
				System.out.println( "\nResult for union");
				printList(result);
				break;

			case 2:

				result = intersection(myList1, myList2);
				System.out.println("\nResult for intersection");
				printList(result);
				break;

			case 3:
				
				result = difference(myList1, myList2);
				System.out.println( "\nResult for difference");
				printList(result);
				break;

			default:
				System.out.println("\nInvalid number. Enter 1, 2, or 3 to choose an operation." );

			}

			isContinue = askIfRepeat(sc);
		}

	}

	public static boolean askIfRepeat(Scanner sc)
	{

		System.out.println("\nDo you want to test another operation?");
		System.out.println("Enter y to continue, n to end the program.");
		char choice = sc.next().charAt(0);

		switch (choice)
		{
		case 'y':
			return true;
		case 'n':
			System.out.println("Good bye!");
			return false;
		default:
			System.out.println("Invalid letter, Start over!");
			return false;
		}

	}
	public static void version2(Scanner sc)
	{
		Set set1 = new Set();
		Set set2 = new Set();
		Set result = new Set();

		getSetInput(sc, set1, set2);
		
		// Delete all the duplicates from both lists
		set1.deleteDuplicate();
		set2.deleteDuplicate();

		boolean isContinue = true;

		while (isContinue)
		{
			System.out.println("\npress 1 for union, 2 for intersection, and 3 for difference." );
			int operation = sc.nextInt();
		
			switch (operation)
			{
			case 1:
				result = set1.union(set2);
				System.out.println("\nResult for union");
				result.printSet();
				break;

			case 2:
				result = set1.intersection(set2);
				System.out.println("\nResult for intersection");
				result.printSet();
				break;

			case 3:
				result = set1.difference(set2);
				System.out.println("\nResult for difference");
				result.printSet();

				break;

			default:
				System.out.println("\ninvalid number. enter 1, 2, or 3 to choose an operation.");
			}

			isContinue = askIfRepeat(sc);

		}
	}

	// Prompt a user to enter values and store them for non-ADT
	public static void getListValues(Scanner sc, ArrayList<Integer>myList1,  ArrayList<Integer> myList2 )
	{

        System.out.println("\n\nEnter first set of integers. Input 999 to end.");
    

        while (sc.hasNextInt())
        {
        	int n = sc.nextInt();
        	if (n == 999)
        		break;
        	else
            	myList1.add(n);
          
        }

        //sc.next();
        System.out.println("Enter second set of integers. Input 999 to end.");

        while (sc.hasNextInt()) 
        {
        	int d = sc.nextInt();
        	if (d == 999)
        		break;
        	else 
            	myList2.add(d);
           
        }
	}

	public static void printList( ArrayList<Integer>list)
	{
		if (list.size() == 0)
			System.out.print("[]" );

		else 
		{
			System.out.print("[" +  list.get(0));
			for (int i = 1; i < list.size(); i++)
				System.out.print( ", " + list.get(i));
			System.out.println("]");
		}
	}

	public static void printLists( ArrayList<Integer>list1,  ArrayList<Integer>list2)
	{
		System.out.print("List1: ");
		System.out.print( "[" + list1.get(0));

		for (int i = 1; i < list1.size(); i++)
			System.out.print( ", " + list1.get(i));
		System.out.println("]" );

		System.out.print("List2: ");
		System.out.print( "[" + list2.get(0));

		for (int i = 1; i < list2.size(); i++)
			System.out.print( ", " + list2.get(i));
		System.out.println("]" );
	
	}

	public static void printSets( Set set1, Set set2)
	{
		System.out.print("Set 1: ");
		System.out.print( "[" + set1.getSetAt(0));

		for (int i = 1; i < set1.getNumOfElement(); i++)
			System.out.print( ", " + set1.getSetAt(i));
		System.out.println("]" );

		System.out.print("Set 2: ");
		System.out.print( "[" + set2.getSetAt(0));

		for (int i = 1; i < set2.getNumOfElement(); i++)
			System.out.print( ", " + set2.getSetAt(i));
		System.out.println("]" );
	}

	public static void getSetInput(Scanner sc, Set set1, Set set2)
	{

		
        System.out.println("\n\nEnter first set of integers. Input 999 to end.");
    

        while (sc.hasNextInt())
        {
        	int n = sc.nextInt();
        	if (n == 999)
        		break;
        	else
            	set1.addInt(n);
          
        }

        //sc.next();
        System.out.println("Enter second set of integers. Input 999 to end.");

        while (sc.hasNextInt()) 
        {
        	int d = sc.nextInt();
        	if (d == 999)
        		break;
        	else 
            	set2.addInt(d);
           
        }
	}

	public static void deleteDuplicateItem(ArrayList<Integer>list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			boolean found = false;
			int j = i + 1;
			while (j < list.size() && !found)
			{
				if ( list.get(i) ==  list.get(j))
				{
					found = true;
					list.remove(j);
				}
				j++;
			}
		}
	}

	public static void appendNonDuplicate(ArrayList<Integer>from, ArrayList<Integer>to)
	{
		for (int i = 0; i < from.size(); i++)
		{
			int j = 0;
			boolean found = false;
			while (j < to.size() && !found)
			{
				if (from.get(i) == to.get(j))
					found = true;
				j++;
			}
			if (!found)
			{
				to.add(from.get(i));
			}
		}
	}

	public static void subtractList(ArrayList<Integer> from, ArrayList<Integer> intersectionList)
	{
		for (int i : intersectionList)
		{
			int j = 0;
			boolean found = false;
			while (j < from.size() && !found)
			{
				if (i == from.get(j))
				{
					from.remove(j);
					found = true;
				}
				j++;
			}
		}
	}

	public static void copyList( ArrayList<Integer> from, ArrayList<Integer> to)
	{
		
		for (int i : from)
		{
			to.add(i);
		}
	}

	public static ArrayList<Integer> union( ArrayList<Integer> list1, ArrayList<Integer> list2 )
	{

		ArrayList<Integer> result = new ArrayList<Integer>();

		// First copy the first list in result
		copyList(list1, result);

		// Append int in myList2 into result
		appendNonDuplicate(list2, result);

		return result;

	}

	public static ArrayList<Integer> intersection( ArrayList<Integer> list1, ArrayList<Integer> list2)
	{	

		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i :list1)
		{
			int j = 0;
			boolean found = false;
			while (j < list2.size() && !found)
			{
				if ( i == list2.get(j))
				{
					result.add(i);
				}
				j++;
			}
		}
		return result;
	}

	public static ArrayList<Integer> difference(ArrayList<Integer> fromList, ArrayList<Integer> subtractingList)
	{
		ArrayList<Integer> intersectionList = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();

		intersectionList = intersection(fromList, subtractingList);

		// copy fromList into result list
		copyList(fromList, result);

		// Subtract intersection from result list 
		subtractList(result, intersectionList);

		return result;
		
	}

	
}

