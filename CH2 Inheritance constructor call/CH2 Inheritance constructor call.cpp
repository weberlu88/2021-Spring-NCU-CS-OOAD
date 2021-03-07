// CH2 Inheritance constructor call.cpp : 此檔案包含 'main' 函式。程式會於該處開始執行及結束執行。
//

#include <iostream>
using namespace std;

class Pet
{
protected:
    int weight;
    string food;
public:
    // Constructors, Destructors
    Pet() : weight(1), food("Pet Chow")
    {
        cout << "Pet Constructor" << endl;
    }
    ~Pet()
    {
        cout << "Pet Destructor" << endl;
    }
    // Rest of code unmodified from first example
    //Accessors
    void setWeight(int w) { weight = w; }
    int getWeight() { return weight; }
    void setfood(string f) { food = f; }
    string getFood() { return food; }
    //General methods
    void eat();
    void speak();
};
void Pet::eat() { cout << "Eating " << food << endl; }
void Pet::speak() { cout << "Growl" << endl; }

class Rat : public Pet
{
public:
    Rat()
    {
        cout << "Rat Constructor" << endl;
    }
    ~Rat()
    {
        cout << "Rat Destructor" << endl;
    }
    //Other methods
    void sicken() { cout << "Spreading Plague" << endl; }
};

class Cat : public Pet
{
private:
    int numberToes;
public:
    Cat() : numberToes(5)
    {
        cout << "Cat Constructor" << endl;
    }
    ~Cat()
    {
        cout << "Cat Destructor" << endl;
    }
    //Other accessors
    void setNumberToes(int toes) { numberToes = toes; }
    int getNumberToes() { return numberToes; }
};

int main()
{
    Rat charles;
    Cat fluffy;
    //charles.setWeight(25);
    //cout << "Charles weighs " << charles.getWeight() << " lbs. " << endl;
    //charles.speak();
    //charles.eat();
    //charles.sicken();
    //fluffy.speak();
    //fluffy.eat();
    //cout << "Fluffy has " << fluffy.getNumberToes() << " toes " << endl;
    return 0;
}

// outputs:
/*
Pet Constructor
Rat Constructor
Pet Constructor
Cat Constructor
Cat Destructor
Pet Destructor
Rat Destructor
Pet Destructor
*/

// 執行程式: Ctrl + F5 或 [偵錯] > [啟動但不偵錯] 功能表
// 偵錯程式: F5 或 [偵錯] > [啟動偵錯] 功能表

// 開始使用的提示: 
//   1. 使用 [方案總管] 視窗，新增/管理檔案
//   2. 使用 [Team Explorer] 視窗，連線到原始檔控制
//   3. 使用 [輸出] 視窗，參閱組建輸出與其他訊息
//   4. 使用 [錯誤清單] 視窗，檢視錯誤
//   5. 前往 [專案] > [新增項目]，建立新的程式碼檔案，或是前往 [專案] > [新增現有項目]，將現有程式碼檔案新增至專案
//   6. 之後要再次開啟此專案時，請前往 [檔案] > [開啟] > [專案]，然後選取 .sln 檔案
