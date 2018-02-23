/***************************************************************
* file: Set.h
* author: Jinjing Lee
* class: CS 408 Programming Languages
*
* Header file for class Set that is used for Student search application that finds students 
* in either or both in CS435  and CS301 
*
* assignment: project1
* date last modified: 02/18/2018
*
****************************************************************/

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

