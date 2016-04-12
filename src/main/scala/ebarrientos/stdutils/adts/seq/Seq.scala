package ebarrientos.stdutils.adts.seq

import simulacrum._

import ebarrientos.stdutils.adts.Coll

import scala.language.higherKinds
import scala.language.implicitConversions

/** Sequence of elements. Elements are ordered. */

@typeclass trait TCSeq[Repr[_]] extends Coll[Repr]{
  @op("|+|") def prepend[A](seq: Repr[A], a: A): Repr[A]
}

/*
object TCSeqSyntax {
  implicit class TCSeqOps[A, Repr[_]](seq: Repr[A])(implicit ev: TCSeq[Repr]) {
    def prepend(a: A): Repr[A] = ev.prepend(seq, a)
  }
}
*/
