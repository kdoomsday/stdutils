package ebarrientos.stdutils.adts.seq

import simulacrum._

import ebarrientos.stdutils.adts.FiniteColl

import scala.language.higherKinds
import scala.language.implicitConversions

/** Sequence of elements. Elements are ordered. */

@typeclass trait TCSeq[Repr[_]] extends FiniteColl[Repr]{
  def prepend[A](seq: Repr[A], a: A): Repr[A]
  def append[A](seq: Repr[A], a: A): Repr[A]
}
