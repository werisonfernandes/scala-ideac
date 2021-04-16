package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    //Use the type A
    def add[B >: A](element: B): MyList[B] = ???
  }

  class MyMap[key, value] {}

  //Generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]
  val emptyListOfIntegers = MyList.empty[Int]

  //Variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. Yes, List[Cat] extends List[Animal] //COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  val animalList2: CovariantList[Animal] = new CovariantList[Animal]
  //val animalList3: CovariantList[Cat] = new CovariantList[Animal]
  //animalList.add(new Dog) ??? HARD QUESTION. (Return a list of Animal)

  //2. NO INVARIANCE
  class InvariantList[A]
  //val invariantList: InvariantList[Animal] = new InvariantList[Cat] //no compile
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]
  //val invariantList2: InvariantList[Animal] = new InvariantList[Cat]
  //val invariantList3: InvariantList[Cat] = new InvariantList[Animal]

  //3. HELL, NO!!! CONTRAVARIANCE
  class Contravariant[-A]
  val contravariantList: Contravariant[Cat] = new Contravariant[Animal]
  val contravariantList2: Contravariant[Animal] = new Contravariant[Animal]
  //val contravariantList3: Contravariant[Animal] = new Contravariant[Cat]

  //3. HELL, NO!!! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //Bounded types: Subtypes of
  class Cage[A <: Animal](animal: A)
  val cage: Cage[Dog] = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car)

  //Exercises. Expand MyList to be generic.
}
