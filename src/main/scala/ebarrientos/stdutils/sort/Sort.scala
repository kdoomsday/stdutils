package ebarrientos.stdutils.sort

/**
 * Collection of sorting routines.
 */
object Sort {
  import scala.math.Ordering
  import scala.annotation.tailrec
  
  /** Quicksort with provided ordering. */
  def quickSort[T](items: Seq[T])(implicit order: Ordering[T]): Seq[T] = {
    if (items.isEmpty)
      items
    else {
      val h = items.head
      val (less, more) = items.tail.partition(x => order.lt(x, h))
      quickSort(less) ++ (h +: quickSort(more))
    }
  }
  
  
  /** Mergesort with provided ordering. */
  def mergeSort[T](items: Seq[T])(implicit order: Ordering[T]): Seq[T] = {
    @tailrec
    def join(a: Seq[T], b: Seq[T], accum: Seq[T]): Seq[T] = {
      if (a.isEmpty)
        accum ++ b
      else if (b.isEmpty)
        accum ++ a
      else {
        if (order.lt(a.head, b.head))
          join(a.tail, b, accum :+ a.head)
        else
          join(a, b.tail, accum :+ b.head)
      }
    }
    
    if (items.isEmpty)
      items
    else {
      val size = items.size
      if (size == 1)
        items
      else {
        val (first, second) = items.splitAt(items.size / 2)
        val of = mergeSort(first)
        val os = mergeSort(second)
      
        join(of, os, Seq())
      }
    }
  }
}
