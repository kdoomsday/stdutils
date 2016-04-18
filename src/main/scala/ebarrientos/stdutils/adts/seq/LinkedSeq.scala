package ebarrientos.stdutils.adts.seq

import ebarrientos.stdutils.adts.{ Coll, FiniteColl, Eq, Functor, Foldable }

/** Linked list implementation */

sealed trait LL[A]
case class Node[A](head: A, tail: LL[A]) extends LL[A]
case class Empty[A]() extends LL[A]

object LL
  extends TCSeq.ToTCSeqOps
  with    Coll.ToCollOps
  with    FiniteColl.ToFiniteCollOps
  with    Eq.ToEqOps
  with    Functor.ToFunctorOps
  with    Foldable.ToFoldableOps
{
  /** TCSeq operations */
  implicit def tcSeqLL: TCSeq[LL] = new TCSeq[LL]() {
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


    def size[A](seq: LL[A]): Int = {
      @scala.annotation.tailrec
      def s(ss: LL[A], acc: Int): Int = ss match {
        case Empty() => acc
        case Node(_, ts) => s(ts, acc+1)
      }

      s(seq, 0)
    }
  }


  /** Eq operations */
  implicit def eqLL[A : Eq]: Eq[LL[A]] = new Eq[LL[A]] {
    def same(a1: LL[A], a2: LL[A]): Boolean = (a1, a2) match {
      case (Empty(), Empty()) => true
      case (Node(h1, t1), Node(h2, t2)) => h1 == h2 && same(t1, t2)
      case _ => false
    }
  }


  /** Functor ops */
  implicit def functorLL: Functor[LL] = new Functor[LL] {
    def map[A, B](l: LL[A], f: (A => B)): LL[B] = l match {
      case Node(a, t) => Node(f(a), map(t, f))
      case Empty()    => Empty()
    }
  }

  /** Foldable ops */
  implicit def foldableLL: Foldable[LL] = new Foldable[LL] {
    override def foldLeft[A, B](r: LL[A], init: B)(f: (B, A) => B): B = r match {
      case Empty()    => init
      case Node(h, t) => foldLeft(t, f(init, h))(f)
    }
  }


  def apply[A](): LL[A] = Empty()
  def apply[A](a: A): LL[A] = Node(a, Empty())
}
