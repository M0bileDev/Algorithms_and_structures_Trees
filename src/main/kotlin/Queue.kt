interface Queue<T> {

    fun enqueue(element: T): Boolean

    fun dequeue(): T?

    val count: Int

    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?
}

class ArrayListQueue<T> : Queue<T> {

    val list = mutableListOf<T>()

    override fun enqueue(element: T) =
        list.add(element)


    override fun dequeue() = if (isEmpty) null else list.removeAt(0)


    override val count: Int
        get() = list.size

    override fun peek() = list.firstOrNull()

}