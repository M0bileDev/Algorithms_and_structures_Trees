fun main() {

    makeAppleTree()
    val beverageTree = makeBeverageTree()

    println("\n=forEachDepthFirst=")
    beverageTree.forEachDepthFirst {
        println(it.value)
    }

    println("\n=forEachLevelOrder=")
    beverageTree.forEachLevelOrder {
        println(it.value)
    }

    println("\n=search=")
    beverageTree.search("ginger ale", traversalAlgorithm = TraversalAlgorithm.LEVEL_ORDER)?.let {
        println("Found node: $it -> ${it.value}")
    } ?: println("Node was not found")

    beverageTree.search("soda", TraversalAlgorithm.DEPTH_FIRST)?.let {
        println("Found node: $it -> ${it.value}")
    } ?: println("Node was not found")
}

fun makeAppleTree() {
    val green = TreeNode("Green")
    val red = TreeNode("Red")

    val apples = TreeNode("Apples").run {
        add(green)
        add(red)
    }
}

fun makeBeverageTree(): TreeNode<String> {
    val tree = TreeNode("Beverages")

    val hot = TreeNode("hot")
    val cold = TreeNode("cold")

    val tea = TreeNode("tea")
    val coffee = TreeNode("coffee")
    val chocolate = TreeNode("cocoa")

    val blackTea = TreeNode("black")
    val greenTea = TreeNode("green")
    val chaiTea = TreeNode("chai")

    val soda = TreeNode("soda")
    val milk = TreeNode("milk")

    val gingerAle = TreeNode("ginger ale")
    val bitterLemon = TreeNode("bitter lemon")

    tree.add(hot)
    tree.add(cold)

    hot.add(tea)
    hot.add(coffee)
    hot.add(chocolate)

    cold.add(soda)
    cold.add(milk)

    tea.add(blackTea)
    tea.add(greenTea)
    tea.add(chaiTea)

    soda.add(gingerAle)
    soda.add(bitterLemon)

    return tree
}