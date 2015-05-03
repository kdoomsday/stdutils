import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object TreesetTests extends Properties("Treeset") {
  import ebarrientos.stdutils.adts.set.Treeset
  
  
  property("empty treeset contains nothing") = {
    val set = Treeset[Int]()
    forAll { i: Int =>
      !set.contains(i)
    }
  }
  
  property("Adding an element to a tree means it contains it") = forAll { i: Int =>
    (Treeset() add i contains i) &&
      (Treeset(i) contains i)
  }
  
  property("Contains what it should") = forAll { (i: Int, j: Int, k: Int) =>
    val tree = Treeset() add i add j add k
    tree.contains(i) && tree.contains(j) && tree.contains(k) &&
      ((i + 1 == j) || (i+1 == k) || !tree.contains(i+1))
  }
}