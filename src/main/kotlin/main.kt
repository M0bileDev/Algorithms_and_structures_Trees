fun main() {

    val green = TreeNode("Green")
    val red = TreeNode("Red")

    val apples = TreeNode("Apples").run {
        add(green)
        add(red)
    }
}