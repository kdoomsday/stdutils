package ebarrientos.stdutils.adts.seq

sealed trait LinkedSeq[+A]
object EmptySeq extends LinkedSeq[Nothing]
case class NodeSeq[A](a: A, s: LinkedSeq[A]) extends LinkedSeq[A]


object LinkedSeq {
  implicit def linkedSeqLike[A](): SeqLike[A, LinkedSeq] = new SeqLike[A, LinkedSeq] {
    def head(s: LinkedSeq[A]): A = s match {
      case EmptySeq => throw new Exception("Head of empty seq")
      case NodeSeq(a, _)  => a
    }

    def tail(s: LinkedSeq[A]): LinkedSeq[A] = s match {
      case EmptySeq      => EmptySeq
      case NodeSeq(_, t) => t
    }

    def prepend(s: LinkedSeq[A], a: A): LinkedSeq[A] = NodeSeq(a, s)

    def append(s: LinkedSeq[A], a: A): LinkedSeq[A] = ???
  }
}
