import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import ebarrientos.stdutils.sort.Sort.quickSort

/** Tests for all sort algorithms */
object SortTests extends Properties("Sort") {
  def isOrdered[T](s: Seq[T])(implicit o: scala.math.Ordering[T]) =
    (0 until s.size-1).forall(i => o.lteq(s(i), s(i+1)))
  
  property("quickSort.orders") = forAll { l: List[Int] =>
    isOrdered(quickSort(l))
  }
}
