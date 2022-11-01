import example_prof.BTrees.Tree.{Branch, Leaf, count, find, size}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.must.Matchers as MustMatchers


class DemoScalaTest extends AnyFunSpec {

  describe("A tree with 3 elements") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(1))

    it ("should have 3 elements") {
      assert(size(tree) == 3)
    }

    it ("should cointain 2 but not 3") {
      assert(find(tree, 2) == true)
      assert(find(tree, 3) == false)
    }

    it ("should have 1 element of 2 and 2 elements of 1") {
      assertResult(2) {count(tree, 1)}
      assertResult(1) {count(tree, 2)}
      assertResult(0) {count(tree, 3)}
    }
  }
}
