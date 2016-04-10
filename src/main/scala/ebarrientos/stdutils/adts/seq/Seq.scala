package ebarrientos.stdutils.adts.seq

import ebarrientos.stdutils.adts.Coll

import scala.language.higherKinds

/** Sequence of elements. Elements are ordered. */

trait TCSeq[A, Repr[_]] {
  def prepend(seq: Repr[A], a: A): Repr[A]
  def append(seq: Repr[A], a: A): Repr[A]
}

object TCSeq {
  implicit class TCSeqOps[A, Repr[_]](seq: Repr[A])(implicit ev: TCSeq[A, Repr]) {
    def prepend(a: A): Repr[A] = ev.prepend(seq, a)
    def append(a: A): Repr[A] = ev.append(seq, a)
  }
}
