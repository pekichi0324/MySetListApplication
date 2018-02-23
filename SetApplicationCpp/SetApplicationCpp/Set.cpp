#include "Set.h"
#include <iostream>
using namespace std;

Set::Set()
{
	intList = new int[20];
	numOfElement = 0;
}
Set::Set(int size)
{
	intList = new int[size];
	numOfElement = 0;
}

int* Set::GetList()const
{
	return intList;
}
int Set::GetIntAt(int num)const
{
	return intList[num];

}
void Set::AppendValue(int value)
{
	*(intList + (numOfElement++)) = value;

}

// It appends non-duplicate elements in the argument Set into the calling Set
void Set::AppendNonDuplicate(const Set set)
{
	for (int i = 0; i < set.numOfElement; i++)
	{
		int j = 0;
		bool found = false;
		while (j < numOfElement && !found)
		{
			if (*(set.intList + i) == *(intList +j))
				found = true;
			j++;
		}
		if (!found)
		{
			*(intList + numOfElement++) = *( set.intList + i);
		}
	}
}
int Set::GetNumOfElement()const
{
	return numOfElement;
}
void Set::SetNumOfElement(int n)
{
	numOfElement = n;
}

// It creates a clone of the calling list and return it as a result list
Set Set::Clone() const
{
	// Need a double size for result since it might get as big as twice the max size of a single set
	Set result = Set(40);
	for (int i = 0; i < numOfElement; i++)
	{
		*(result.intList + i) = *(intList + i);
		result.numOfElement++;
	}
	return result;
}

// A function to print each element in intList
void Set::Print()
{
	if (numOfElement > 0)
	{
		cout << "[" << *(intList);
		for (int i = 1; i < numOfElement; i++)
		{
			cout << ", " << *(intList + i);
		}
		cout << "]" << endl;
	}
	else 
		cout << "[]" << endl;
}


// A funtion to delete any duplicates in a list
void Set::ClearDuplicate()
{
	for (int i = 0; i < numOfElement; i++)
	{
		bool found = false;
		int j = i + 1;
		while (j < numOfElement && !found)
		{
			if (*(intList + i) == *(intList + j))
			{
				found = true;

				// move the last element to the j position
				*(intList + j) = *(intList + (numOfElement)-1);
				*(intList + numOfElement) = 0;
				numOfElement--;
			}
			j++;
		}
	}
}

// Union  operator overloading 
Set Set::operator+(Set otherSet) const
{
	// Clone calling object set to result set
	Set result = Clone();
	result.AppendNonDuplicate(otherSet);
		
	return result;
}

Set Set::operator*(Set otherSet) const
{
	Set result = Set();
	for (int i = 0; i < numOfElement; i++)
	{
		int j = 0;
		bool found = false;
		while (j < otherSet.numOfElement && !found)
		{
			if (*(intList + i) == *( otherSet.intList + j))
			{
				*(result.intList + result.numOfElement++) = *(otherSet.intList + j);
				found = true;
			}
			j++;

		}
	}
	return result;
}

// Intersection operator overloading
Set Set::operator-(Set otherSet) const
{
	Set resultSet = *this + otherSet;
	Set intersectionSet = *this * otherSet;
	

	for (int i = 0; i < intersectionSet.numOfElement; i++)
	{
		int j = 0;
		bool found = false;
		while (j < resultSet.numOfElement && !found)
		{
			// If a duplicate is found, delete it from the result list
			if (*(resultSet.intList + j) == *(intersectionSet.intList + i))
			{
				// Copy the last element in result to the current index (Deleting the element at the current index)
				
				*(resultSet.intList + j) = *(resultSet.intList + (--resultSet.numOfElement));
				*(resultSet.intList + resultSet.numOfElement) = 0;
				found = true;
			}
			j++;
		}
	}
	return resultSet;
}


