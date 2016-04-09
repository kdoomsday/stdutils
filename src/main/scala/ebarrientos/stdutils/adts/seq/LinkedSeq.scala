package ebarrientos.stdutils.adts.seq

trait LL[A]
case class Node[A](head: A, tail: LL[A]) extends LL[A]
case class Empty[A]() extends LL[A]

object LL {
  implicit def LLTCSeq[A]: TCSeq[A, LL] = new TCSeq[A, LL] {
    def prepend(seq: LL[A], a: A): LL[A] = Node(a, seq)
  }

  def apply[A](): LL[A] = Empty()
}
