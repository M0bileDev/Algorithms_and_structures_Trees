typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf()

    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach { it.forEachDepthFirst(visit) }
    }

    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)

        //root node
        val queue = ArrayListQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }

        //children nodes
        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

    fun search(value: T, traversalAlgorithm: TraversalAlgorithm): TreeNode<T>? {
        var result: TreeNode<T>? = null

        when (traversalAlgorithm) {
            TraversalAlgorithm.DEPTH_FIRST -> {
                forEachDepthFirst {
                    if (it.value == value) {
                        result = it
                        return@forEachDepthFirst
                    }
                }
            }

            TraversalAlgorithm.LEVEL_ORDER -> {
                forEachLevelOrder {
                    if (it.value == value) {
                        result = it
                        return@forEachLevelOrder
                    }
                }
            }
        }

        return result
    }

    /**
     * Challenge: Print the values in a tree in an order based on their level.
     *
     *                          root
     *                 /          |          \
     *              group1      group2      group3
     *      /      /       \      |         /    \
     *  value1  value2  value3  value4  value5  value6
     *
     *
     */
    fun printEachLevel() {
        val queue = ArrayListQueue<TreeNode<T>>()
        var orderLevel = 0
        var levelNodes = 0

        if(this.children.isEmpty()) return print("Level 0: ${this.value}")

        print("Level 0: ${this.value}")
        this.children.forEach { queue.enqueue(it) }.apply {
            levelNodes = queue.count
        }

        while(!queue.isEmpty){
            orderLevel++
            print("\nLevel $orderLevel:")

            while (levelNodes > 0){
                levelNodes--

                val node = queue.dequeue()
                if(node == null) return
                print(" ${node.value},".removeSuffix(","))


                node.children.forEach { queue.enqueue(it) }
            }

            levelNodes = queue.count
        }
    }
}

enum class TraversalAlgorithm {
    DEPTH_FIRST,
    LEVEL_ORDER
}