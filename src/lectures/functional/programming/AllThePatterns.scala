package lectures.functional.programming

import exercises.CaseClasses.{MyList, Cons, Empty}
import lectures.part2oop.App2

object AllThePatterns extends App2 {
  //Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => s"A number"
    case "Scala" => s"The Scala"
    case true => s"The truth"
    case 1 => s"A number"
    case AllThePatterns => s"A singleton object"
  }
  println(constants)
  //2 - Matching anything
  //2.1 Wildcard
  val matchAnything = x match {
    case _ =>
  }
  //2.2. A variable
  val matchVariable = x match {
    case something => s"i've found $something"
  }
  //3. Typles
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 1) => s"I've found $something"
    case _=>
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  //PMs can be nested!

  //Case class - Constructor pattern
  //PMs can be nested with case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subHead, subTail)) =>
  }

  //5 - List patterns
  val aStandartList = List(1, 2, 3, 42)
  val standardListMatching = aStandartList match {
    case List(1, _, _, _) => //extractor - advanced
    case List(1, _*) => //list of arbitrary length - advanced
    case 1::List(_) => //Infix pattern
    case List(1,2,3) :+ 42 => //infix pattern
  }
  //Type specifiers
  val unknown: Any = 2
  val unknownMatch =  unknown match {
    case list: List[Int] => //explicit type specifier
    case _=>
  }
  //Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => //name binding - use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => //name binding inside nested patterns
  }
  //Multi-Patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => //Compound pattern (multi-pattern)
    case _ =>
  }
  //If - Guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => //
    case _=>
  }

  //ALL.
  /*
    Question?
   */
  val numbers = List(1, 2, 3, 4)
  val numbersMatch = numbers match {
    case listOfNumber: List[Int] => s"A list of numbers"
    case listOfStrings: List[String] => s"A list of strings"
    case _=>
  }
  println(numbersMatch)
  //JVM trick
}
