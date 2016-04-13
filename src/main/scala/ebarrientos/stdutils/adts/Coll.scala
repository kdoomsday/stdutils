package ebarrientos.stdutils.adts

import simulacrum._

import scala.language.higherKinds
import scala.language.implicitConversions

/** Common typeclass for all collections */
@typeclass trait Coll[Repr[_]] {
  def contains[A](r: Repr[A], a: A): Boolean
  def empty[A](r: Repr[A]): Boolean
}


/** Finite collections. Supports a size method.
  * TODO So far it simply returns an Int. Maybe I should encode it to return
  * something else?
  */
@typeclass trait FiniteColl[Repr[_]] extends Coll[Repr]{
  def size[A](r: Repr[A]): Int
  @inline final def length[A](r: Repr[A]) = size(r)
}
