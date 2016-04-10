package ebarrientos.stdutils.adts

import scala.language.higherKinds

/** Common typeclass for all collections */
trait Coll[A, Repr[_]] {
  def contains(r: Repr[A], a: A): Boolean
  def empty(r: Repr[A]): Boolean
}

object Coll {
  implicit class CollOps[A, Repr[_]](r: Repr[A])(implicit ev: Coll[A, Repr]) {
    def contains(a: A): Boolean = ev.contains(r, a)
    def empty: Boolean = ev.empty(r)
  }
}
