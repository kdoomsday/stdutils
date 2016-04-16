package ebarrientos.stdutils.adts

import simulacrum._

import scala.language.{ higherKinds, implicitConversions }

/** Functor: Things you can map over */
@typeclass trait Functor[Repr[_]] {
  def map[A, B](r: Repr[A], f: A => B): Repr[B]
}
