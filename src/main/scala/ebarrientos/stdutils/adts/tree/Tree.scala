package ebarrientos.stdutils.adts.tree

/** Tree trait for binary trees. */
trait Tree[+T] {
  def elem: T
  def left: Tree[T]
  def right: Tree[T]
  
  def map[B](f: T => B): Tree[B]
}

object Tree {
  def apply[T] = Leaf
  def apply[T](elem: T) = new Node(elem, Leaf, Leaf)
  def apply[T](elem: T, left: Tree[T], right: Tree[T]) = new Node(elem, left, right)
}


/** Leaf of a tree. Same object for every tree. */
object Leaf extends Tree[Nothing] {
  override def elem  = throw new NoSuchElementException("Element of a Leaf")
  override def left  = throw new NoSuchElementException("Left tree of a Leaf")
  override def right = throw new NoSuchElementException("Right tree of a Leaf")
  
  override def map[B](f: Nothing => B): Tree[B] = this
}

/** Node of a tree. Has an element and a left and right tree (which might be leaves). */
class Node[+T](
  override val elem: T,
  override val left: Tree[T],
  override val right: Tree[T]
) extends Tree[T] {
  
  override def map[B](f: T => B): Tree[B] =
    Tree(f(elem), left.map(f), right.map(f))
}