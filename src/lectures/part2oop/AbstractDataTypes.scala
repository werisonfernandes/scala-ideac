package lectures.part2oop

object AbstractDataTypes extends App2 {

  //Abstract
  abstract class Animal {
    val creatureType: String

    def sleep: Unit
    def eat: Unit
    def run: Unit = println("Running!!!")
  }

  class Dog extends Animal {
    override def sleep: Unit = ???
    def eat: Unit = println("Crunch crunch")
    override val creatureType: String= "Canine"
  }

  //Traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "reptile"

    override def sleep: Unit = ???

    override def eat: Unit = ???

    override def eat(animal: Animal): Unit = println(s"I'm a crocodile and i'm eating ${animal.creatureType}")
  }

  println("Works!!!")

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  //Traits vs abstract classes
  //1. Traits do not have constructors
  //2. Multiple traits can be inherited by the same class
  //3. Traits = behavior, classes = "thing"
}
