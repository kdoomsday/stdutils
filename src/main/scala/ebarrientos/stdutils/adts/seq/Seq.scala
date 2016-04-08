package ebarrientos.stdutils.adts.seq

import scala.language.higherKinds

trait SeqLike[A, M[_]] {
  def head(s: M[A]): A
  def tail(s: M[A]): M[A]

  def prepend(s: M[A], a: A): M[A]
  def append(s: M[A], a: A): M[A]
}

object SeqLike {
  implicit class SeqLikeOps[A, M[_]](sm: M[A])
    (implicit s: SeqLike[A, M])
  {
    def head = s.head(sm)
    def tail = s.tail(sm)

    def prepend(a: A) = s.prepend(sm, a)
    def append(a: A) = s.prepend(sm, a)
  }

  def apply[A]() = EmptySeq
}
