#include "MyComplex.h"
// Constructor
myComplex::myComplex(int real, int imag) {
    realPart = real;
    imaginaryPart = imag;
}

// Copy constructor
myComplex::myComplex(const myComplex& number) {
    realPart = number.realPart;
    imaginaryPart = number.imaginaryPart;
}

// Accessor functions
int myComplex::getRealPart() const {
    return realPart;
}

int myComplex::getImaginaryPart() const {
    return imaginaryPart;
}

// Mutator functions
void myComplex::setRealPart(int real) {
    realPart = real;
}

void myComplex::setImaginaryPart(int imag) {
    imaginaryPart = imag;
}

void myComplex::set(int real, int imag) {
    realPart = real;
    imaginaryPart = imag;
}

// Overloaded binary operators
myComplex myComplex::operator +(const myComplex& number) const {
    int newReal = realPart + number.realPart;
    int newImag = imaginaryPart + number.imaginaryPart;
    return myComplex (newReal, newImag);
}

myComplex myComplex::operator +(int value) const {
    return myComplex(value) + (*this);
}

myComplex myComplex::operator -(const myComplex& number) const {
    return myComplex(realPart - number.realPart, imaginaryPart - number.imaginaryPart);
}

myComplex myComplex::operator -(int value) const {
    return -myComplex(value) + (*this);
}

myComplex myComplex::operator *(const myComplex& number) const {
    return myComplex(realPart * number.realPart - imaginaryPart * number.imaginaryPart, realPart * number.imaginaryPart + imaginaryPart * number.realPart);
}

// Assignment operators
myComplex& myComplex::operator =(const myComplex& number) {
    this->realPart = number.realPart;
    this->imaginaryPart = number.imaginaryPart;
    return *this;
}

myComplex& myComplex::operator =(int value) {
    realPart = value;
    imaginaryPart = 0;
    return *this;
}

myComplex& myComplex::operator +=(const myComplex& number) {
    realPart += number.realPart;
    imaginaryPart += number.imaginaryPart;
    return *this;
}

myComplex& myComplex::operator -=(const myComplex& number) {
    realPart -= number.realPart;
    imaginaryPart -= number.imaginaryPart;
    return *this;
}

myComplex& myComplex::operator *=(const myComplex& number) {
    int tmp_r = realPart;
    realPart = realPart * number.realPart - imaginaryPart * number.imaginaryPart;
    imaginaryPart = tmp_r * number.imaginaryPart + imaginaryPart * number.realPart;
    return *this;
}

// Overloading comparison operators
bool myComplex::operator ==(const myComplex& number) const {
    return (realPart == number.realPart) && (imaginaryPart == number.imaginaryPart);
}

bool myComplex::operator !=(const myComplex& number) const {
    return (realPart != number.realPart) || (imaginaryPart != number.imaginaryPart);
}

bool myComplex::operator >(const myComplex& number) const {
    return norm() > number.norm();
}

bool myComplex::operator >=(const myComplex& number) const {
    return norm() >= number.norm();
}

bool myComplex::operator <(const myComplex& number) const {
    return norm() < number.norm();
}

bool myComplex::operator <=(const myComplex& number) const {
    return norm() <= number.norm();
}

// Overloaded unary operators
// unary minus
myComplex myComplex::operator -() {
    return myComplex(-realPart, -imaginaryPart);
}

myComplex myComplex::operator ~() {
    return myComplex(realPart, -imaginaryPart);
}

myComplex myComplex::operator ++() {
    realPart++;
    return *this;
}

myComplex myComplex::operator ++(int) {
    myComplex ret(realPart, imaginaryPart);
	++(*this);
	return ret;
}

myComplex myComplex::operator --() {
    realPart--;
    return *this;
}

myComplex myComplex::operator --(int) {
    myComplex ret(realPart, imaginaryPart);
	--(*this);
	return ret;
}

// private function
int myComplex::norm() const {
    return realPart * realPart + imaginaryPart * imaginaryPart;
}

ostream &operator <<(ostream &outStream, const myComplex& number) {
    outStream << "(" << number.realPart << "," << number.imaginaryPart << ")";
    return outStream;
}

istream &operator >>(istream &inStream, myComplex& number) {
    inStream >> number.realPart >> number.imaginaryPart;
    return inStream;
}

myComplex operator+(int value, const myComplex & number) {
	return number + value;
}

myComplex operator-(int value, const myComplex & number) {
	return -(number - value);
}

myComplex operator*(int value, const myComplex & number) {
	return number * value;
}