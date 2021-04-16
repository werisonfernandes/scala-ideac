package exercises

import lectures.part2oop.App2

object Exceptions extends App2 {
  /*
     1. Crash your program with an OutOfMemoryError
     2. Crash with SOError
     3. PocketCalculator
       - add(x,y)
       - subtract(x,y)
       - multiply(x,y)
       - divide(x,y)

       Throw
       - OverflowException if add(x,y) exceeds Int.MAX_VALUE
       - UnderflowException if add(x,y) exceeds Int.MIN_VALUE
       - MathCalculationException for divide by 0
    */
  //OOM
  //Crash your program with an OutOfMemoryError
  //val array = Array.ofDim(Int.MaxValue)

  //SOE
  //Crash with SOError (StackOverflowError)
  //def infinite: Int = 1 + infinite
  //val noLimit = infinite
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else  if(x < 0 && y < 0 && result > 0) throw new UnderflowException
      result
    }

    def sub(x: Int, y: Int): Int = {
      val result = x - y
      if(x > 0 && y < 0 && result < 0) throw new OverflowException
      else if(x < 0 && y > 0 && result > 0) throw new OverflowException
      result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result < 0) throw new OverflowException
      else  if(x > 0 && y < 0 && result > 0) throw new UnderflowException
      else  if(x < 0 && y > 0 && result > 0) throw new UnderflowException
      result
    }

    def divide(x: Int, y: Int): Int ={
      if(y == 0) throw new MathCalculationException
      x/y
    }
  }
  //println(PocketCalculator.add(Int.MaxValue-1, 2))
  //println(PocketCalculator.add(Int.MinValue, -1))
  println(PocketCalculator.divide(1, 0))
}
