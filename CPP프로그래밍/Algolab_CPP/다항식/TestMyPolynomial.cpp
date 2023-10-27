#include "MyPolynomial.h"
#include <fstream>
#include <cstdlib>
using namespace std;

void testSimpleCase();
void testDataFromFile();

int main() {
    testSimpleCase();
    testDataFromFile();
    return 0;
}

void testSimpleCase() {
    // test static variables
    cout << MyPolynomial::ZERO << endl;
    cout << MyPolynomial::ONE << endl;
    cout << MyPolynomial::X << endl;
    MyPolynomial p0, p1(1), p2(1, 1), p3(3, 5);
    int testData4[10] = {1, 0, 1, 1, 1, 2, 1, 3, 1, 4};
    int testData5[10] = {-1, 0, -1, 1, -1, 2, -1, 3, -1, 4};
    int testData6[10] = {1, 0, -1, 1, 1, 2, -1, 3, 1, 4};
    int testData7[10] = {2, 2, 5, 5, 4, 4, 1, 1, 3, 3};
    int testData8[12] = {1, 1000000000, 1, 100000000, 1, 1000000, 1, 10000, 1, 100, 1, 0};
    MyPolynomial p4(5, testData4);
    MyPolynomial p5(5, testData5);
    MyPolynomial p6(5, testData6);
    MyPolynomial p7(5, testData7);
    MyPolynomial p8(6, testData8);
    MyPolynomial p9(p7);
    // test constructor
    cout << p0 << endl << p1 << endl << p2 << endl;
    cout << p4 << endl << p8 << endl;
    // test copy constructor
    cout << p9 << endl;
    // test accessor function
    cout << p8.getDegree() << " " << p8.getNumTerms() << endl;
    // test evaluation function
    cout << p1(2) << " " << p2(2) << " " << p3(2) << " " << p4(2) << " " << endl;
    cout << p5(3) << " " << p6(3) << " " << p7(3) << " " << p9(3) << " " << endl;
    // test comparison operators
    cout << (p7 == p9) << " " << (p7 != p9) << endl;
    // test derivative function
    cout << MyPolynomial::ZERO.ddx() << endl;
    cout << MyPolynomial::ONE.ddx() << endl;
    cout << MyPolynomial::X.ddx() << endl;
    cout << p4.ddx() << endl;
    cout << p8.ddx() << endl;
    // test unary operator -
    cout << -MyPolynomial::ZERO << endl;
    cout << -p4 << endl;
    // test k*p(x) or p(x) * k
    cout << 3*MyPolynomial::ZERO << endl;
    cout << 3*MyPolynomial::ONE << endl;
    cout << MyPolynomial::X*3 << endl;
    cout << 3*p4 << " " << p4*3 << endl;
    cout << 0*p5 << " " << p5*5 << endl;
    // test binary operator +
    cout << p4 + p5 << endl;
    // test binary operator -
    cout << p6 - p7 << endl;
    // test binary operator *
    cout << p8 * p9 << endl;
    MyPolynomial tmp1(p4), tmp2, tmp3, tmp4;
    tmp4 = tmp3 = tmp2 = tmp1;
    cout << (tmp1 += p5) << endl;
    cout << (tmp2 -= p5) << endl;
    cout << (tmp3 *= p5) << endl;
    cout << (tmp4 *= 3) << endl;
    // int t;
    // cin >> t;
}

void testDataFromFile() {
    int numTestCases;
    cin >> numTestCases;
    for (int i=0; i<numTestCases; i++) {
        int numTerms, terms[100];
        /* read first polynomial */
        cin >> numTerms;
        for (int j=0; j<numTerms; j++) {
            cin >> terms[2*j] >> terms[2*j+1];
        }
        MyPolynomial p1(numTerms, terms);
        /* read second polynomial */
        cin >> numTerms;
        for (int j=0; j<numTerms; j++) {
            cin >> terms[2*j] >> terms[2*j+1];
        }
        MyPolynomial p2(numTerms, terms);
        cout << p1 << endl << p2 << endl;
        cout << p1.getDegree() << " " << p2.getNumTerms() << endl;
        cout << p1.ddx() << endl << p2.ddx() << endl;
        cout << (p1 == p2) << " " << (p1 != p2) << endl;
        cout << p1(1) << endl;
        cout << -p1 + p1 * 2 * p2 - p2 * 2 + 3 * p1 << endl;
        MyPolynomial p3(MyPolynomial::ZERO), p4(MyPolynomial::ONE), p5(MyPolynomial::X);
        p3 += p1;
        p4 -= p2;    // -x^1000000000 + 1
        p5 *= p4;    // -x^1000000001 + x
        p5 *= 2;     // -2x^1000000001 + 2x
        cout << p5 << endl;
    }
}