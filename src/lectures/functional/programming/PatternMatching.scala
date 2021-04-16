package lectures.functional.programming

import lectures.part2oop.App2

import scala.util.Random

object PatternMatching extends App2 {
  //Switch on steroids
  val random =  new Random()
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "The ONE"
    case 2 => "Double or Nothing"
    case 3 => "Third time is the charm"
    case _ => "Something else" //_ Wildcard
  }
  println(x)
  println(description)
  //1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greenting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and i can't drink in Brazil"
    case Person(n, a) => s"Hi, my name is $n and i am $a years old."
    case _ => "I don't know who i am"
  }
  println(greenting)
  /*
    1. Cases are matched in order
    2. What if no cases match? MatchError
    3. Type of pattern Matching expression? Unified type of all the type in all the cases
    4. Pattern matching works really with case classes
   */

  //Pattern matching in hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed.")
  }

  //Matching everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  //WHY:?
  val isEvenCond = if(x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0
  /*
  Exercises
  Simple function uses PM
  Takes an Expr => human readable form

  Sum(Number(2), Number(3)) => 2 +3
  Sum(Sum(Number(2), Number(3)),Number(4)) => 2 + 3 + 4
  Prod(Sum(Number(2), Number(1)), Number(3)) => (2 + 1) * 3
  Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 2
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  //Exercises
  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2)  => {
      def maybeShowParenthesis(exp: Expr)= exp match {
        case Prod(_,_) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
    }
  }
  //
  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)),Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))

  println(show(Prod(Sum(Number(2), Number(1)), Prod(Number(3), Number(4)))))
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))

  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
}
