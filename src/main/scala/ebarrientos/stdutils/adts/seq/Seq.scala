package ebarrientos.stdutils.adts.seq

import scala.language.higherKinds

trait TCSeq[A, Repr[_]] {
  def prepend(seq: Repr[A], a: A): Repr[A]
}

object TCSeq {
  implicit class TCSeqOps[A, Repr[_]](seq: Repr[A])(implicit ev: TCSeq[A, Repr]) {
    def prepend(a: A): Repr[A] = ev.prepend(seq, a)
  }
}
