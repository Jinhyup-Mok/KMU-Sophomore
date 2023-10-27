#include "MyRational.h"
using namespace std;
myRational::myRational(long num, long den):_num(num), _den(den){
    reduce();
}
myRational::myRational(const myRational &r) {
    _num = r._num;
    _den = r._den;
}
myRational myRational::reciprocal() const{
    return myRational(_den, _num);
}

ostream &operator <<(ostream &outStream, const myRational& r)
{
    if (r._num == 0)
        outStream << 0;
    else if (r._den == 1)
        outStream << r._num;
    else
        outStream << r._num << "/" << r._den;
    return outStream;
}
istream &operator >>(istream &inStream, myRational& r)
{
    inStream >> r._num >> r._den;
    if (r._den == 0)
    {
    r._num = 0; 
    r._den = 1;
    }
    r.reduce();
    return inStream;
}
long myRational::gcd(long m, long n)
{
    if (m == n) return n;
    else if (m < n) return gcd(m, n-m);
    else return gcd(m-n, n);
}
void myRational::reduce()
{
    if (_num == 0 || _den == 0)
    {
    _num = 0;
    _den = 1;
    return;
    }
    if (_den < 0)
    {
    _den *= -1;
    _num *= -1;
    }
    if (_num == 1)
        return;
    int sgn = (_num < 0 ? -1 : 1);
    long g = gcd(sgn * _num, _den);
    _num /= g;
    _den /= g;
} 
long myRational::getNumerator() const {
    return _num;
}
long myRational::getDenominator() const {
    return _den;
}


myRational myRational::operator +(const myRational& number) const {
    long new_den =  _den * number._den;
    long new_num = _den * number._num + number._den * _num;
    return myRational (new_num, new_den);
}

myRational myRational::operator +(long value) const {
    return (*this) + myRational(value, 1);
}

myRational myRational::operator -(const myRational& number) const {
    long new_den = _den *  number._den;
    long new_num = (number._den * _num) - (_den * number._num);
    return myRational(new_num, new_den);
}

myRational myRational::operator -(long value) const {
    return (*this) - myRational(value, 1);
}

myRational myRational::operator *(const myRational& number) const {
    long new_den = _den * number._den;
    long new_num = _num * number._num;
    return myRational(new_num, new_den);
}
myRational myRational::operator* (long value) const{
    return *this * myRational(value, 1);
}

myRational myRational::operator /(const myRational& number) const {
    if (number._num == 0) return myRational();
    myRational r = number.reciprocal();
    long new_den = _den * r._den;
    long new_num = _num * r._num;
    return myRational(new_num, new_den);
}
myRational myRational::operator /(long value) const{
    return *this / myRational(value, 1);
}
// Assignment operators
myRational& myRational::operator =(const myRational& number) {
    _num = number._num;
    _den = number._den;
    return *this;
}

myRational& myRational::operator =(long value) {
    _num = value;
    _den = 1;
    return *this;
}

myRational& myRational::operator +=(const myRational& number) {
    long new_num = _num;
    long new_den = _den;
    _num = (new_num * number._den) + (number._num * _den);
    _den = new_den * number._den;
    reduce();
    return *this;
}
myRational& myRational::operator +=(long value){
    *this += myRational(value, 1);
    return *this;
}

myRational& myRational::operator -=(const myRational& number) {
    long new_num = _num;
    long new_den = _den;
    _num = (new_num * number._den) - (number._num * _den);
    _den = new_den * number._den;
    reduce();
    return *this;
}
myRational& myRational::operator -=(long value){
    *this -= myRational(value, 1);
    return *this;
}

myRational& myRational::operator *=(const myRational& number) {
    long new_num = _num;
    long new_den = _den;
    _num = new_num * number._num;
    _den = new_den * number._den;
    reduce();
    return *this;
}
myRational& myRational::operator *=(long value){
    *this *= myRational(value, 1);
    return *this;
}
myRational& myRational::operator /=(const myRational& number) {
    if (number._num == 0){
        _num = 0;
        _den = 1;
        return *this;
    }
    long new_num = _num;
    long new_den = _den;
    myRational r(number.reciprocal());
    _num = new_num * r._num;
    _den = new_den * r._den;
    reduce();
    return *this;
}
myRational& myRational::operator /=(long value){
    *this /= myRational(value, 1);
    return *this;
}


// Overloading comparison operators
bool myRational::operator ==(const myRational& number) const {
    return (_num == number._num) && (_den == number._den);
}

bool myRational::operator !=(const myRational& number) const {
    return !operator==(number);
}

bool myRational::operator >(const myRational& number) const {

    return  number._den * _num > _den * number._num;
}

bool myRational::operator >=(const myRational& number) const {
    return number._den * _num >= _den * number._num;
}

bool myRational::operator <(const myRational& number) const {
    return number._den * _num < _den * number._num;
}

bool myRational::operator <=(const myRational& number) const {
    return number._den * _num <= _den * number._num;
}

// Overloaded unary operators
// unary minus
myRational myRational::operator -() {
    return myRational(-_num, _den);
}


myRational myRational::operator ++() {
    _num += _den;
    return *this;
}
myRational myRational::operator ++(int) {
    myRational ret(_num, _den);
    _num += _den;
    return ret;
}

myRational myRational::operator --() {
    _num -= _den;
    return *this;
}

myRational myRational::operator --(int) {
	myRational ret(_num, _den);
    _num -= _den;
    return ret;
}

// private function
myRational operator +(long value, const myRational & number) {
	return number + value;
}

myRational operator-(long value, const myRational & number) {
	myRational r(number);
    return -r + value;
}

myRational operator*(long value, const myRational & number) {
	return number * value;
}
myRational operator/(long value, const myRational & number) {
    myRational r(number.reciprocal());
    return r * value;
}

