#include <fstream>
#include <cstdlib>
#include "MyComplex.h"
void testSimpleCase();
void testDataFromFile();
int main(void)
{
    testSimpleCase();
    testDataFromFile();
    return 0;
}
void testSimpleCase()
{
    myComplex c0, c1(1), c2(2, 2);
    myComplex c3(c2);
    myComplex c4, c5, c6, c7, c8, c9;
    // test constructor
    cout << c0 << endl << c1 << endl << c2 << endl;
    // test copy constructor
    cout << c3 << endl;
    // test accessor function
    cout << c3 << endl;
    // test mutator function
    c3.set(3, 3);
    cout << c3 << endl;
    c3.setRealPart(4);
    cout << c3 << endl;
    c3.setImaginaryPart(4);
    cout << c3 << endl;
    // test binary operators
    c4 = c1 + c3;
    c5 = c1 - c3;
    c6 = c4 * c5;
    cout << c4 << endl << c5 << endl << c6 << endl;
    c7 = c6 + 2;
    c8 = c6 - 2;
    c9 = c6 * 2;
    cout << c7 << endl << c8 << endl << c9 << endl;
    c7 += c4;
    c8 -= c5;
    c9 *= c6;
    cout << c7 << endl << c8 << endl << c9 << endl;
    c7 += 2;
    c8 -= 2;
    c9 *= 2;
    cout << c7 << endl << c8 << endl << c9 << endl;
    // test comparison operators
    cout << (c8 != c9) << " " << (c8 == c9) << endl;
    cout << (c8 > c9) << " " << (c8 >= c9) << " " << (c8 < c9) << " " << (c8 <=
    c9) << endl;
    c7 = c8 = c9;
    cout << (c8 != c9) << " " << (c8 == c9) << endl;
    cout << (c8 > c9) << " " << (c8 >= c9) << " " << (c8 < c9) << " " << (c8 <=
    c9) << endl;
    // test prefix operators
    c7 = -myComplex(2, 3);
    c8 = (++c7) * 2;
    c9 = 2 * (c7++);
    cout << c7 << " " << c8 << " " << c9 << endl;
    c7 = ~myComplex(2, 3);
    c8 = (--c7) * 2;
    c9 = 2 * (c7--);
    cout << c7 << " " << c8 << " " << c9 << endl;
    // test expressions with myComplex numbers
    c1 = myComplex(1,2);
    c2 = myComplex(2,3);
    c3 = myComplex(4,5);
    c4 = myComplex(5,6);
    c5 = myComplex(6,7);
    c6 = 3;
    cout << -(c1*c2) - 2*c3 + ~c4 * c5 * 3 + 2 -c6 << endl;
}
void testDataFromFile()
{
    ifstream inStream;
    int numTestCases;
    inStream.open("input.txt");
    if (inStream.fail())
    {
    cerr << "Input file opening failed.\n";
    exit(1);
    }
    inStream >> numTestCases;
    for (int i=0; i<numTestCases; i++)
    {
    myComplex c1, c2, c3, c4;
    myComplex c5, c6, c7, c8, c9;
    inStream >> c1 >> c2 >> c3 >> c4;
    cout << c1 << " " << c2 << " " << c3 << " " << c4 << endl;
    cout << (2 + c1 + 3) + (2 - c2 - 3) * (2 * c3 * 3) - ( 2 * c4 - 3) <<
    endl;
    c5 = c6 = c7 = c8 = c1;
    cout << (c5 == c2) << " " << (c5 != c2) << endl;
    cout << (c5 > c2) << " " << (c5 >= c2) << " " << (c5 < c2) << " " <<
    (c5 <= c2) << endl;
    cout << (c5 == c6) << " " << (c5 != c6) << endl;
    cout << (c5 > c6) << " " << (c5 >= c6) << " " << (c5 < c6) << " " <<
    (c5 <= c6) << endl;
    c5 += c2;
    c6 -= c3;
    c7 *= c4;
    c8 = c2.getRealPart();
    c9 = c3.getImaginaryPart();
    cout << c5 << " " << c6 << " " << c7 << " " << c8 << " " << c9 << endl;
    c7 = -c6;
    c8 = (++c7) * 2;
    c9 = 2 * (c7++);
    cout << c7 << " " << c8 << " " << c9 << endl;
    c7 = ~c6;
    c8 = (++c7) * 2;
    c9 = 2 * (c7++);
    cout << c7 << " " << c8 << " " << c9 << endl;
    }
    inStream.close();
}