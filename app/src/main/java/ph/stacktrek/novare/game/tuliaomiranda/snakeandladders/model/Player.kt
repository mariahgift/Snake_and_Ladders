package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.model

class Player(var username: String) {
    lateinit var id: String

}

enum class Color {
    BLACK,
    BLUE,
    RED,
    GREEN,
    NOT_SET
}