package ebarrientos.stdutils.adts.seq

/** Linked list implementation */

sealed trait LL[A]
case class Node[A](head: A, tail: LL[A]) extends LL[A]
case class Empty[A]() extends LL[A]

object LL {
  import ebarrientos.stdutils.adts.Coll
  import TCSeq.TCSeqOps

  implicit def LLColl[A]: Coll[A, LL] = new Coll[A, LL] {
    def empty(seq: LL[A]): Boolean = seq match {
      case Empty() => true
      case Node(_, _) => false
    }

    def contains(seq: LL[A], a: A): Boolean = seq match {
      case Empty() => false
      case Node(h, t) => h == a || contains(t, a)
    }
  }

  implicit def LLTCSeq[A]: TCSeq[A, LL] = new TCSeq[A, LL]() {
    def prepend(seq: LL[A], a: A): LL[A] = Node(a, seq)

    def append(seq: LL[A], a: A) = seq match {
      case e @ Empty() => Node(a, e)
      case Node(h, t) => Node(h, append(t, a))
    }
  }


  def apply[A](): LL[A] = Empty()
  def apply[A](a: A): LL[A] = Node(a, Empty())
}
