package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: String) = a + " " + b
  println(aFunction("x", "y"))

  def bFunction(a: String, b: String) = a + " " + b
  println(bFunction("z", "w"))

  def aParameterLessFunction(): Int = 45
  println(aParameterLessFunction())
  println(aParameterLessFunction)

  //When you need loops, use recursion
  def aRepeatFunction(aString: String, n: Int): String= {
    if(n == 1) { aString }
    else aString + aRepeatFunction(aString, n-1)
  }
  println(aRepeatFunction("hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit= println("OK!!!")

  def aBigFunction(n: Int) = {
    def aSmallerFunction(a: Int, b: Int) = a + b
    aSmallerFunction(n, n-1)
  }
  println(aBigFunction(10))

  /*
    1. A greeting function(name, age) => "Hi my name is $name and i am $age years old."
    2. Factorial function 1 * 2 * 3 ...* n
    3. Fibonacci function
      f(1)= 1
      f(2)= 1
      f(n)= f(n-1) + f(n-2)
    4. Tests if a number is prime
  */
def aGreetingFunciton(name: String, age: Int): Unit = println("Hi my name is " + name + " and i am "  + age + " years old.")
  aGreetingFunciton("Werison", 37)

  def aFatorial(n: Int): Int = {
    if(n == 0) 1
    else n * aFatorial(n-1)
  }
  println(aFatorial(5))

  def aFibonacci(n: Int): Int = {
    if(n < 1) 0
    else if(n == 1) 1
    else aFibonacci(n-1) + aFibonacci(n-2)
  }
  println(aFibonacci(8))

  def aIsPrime(n: Int): Boolean = {
    def aIsPrimeUntil(t: Int): Boolean = {
      if(t <= 1) true
      else n % t != 0 && aIsPrimeUntil(t-1)
    }
    aIsPrimeUntil(n/2)
  }
  //Pass 5 => 5/2= 2 => 2 <= 1 = (false) => 5 % 2 != 0 (1 true) && (2-1)
  //Continue true && 1 <= 1 (true) => (true)
  println(aIsPrime(5))

  //Pass 5 => 6/2= 3 => 3 <= 1 = (false) => 6 % 3 != 0 = (0 false) && (3-1)
  //Continue false && 2 <= 1 = (false) => 6 % 2 != 0 = (0 false) && (2-1)
  //Continue false && 1 <= 1 = (true) => (false)
  println(aIsPrime(6))
}
