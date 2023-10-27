#include "MyTerm.h"
using namespace std;

MyTerm::MyTerm(int coeff, unsigned exp): coeff(coeff), exp(exp) {}
MyTerm::MyTerm(const MyTerm &term): coeff(term.coeff), exp(term.exp) {}

bool operator==(const MyTerm &term1, const MyTerm &term2) {
    return (term1.coeff == term2.coeff && term1.exp == term2.exp) ? true : false;
}
bool operator!=(const MyTerm &term1, const MyTerm &term2) {
    return !(term1 == term2);
}

bool operator<(const MyTerm &term1, const MyTerm &term2) {
    return (term1.exp > term2.exp) ? true : false;
}
bool operator>=(const MyTerm &term1, const MyTerm &term2) {
    return !(term1 < term2);
}
bool operator>(const MyTerm &term1, const MyTerm &term2) {
    return (term1.exp < term2.exp) ? true : false;
}
bool operator<=(const MyTerm &term1, const MyTerm &term2) {
    return !(term1 > term2);
}

MyTerm MyTerm::operator-() const {
    MyTerm term(-coeff, exp);
    return term;
}

MyTerm MyTerm::ddx() const {
    MyTerm dx(coeff*exp, exp-1);
    return dx;
}

ostream &operator<<(ostream &outstream, const MyTerm &term) {
    if (term.exp == 0) {
        if (term.coeff == 0) {
            return outstream;
        }
        else {
            return outstream << term.coeff;
        }
    }

    if (term.exp == 1) {
        return outstream << term.coeff << "x";
    }

    return outstream << term.coeff << "x^" << term.exp;
}