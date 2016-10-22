package ebarrientos.stdutils.adts.set

trait Set[A] {
  def contains(a: A): Boolean
  def add(a: A): Set[A]
}