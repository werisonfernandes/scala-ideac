package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int= {
    if(n < 0) {
      println("Block n < 0")
      -1
    } else if(n == 0) {
      println("Block n ==1")
      1
    } else {
      println("Computed factorial of=>" + n + "- First i need factorial of=>" + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of=>" + n)
      result
    }
  }
  println((factorial(5)))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if(x <= 1) accumulator
      else factorialHelper(x-1, accumulator * x) //TAIL RECURSION = Use recursive call as the LAST expression
    }
    factorialHelper(n, 1)
  }
  /*anotherFactorial(10) = factorialHelper(10, 1)
    factorialHelper(9, 10*1)
    factorialHelper(8, 10*1*9)
    factorialHelper(7, 10*1*9*8)
    factorialHelper(6, 10*1*9*8*7)
    factorialHelper(5, 10*1*9*8*7*6)
    factorialHelper(4, 10*1*9*8*7*6*5)
    factorialHelper(3, 10*1*9*8*7*6*5*4)
    factorialHelper(2, 10*1*9*8*7*6*5*4*3)
    factorialHelper(1, 10*1*9*8*7*6*5*4*3*2)
    =10*1*9*8*7*6*5*4*3*2
   */
  println((anotherFactorial(10)))
  println((anotherFactorial(5000)))

  //When you need LOOPS use _TAIL_RECURSION

  /*
    1. Concatenates a string n times using tail recursive
    2. IsPrime function tail recursive
    3. Fibonacci function tail recursive
   */
  def concatenates(aString: String, n: Int): String = {
    @tailrec
    def concat(x: String, y: Int, accumulator: String): String = {
      if(y <= 0) accumulator
      else concat(x, (y-1), (x + " " + accumulator))
    }
    concat(aString, n, "")
  }
  println(concatenates("Werison", 3))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if(!isStillPrime) false
      else if(t <= 1) true
      else isPrimeTailRec((t-1), isStillPrime && (n % t != 0))
    }
    isPrimeTailRec(n/2, true)
  }
  println(isPrime(4))
  println(isPrime(2003))
  println(isPrime(629))

  def fibonnaci(n: Int): Int = {
    @tailrec
    def fibonnaciTailRec(x: Int, last: Int, nextToLast: Int): Int = {
      if(x <= 2) last
      else fibonnaciTailRec(x-1, last + nextToLast, last)
    }
    fibonnaciTailRec(n, 1, 1)
  }
  println(fibonnaci(9)) // 1, 1, 2, 3, 5, 8, 13, 21, 34
  println(fibonnaci(8)) // 1, 1, 2, 3, 5, 8, 13, 21
  println(fibonnaci(7)) // 1, 1, 2, 3, 5, 8, 13
  println(fibonnaci(5)) // 1, 1, 2, 3, 5
}
