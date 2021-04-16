package exercises

import lectures.functional.programming.TuplesAndMaps.{newPhoneBook, phoneBook}
import lectures.part2oop.App2

import scala.annotation.tailrec

object TuplesAndMaps extends App2{
  /*
  1. What would happen if i had two original entries "Jim" -> 555 and "JIM" ->900?
    !!!careful with map keys
  2. Overly simplified social network based on maps
    Person = String
      - add a person to the network
      - remove
      - friend (mutual)
      - unfriend

      - number of friends of a person
      - person with most friends
      - how many people have NO FRIENDS
      - if there is a social connection between two people (direct or not)
 */
  val phoneBook = Map(("Jim", 555), ("Werison", 444), ("JIM", 900)).withDefaultValue(-1)
  //a -> b is a syntax sugar for (a, b)
  println(phoneBook)
  //Functionals in map
  //map, flatMap and filter
  println(phoneBook.map(pair => pair._1.toLowerCase() -> pair._2))

  //METHODS
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }
  def friends(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriends(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAccumulator: Map[String, Set[String]]): Map[String, Set[String]] = {
      if(friends.isEmpty) networkAccumulator
      else removeAux(friends.tail, unfriends(networkAccumulator, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }
  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if(!network.contains(person)) 0
    network(person).size
  }
  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }
  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    //network.filterKeys(k => network(k).isEmpty).size
    //network.count(pair => pair._2.isEmpty)
    network.count(_._2.isEmpty)
  }
  def socialConnection(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople+person, discoveredPeople.tail++network(person))
      }
    }
    bfs(personB, Set(), network(personA) + personA)
  }
  //TEST
  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friends(network, "Bob", "Mary"))
  println(unfriends(friends(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friends(network, "Bob", "Mary"), "Bob"))

  //Bob, Mary and Jim
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friends(people, "Jim", "Bob")
  val maryBob = friends(people, "Mary", "Bob")
  val mayJim = friends(people, "Mary", "Jim")
  val testNet = friends(jimBob, "Bob", "Mary")
  println(testNet)
  println(nFriends(testNet, "Bob"))
  println(mostFriends(testNet))
  println(nPeopleWithNoFriends(testNet))
  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
