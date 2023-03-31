package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.model.Player
import java.sql.SQLException

interface PlayerDAO {
    fun addPlayer(player: Player)
    fun getPlayers(): ArrayList<Player>
    fun updatePlayer(player: Player)
    fun deletePlayer(player: Player)
}


class PlayerDAOSQLLiteImplementation(var context: Context): PlayerDAO{
    override fun addPlayer(player: Player) {
        val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.TABLE_PLAYER_NAME, player.username)

        var status = db.insert(DatabaseHandler.TABLE_PLAYER,
            null,
            contentValues)
        db.close()
    }

    override fun getPlayers(): ArrayList<Player> {
        val databaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var result = ArrayList<Player>()
        var cursor: Cursor? = null

        val columns =  arrayOf(DatabaseHandler.TABLE_PLAYER_ID,
            DatabaseHandler.TABLE_PLAYER_NAME)

        try {
            cursor = db.query(DatabaseHandler.TABLE_PLAYER,
                columns,
                null,
                null,
                null,
                null,
                null)

        }catch (sqlException: SQLException){
            db.close()
            return result
        }

        var player: Player
        if(cursor.moveToFirst()){
            do{
                player = Player("")
                player.username = cursor.getString(1)
                player.id = cursor.getInt(0).toString()
                result.add(player)
            }while(cursor.moveToNext())
        }
        return result
    }

    override fun updatePlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun deletePlayer(player: Player) {
        TODO("Not yet implemented")
    }

}
