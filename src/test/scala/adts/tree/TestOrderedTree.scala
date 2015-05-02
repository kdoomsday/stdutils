import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import ebarrientos.stdutils.adts.tree.OrderedTree

object OrderedTreeTests extends Properties("OrderedTree") {
  
  property("contains finds correct elements") = {
    val tree = OrderedTree(15)
    
    (tree contains 15) && !tree.contains(0)
  }
  
  property("tree of an element contains said element") = forAll { i: Int =>
    OrderedTree(i) contains i
  }
  
  property("added element is contained") = forAll { (i: Int, j: Int) =>
    val tree = OrderedTree(i) add j
    (tree contains i) && (tree contains j)
  }
}