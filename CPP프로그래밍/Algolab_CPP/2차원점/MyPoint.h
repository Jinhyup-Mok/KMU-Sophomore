#ifndef _MY_POINT_H_
#define _MY_POINT_H_
class MyPoint
{
public:
    // constructors
    MyPoint ();
    MyPoint (int coordX, int coordY);
    MyPoint (const MyPoint& p);
    // accessor functions
    int getX() const;
    int getY() const;
    // mutator function
    void setX(int x);
    void setY(int y);
    // comparison operators
    bool operator== (const MyPoint& p) const;
    bool operator!= (const MyPoint& p) const;
    // utility functions
    int dist2sqr(const MyPoint& p) const; // 직선거리(Euclidean distance)의 제곱을 계산
    int dist1(const MyPoint& p) const;    // 직각거리(rectilinear distance)를 계산
private:
    int x, y;
};
#endif // _MY_POINT_H_ 