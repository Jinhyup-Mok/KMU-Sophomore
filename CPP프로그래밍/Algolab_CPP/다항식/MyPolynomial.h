#ifndef MYPOLYNOMIAL_H
#define MYPOLYNOMIAL_H

#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>
#include "MyTerm.h"
using namespace std;

class MyPolynomial {
private:
    vector<MyTerm> polynomial;
public:
    MyPolynomial(int coeff=0, unsigned exp=0);
    MyPolynomial(unsigned, int *);
    MyPolynomial(const MyPolynomial &);
    MyPolynomial &operator=(const MyPolynomial &);

    int getDegree() const;
    unsigned getNumTerms() const;
    MyPolynomial ddx() const;
    
    MyPolynomial &operator+=(const MyPolynomial &);
    MyPolynomial &operator-=(const MyPolynomial &);
    MyPolynomial &operator*=(const MyTerm &);
    MyPolynomial &operator*=(const MyPolynomial &);
    MyPolynomial &operator*=(int);

    long operator()(int x) const;

    MyPolynomial operator-() const;
    
    friend bool operator==(const MyPolynomial &, const MyPolynomial &);
    friend ostream &operator<<(ostream &, const MyPolynomial &);

    static const MyPolynomial ZERO;
    static const MyPolynomial ONE;
    static const MyPolynomial X;
};

MyPolynomial operator+(const MyPolynomial &, const MyPolynomial &);
MyPolynomial operator-(const MyPolynomial &, const MyPolynomial &);
MyPolynomial operator*(const MyPolynomial &, const MyTerm &);
MyPolynomial operator*(const MyTerm &, const MyPolynomial &);
MyPolynomial operator*(const MyPolynomial &, const MyPolynomial &);
MyPolynomial operator*(const MyPolynomial &, int);
MyPolynomial operator*(int, const MyPolynomial &);

bool operator!=(const MyPolynomial &, const MyPolynomial &);
#endif