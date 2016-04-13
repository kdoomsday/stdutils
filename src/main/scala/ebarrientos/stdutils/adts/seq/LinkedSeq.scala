package ebarrientos.stdutils.adts.seq

import ebarrientos.stdutils.adts.{ Coll, FiniteColl }

/** Linked list implementation */

sealed trait LL[A]
case class Node[A](head: A, tail: LL[A]) extends LL[A]
case class Empty[A]() extends LL[A]

object LL
  extends TCSeq.ToTCSeqOps
  with    Coll.ToCollOps
  with    FiniteColl.ToFiniteCollOps
{

  implicit def LLTCSeq: TCSeq[LL] = new TCSeq[LL]() {
    def prepend[A](seq: LL[A], a: A): LL[A] = Node(a, seq)

    def append[A](seq: LL[A], a: A): LL[A] = seq match {
      case Empty() => LL(a)
      case Node(h, t) => Node(h, append(t, a))
    }

    def contains[A](seq: LL[A], a: A): Boolean = seq match {
      case Node(h, t) => if (h == a) true
                         else contains(t, a)
      case Empty() => false
    }

    def empty[A](seq: LL[A]): Boolean = seq match {
      case Node(_, _) => false
      case Empty() => true
    }

    /** TODO Tail recursive version */
    def size[A](seq: LL[A]): Int = seq match {
      case Empty()    => 0
      case Node(h, t) => 1 + size(t)
    }
  }


  def apply[A](): LL[A] = Empty()
  def apply[A](a: A): LL[A] = Node(a, Empty())
}
