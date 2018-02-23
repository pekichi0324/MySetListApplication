#include <iostream>
#include "Set.h"
#include <map>
#include <string>
using namespace std;

void printList(const int* list, const int size)
{
	if (size == 0)
		cout << "[]";
	else
	{
		cout << "[" << list[0];
		for (int i = 1; i < size; i++)
			cout << ", " << list[i];
		cout << "]" << endl;
	}
}

void printLists(const int* list1, int size1, const int* list2, int size2 )
{
	cout << "\nYour Lists: " << endl;
	cout << "List1: ";
	cout << "[" << list1[0];
	for (int i = 1; i < size1; i++)
		cout << ", " << list1[i];
	cout << "]" << endl;

	cout << "List2: ";
	cout << "[" << list2[0];
	for (int i = 1; i < size2; i++)
		cout << ", " << list2[i];
	cout << "]" << endl;
}

void getInputVersion1(int* myList1, int &numOfElement1, int* myList2, int &numOfElement2 )
{
	cout << "Enter first set of integers.Input 999 to end." << endl;
	bool isDone = false;

	while (!isDone && (numOfElement1) < 20)
	{
		int x;
		cin >> x;
		if (x == 999)
			isDone = true;
		else
		{
			*(myList1 + numOfElement1) = x;
			(numOfElement1)++;
		}
	}

	cout << "Enter second set of integers.Input 999 to end." << endl;
	isDone = false;
	while (!isDone && numOfElement2 < 20)
	{
		int y;
		cin >> y;

		if (y == 999)
			isDone = true;
		else
		{
			*(myList2 + numOfElement2) = y;
			(numOfElement2)++;
		}
	} 
}

void printSets(const Set &set1, const Set &set2)
{
	cout << "Your lists:" << endl;
	cout << "List 1: [" << set1.GetIntAt(0);

	for (int i = 1; i < set1.GetNumOfElement(); i++)
		cout << ", " << set1.GetIntAt(i);

	cout << "]\nList 2: [" << set2.GetIntAt(0);

	for (int i = 1; i < set2.GetNumOfElement(); i++)
		cout << ", " << set2.GetIntAt(i);
	cout << "]\n";

}
void getInputVersion2(Set &set1, Set &set2)
{
	cout << "Enter first set of integers.Input 999 to end." << endl;
	bool isDone = false;

	while (!isDone && set1.GetNumOfElement() <= 20)
	{
		int x;
		cin >> x;
		if (x == 999)
			isDone = true;
		else
			set1.AppendValue(x);
		
	}

	cout << "Enter second set of integers.Input 999 to end." << endl;
	isDone = false;
	while (!isDone && set2.GetNumOfElement() <= 20)
	{
		int y;
		cin >> y;
		if (y == 999)
			isDone = true;
		else
			set2.AppendValue(y);
	}
	printSets(set1, set2);
}

void deleteDuplicate(int* list, int &numOfElement)
{
	for (int i = 0; i < numOfElement; i++)
	{
		bool found = false;
		int j = i + 1;
		while (j < numOfElement && !found)
		{
			if (*(list + i) == *(list + j))
			{
				found = true;

				// move the last element to the j position
				*(list + j) = *(list + (numOfElement) - 1); 
				*(list + numOfElement) = 0;
				numOfElement--;
			}
			j++;
		}
	}
}
void CopyList(const int* from, const int &fromSize, int* to, int &toSize)
{
	toSize = fromSize;
	for (int i = 0; i < fromSize; i++)
	{
		to[i] = from[i];
	}
}

void AppendNonDuplicate(const int* from, const int &fromSize, int* to, int &toSize)
{
	for (int i = 0; i < fromSize; i++)
	{
		int j = 0;
		bool found = false;
	
		while (j < toSize && !found)
		{
			if (from[i] == to[j])
				found = true;
			j++;
		}
		if (!found)
		{
			to[toSize] = from[i];
			toSize++;
		}
		
	}
}

void SubtractList(int* subtractedFromList, int &subtractedFromListSize, const int* intersectionList, const int intersectionSize)
{

	for (int i = 0; i < intersectionSize; i++)
	{
		int j = 0;
		bool found = false;
		while (j < subtractedFromListSize && !found)
		{
			if (*(intersectionList + i) == *(subtractedFromList + j))
			{	
				*(subtractedFromList + j) = *(subtractedFromList + --subtractedFromListSize);
			}
			j++;
		}
	}
}

int* Union( const int* myList1, const int &size1, const int* myList2, const int &size2, int &resultSize)
{
	int* result = new int[40];

	// First copy the first list in result
	CopyList(myList1, size1, result, resultSize);

	// Append int in myList2 into result
	AppendNonDuplicate(myList2, size2, result, resultSize);

	return result;
}

int* Intersection(const int* myList1, const int &size1, const int* myList2, const int &size2, int &resultSize)
{
	int* result = new int[20];

	for (int i = 0; i < size1; i++)
	{
		int j = 0;
		bool found = false;
		while (j < size2 && !found)
		{
			if (myList1[i] == myList2[j])
			{
				result[resultSize] = myList1[i];
				resultSize++;
			}
			j++;
		}
	}
	return result;
}

int* Difference(const int* subtractedFrom, const int &from, const int* subtractingList, const int &subtractingListSize, int &resultSize)
{
	int* intersectionList = new int[20];
	int intersectionNumOfEl = 0;
	int* result = new int[20];

	// Copy the subtractedFrom list to result
	CopyList(subtractedFrom, from, result, resultSize);

	intersectionList = Intersection(subtractedFrom, from, subtractingList, subtractingListSize, intersectionNumOfEl);

	// Subtract intersection from result 
	SubtractList(result, resultSize, intersectionList, intersectionNumOfEl);

	delete []intersectionList;
	return result;
}

bool AskIfRepeat()
{

	cout << "\nDo you want to test another operation?" << endl;
	cout << "Enter y to continue, n to end the program." << endl;
	char choice;
	cin >> choice;

	switch (choice)
	{
	case 'y':
		return true;
	case 'n':
		cout << "Good bye!" << endl;
		return false;
	default:
		cout << "Invalid letter, Start over!" << endl;
		return false;
	}

}

void Version1()
{
	int* myList1 = new int[20];
	int* myList2 = new int[20];
	int size1 = 0;
	int size2 = 0;
	getInputVersion1(myList1, size1, myList2, size2);
	deleteDuplicate(myList1, size1);
	deleteDuplicate(myList2, size2);

	printLists(myList1, size1, myList2, size2);

	bool isContinue = true;
	while (isContinue)
	{
		int numOfResult = 0;
		int* result = new int[40];

		cout << "\nPress 1 for Union, 2 for intersection, and 3 for difference." << endl;
		int operation;
		cin >> operation;
		switch (operation)
		{
		case 1:
			
			result = Union(myList1, size1, myList2, size2, numOfResult);
			cout << "\nResult for union!!!!" << endl;
			printList(result, numOfResult);
			break;

		case 2:
			result = Intersection(myList1, size1, myList2, size2, numOfResult);
			cout << "\nResult for intersection" << endl;
			printList(result, numOfResult);
			break;

		case 3:
			result = Difference(myList1, size1, myList2, size2, numOfResult);
			cout << "\nResult for difference" << endl;
			printList(result, numOfResult);
			break;

		default:
			cout << "\nInvalid number. Enter 1, 2, or 3 to choose an operation." << endl;

		}
		delete[] result;

		isContinue = AskIfRepeat();
	}
}
void Version2()
{
	Set set1 = Set();
	Set set2 = Set();
	Set result = Set();

	getInputVersion2(set1, set2);
	
	// Delete all the duplicates from both lists
	set1.ClearDuplicate();
	set2.ClearDuplicate();
	bool isContinue = true;

	while (isContinue)
	{
		cout << "\npress 1 for union, 2 for intersection, and 3 for difference." << endl;
		int operation;
		cin >> operation;
		switch (operation)
		{
		case 1:
			result = set1 + set2;
			cout << "\nResult for union" << endl;
			result.Print();
			break;

		case 2:
			result = set1 * set2;
			cout << "\nResult for intersection" << endl;
			result.Print();
			break;

		case 3:
			result = set1 - set2;
			cout << "\nResult for difference" << endl;
			result.Print();

			break;

		default:
			cout << "\ninvalid number. enter 1, 2, or 3 to choose an operation." << endl;
		
		isContinue = AskIfRepeat();
		}
	}
}

void InitializeSet(Set &set408, Set &set301)
{
	for (int i = 0; i < 12; i++)
		set408.AppendValue(i);
	set408.SetNumOfElement(12);

	set301.AppendValue(1);
	set301.AppendValue(13);
	set301.AppendValue(4);
	set301.AppendValue(17);
	set301.AppendValue(14);
	set301.AppendValue(8);
	set301.AppendValue(16);
	set301.AppendValue(12);
	set301.AppendValue(15);

	set301.SetNumOfElement(9);

}

void printHash(const map<int, string>&students408, const map<int, string>&students301, Set &result)
{
	for (int i = 0; i < result.GetNumOfElement(); i++)
	{
		int id = *(result.GetList() + i);
		if (id >= 0)
		{
			if (students408.count(id))
				cout << students408.find(id)->second << endl;
			else
				cout << students301.find(id)->second << endl;
		}
	}
}

void findEither(const map<int,string>students408, const map<int, string>students301, const int* list408, const int &size408, const int* list301, const int &size301)
{ 
	int resultSize = 0;
	int* result = Union(list408, size408, list301, size301, resultSize); 
	printList(result, resultSize);
	cout << "\n\nList of students who are taking either or both CS301 and CS408." << endl;
	for (int i = 0; i < resultSize; i++)
	{
		int id = *(result + i);

		if (students408.count(id))
			cout << students408.find(id)->second << endl;
		else
			cout << students301.find(id)->second << endl;
	}
}
void findEitherADT(const map<int,string>&students408, const map<int, string>&students301)
{
	Set cs408 = Set();
	Set cs301 = Set();
	InitializeSet(cs408, cs301);

	Set result = Set();
	result = cs408 + cs301;

	printHash(students408, students301, result);

}
void findBoth(const map<int, string>&students408, const map<int, string>&students301, const int* list408, const int &size408, const int* list301, const int &size301)
{
	int resultSize = 0;
	int* result = Intersection(list408, size408, list301, size301, resultSize); 
	printList(result, resultSize);
	cout << "\n\nList of students who are taking both CS301 and CS408." << endl;
	for (int i = 0; i < resultSize; i++)
	{
		int id = *(result + i);

		if (students408.count(id))
			cout << students408.find(id)->second << endl;
		else
			cout << students301.find(id)->second << endl;
	}
}

void findBothADT(map<int, string>&students408, map<int, string>&students301)
{
	Set cs408 = Set();
	Set cs301 = Set();
	InitializeSet(cs408, cs301);

	Set result = Set();
	result = cs408 * cs301;

	printHash(students408, students301, result);

}
void findOnly408(const map<int, string>&students408, const map<int, string>&students301, const int* list408, const int &size408, const int* list301, const int &size301)
{

	int resultSize = 0;
	int* result = Difference(list408, size408, list301, size301, resultSize);
	cout << "\n\nList of students who are taking only CS408." << endl;

	for (int i = 0; i < resultSize; i++)
	{
		int id = *(result + i);
		if (id >= 0)
		{
			if (students408.count(id))
				cout << students408.find(id)->second << endl;
			else
				cout << students301.find(id)->second << endl;
		}
	}
}

void findOnly408ADT(map<int, string>&students408, map<int, string>&students301)
{
	Set cs408 = Set();
	Set cs301 = Set();
	InitializeSet(cs408, cs301);

	Set result = Set();
	result = cs408 - cs301;

	printHash(students408, students301, result);

}

void StudentSearch(bool isADT, int* list408, int &size408, int* list301,int &size301, map<int, string> students408, map<int, string>students301)
{
	bool isContinue = true;
	while (isContinue)
	{
		cout << "\nPress 1 to see a list of students who are taking either or both CS301 and CS408." << endl;
		cout << "Press 2 to see a list of students who are registered both in CS408 and CS301." << endl;
		cout << "Press 3 to see a list of students who are registered only in CS408 and not CS301." << endl;

		int choice;
		cin >> choice;

		switch (choice)
		{
		case 1:
			if (isADT)
				findEitherADT(students408, students301);
			else
				findEither(students408, students301, list408, size408, list301, size301);
			break;
		case 2:
			if (isADT)
				findBothADT(students408, students301);
			else
				findBoth(students408, students301, list408, size408, list301, size301);
			break;
		case 3:
			if (isADT)
				findOnly408ADT(students408, students301);
			else 
				findOnly408(students408, students301, list408, size408, list301, size301);
			break;
		default:
			cout << "\nPlease enter either 1 or 2." << endl;
		}
		isContinue = AskIfRepeat();
	}
}


void InitializeList(int* list408, int &size408, int* list301, int &size301)
{
	for (int i = 0; i < 12; i++)
		*(list408 + i) = i;
	size408 = 12;

	*(list301) = 1;
	*(list301 + 1) = 13;
	*(list301 + 2) = 4;
	*(list301 + 3) = 17;
	*(list301 + 4) = 14;
	*(list301 + 5) = 8;
	*(list301 + 6) = 16;
	*(list301 + 7) = 12;
	*(list301 + 8) = 15;
	size301 = 9;
	//{ 1, 13, 4, 17, 14, 8, 16, 12 };

}

void InitializeMap(map<int, string>&studentsForCS408, map<int, string>&studentsForCS301)
{
	studentsForCS408[0] = "Misa";
	studentsForCS408[1] =  "Jinjing";
	studentsForCS408[2] = "Ryuzo";
	studentsForCS408[3] = "Jason";
	studentsForCS408[4] = "Dimitri";
	studentsForCS408[5] ="Vivian";
	studentsForCS408[6] = "David";
	studentsForCS408[7] = "Raheja";
	studentsForCS408[8] = "Mickey";
	studentsForCS408[9] = "Lily";
	studentsForCS408[10] = "Marc";
	studentsForCS408[11] = "Michael";
	

	studentsForCS301[1] = "Jinjing";
	studentsForCS301[13] = "Miharu";
	studentsForCS301[4] = "Dimitri";
	studentsForCS301[17] = "Wesley";
	studentsForCS301[14] = "Katy";
	studentsForCS301[8] = "Mickey";
	studentsForCS301[12] = "Minnie";
	studentsForCS301[15] = "Henery";
	studentsForCS301[16] = "Paul";
	
}

int GetVersion()
{
	while (true)
	{
		int version;
		cout << "\nPress 1 to test version1 (non ADT)" << endl;
		cout << "Press 2 to test version2 (ADT)" << endl;
		cin >> version;

		switch (version)
		{
		case 1:
			return 1;
		case 2:
			return 2;

		default:
			cout << "Please enter either 1 or 2." << endl;
		}
	}
}
void StudentSearchApplication()
{
	cout << "\nThis is an search application to find student that are registered in certain classes." << endl;
	cout << "There are two student lists, one for CS408, and one for CS301." << endl;

	int* list408 = new int[20];
	int* list301 = new int[20];
	int size408 = 0;
	int size301 = 0;
	InitializeList(list408, size408, list301, size301);

	map<int, string> cs408;
	map<int, string> cs301;

	InitializeMap(cs408, cs301);
	int version = GetVersion();

	bool isADT;
	if (version == 1)
		isADT = true;
	else
		isADT = false;

	StudentSearch(isADT, list408, size408, list301, size301, cs408, cs301);
	
}

void TestSetImplementation()
{
	int version = GetVersion();

	if (version == 1)
		Version1();
	else
		Version2();
}

int GetSelect()
{

	cout << "\n\nPress 1 to Test the Application of Set implementation." << endl;
	cout << "Press 2 to test Set implementation itself." << endl;

	int choice;
	cin >> choice;

	switch (choice)
	{
	case 1:
		return 1;

	case 2:
		return 2;

	default:
		cout << "\nPlease enter either 1 or 2." << endl;

	}
	return choice;
}

// Prompt a user to enter values and store them
void promptMenu()
{
	int choice = GetSelect();

	if (choice == 1)
		StudentSearchApplication();
	else
		TestSetImplementation();

}

int main()
{
	promptMenu();
	return 0;
}

