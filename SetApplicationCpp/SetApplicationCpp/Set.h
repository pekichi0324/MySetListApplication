#pragma once
#include <iostream>
#include <string>

class Set
{
private: int* intList;
private: int numOfElement;
	
public:
	Set();
	Set(int);
	int* GetList()const;
	int GetIntAt(int)const;
	int GetNumOfElement()const;
	void AppendValue(int value);
	void AppendNonDuplicate(const Set);
	void ClearDuplicate();
	void SetNumOfElement(int);
	Set Clone()const;
	void Print();
	Set operator+(Set)const;
	Set operator*(Set)const;
	Set operator-(Set) const;
};

