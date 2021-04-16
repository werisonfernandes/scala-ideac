package exercises

import lectures.part2oop.App2

abstract class Maybe[+T] {
  def map[B](f: T => B): Maybe[B]
  def flatMap[B](f: T => Maybe[B]): Maybe[B]
  def filter(predicate: T => Boolean): Maybe[T]
}

object MaybeNot extends Maybe[Nothing]{
  override def map[B](f: Nothing => B): Maybe[B] = MaybeNot

  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot

  override def filter(predicate: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

case class Just[+T](value: T) extends Maybe[T]{
  override def map[B](f: T => B): Maybe[B] = Just(f(value))

  override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)

  override def filter(predicate: T => Boolean): Maybe[T] = {
    if(predicate(value)) this
    else MaybeNot
  }
}

object MaybeTest extends App2 {
  val just3 = Just(3)
  println(just3)
  println(just3.map(x => x + 8))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(x => x > 1))
}
