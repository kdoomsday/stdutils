package ebarrientos.stdutils.adts

import simulacrum._

import scala.language.higherKinds
import scala.language.implicitConversions

/** Common typeclass for all collections */
@typeclass trait Coll[Repr[_]] {
  def contains[A](r: Repr[A], a: A): Boolean
  def empty[A](r: Repr[A]): Boolean
}
