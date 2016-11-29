// Test.cpp : définit le point d'entrée pour l'application console.
//
#include "stdafx.h"
#include <iostream>
using namespace std;

void swapC(int *a, int *b) {
	int c = *a;
	*a = *b;
	*b = c;
}

void swapCPP(int &a, int &b) {
	int c = a;
	a = b;
	b = c;
}

int main()
{
	int i = 8, j = 6, &k = j;
	cout << j << " " << k++ << endl;
	cout << j << " " << k << endl;
	swapC(&i, &j);
	cout << j << " " << k << endl;
	swapCPP(i, j);
	cout << j << " " << k << endl;
	int a;
	cin >> a;
}

