package ebarrientos.stdutils.adts.set

import ebarrientos.stdutils.adts.Coll

trait Set[A] {
  def contains(a: A): Boolean
  def empty: Boolean
  def add(a: A): Set[A]
}

object Set {
  implicit def setColl: Coll[Set] = new Coll[Set] {
    override def contains[A](s: Set[A], a: A): Boolean = s.contains(a)

    override def empty[A](s: Set[A]): Boolean = s.empty
  }
}