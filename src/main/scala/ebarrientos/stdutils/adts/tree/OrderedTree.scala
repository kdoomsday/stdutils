package ebarrientos.stdutils.adts.tree

import scala.math.Ordering

/** OrderedTree trait. */
trait OrderedTree[T] {
  def elem: T
  def left: OrderedTree[T]
  def right: OrderedTree[T]
  
  val order: Ordering[T]
  
  def add(item: T): OrderedTree[T]
  def contains(item: T): Boolean
}

object OrderedTree {
  def apply[T](implicit ord: Ordering[T]): OrderedTree[T] = OrderedLeaf()(ord)
  def apply[T](item: T)(implicit ord: Ordering[T]): OrderedTree[T] =
    OrderedNode(item, OrderedLeaf(), OrderedLeaf())
}


case class OrderedLeaf[T](override implicit val order: Ordering[T]) extends OrderedTree[T] {
  override def elem  = throw new NoSuchElementException("Element of a Leaf")
  override def left  = throw new NoSuchElementException("Left tree of a Leaf")
  override def right = throw new NoSuchElementException("Right tree of a Leaf")
  
  override def add(item: T): OrderedTree[T] = OrderedNode(item, this, this)(order)
  override def contains(item: T) = false
}


case class OrderedNode[T](
  override val elem: T,
  override val left: OrderedTree[T],
  override val right: OrderedTree[T]
) (override implicit val order: Ordering[T]) extends OrderedTree[T] {
  
  override def add(item: T): OrderedTree[T] =
    if (order.lt(item, elem)) OrderedNode(elem, left add item, right)(order)
    else OrderedNode(elem, left, right add item)(order)
      
  override def contains(item: T): Boolean =
    if (elem == item) true
    else if (order.lt(item, elem)) left.contains(item)
    else right.contains(item)
}