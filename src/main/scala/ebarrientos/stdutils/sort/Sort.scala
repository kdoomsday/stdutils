package ebarrientos.stdutils.sort

/**
 * Collection of sorting routines.
 */
object Sort {
  import scala.math.Ordering
  
  /**
   * Quicksort over elements of type T. The ordering to be used may be provided.
   */
  def quickSort[T](items: Seq[T])(implicit order: Ordering[T]): Seq[T] = {
    if (items.isEmpty)
      items
    else {
      val h = items.head
      val (less, more) = items.tail.partition(x => order.lt(x, h))
      quickSort(less) ++ (h +: quickSort(more))
    }
  }
}
