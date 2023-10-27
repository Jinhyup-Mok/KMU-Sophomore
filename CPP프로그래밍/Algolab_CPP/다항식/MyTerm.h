#ifndef MYTERM_H
#define MYTERM_H

#include <iostream>
using namespace std;

class MyPolynomial;

class MyTerm {
friend class MyPolynomial;
public:
    int coeff;       // integer coefficient
    unsigned exp;    // exponent (non-negative integer)

    MyTerm(int coeff=0, unsigned exp=0);
    MyTerm(const MyTerm &);

    friend bool operator==(const MyTerm &, const MyTerm &);
    friend bool operator<(const MyTerm &, const MyTerm &);
    friend bool operator>(const MyTerm &, const MyTerm &);
    
    MyTerm operator-() const;

    MyTerm ddx() const;

    friend ostream &operator<<(ostream &, const MyTerm &);

    // Friend function of MyPolynomial using MyTerm
    friend bool operator==(const MyPolynomial &, const MyPolynomial &);
    
    friend ostream &operator<<(ostream &, const MyPolynomial &);
};
bool operator!=(const MyTerm &, const MyTerm &);
bool operator>=(const MyTerm &, const MyTerm &);
bool operator<=(const MyTerm &, const MyTerm &);

#endif