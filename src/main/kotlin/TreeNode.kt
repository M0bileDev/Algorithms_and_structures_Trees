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
}

enum class TraversalAlgorithm {
    DEPTH_FIRST,
    LEVEL_ORDER
}