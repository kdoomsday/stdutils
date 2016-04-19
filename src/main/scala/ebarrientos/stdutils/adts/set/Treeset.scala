package ebarrientos.stdutils.adts.set

trait Treeset[A] extends Set[A] {
  def ord: Ordering[A]
  override def add(a: A): Treeset[A]
}


object Treeset {
  def apply[A]()(implicit ord: Ordering[A]): Treeset[A] =
    Leaf(ord)

  def apply[A](elem: A)(implicit ord: Ordering[A]): Treeset[A] = {
    val leaf = Leaf(ord)
    Node(elem, leaf, leaf, ord)
  }
    
  private[set] def apply[A](elem: A, left: Treeset[A], right: Treeset[A])(implicit ord: Ordering[A]) =
    Node(elem, left, right, ord)
}


/** Empty set. */
case class Leaf[A] private[set](ord: Ordering[A]) extends Treeset[A] {
  def contains(a: A) = false
  def empty: Boolean = true
  
  def add(a: A): Treeset[A] = Node(a, this, this, ord)
}

/** Set with at least one element. */
case class Node[A] private[set](
  elem: A,
  left: Treeset[A],
  right: Treeset[A],
  ord: Ordering[A]) extends Treeset[A]
{
  
  def contains(a: A): Boolean =
    if (elem == a) true
    else if (ord.lt(a, elem)) left.contains(a)
    else right.contains(a)

  def empty: Boolean = false

  def add(a: A): Treeset[A] =
    if (elem == a) this
    else if (ord.lt(a, elem)) new Node(elem, left add a, right, ord)
    else new Node(elem, left, right add a, ord)
}

