import io.javalin.Context
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import org.jsoup.Jsoup

fun main(args: Array<String>) {
    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(4000)


    app.routes {

        get("/new_music") { ctx ->
            ctx.json(getSongList())
        }
    }
}
// You can wrap the main function
// in a Kotlin object


fun getSongList() : NewSongs {
    val doc = Jsoup.connect("https://www.last.fm/music/+releases/coming-soon/popular").get()
    val newsHeadlines = doc.select("li.album-grid-item-wrap")
    val newSongList = mutableListOf<NewSong>()

    newsHeadlines.forEach { headlines ->
        val songName: String = headlines.getElementsByClass("album-grid-item-title").text()
        val artistName: String = headlines.getElementsByClass("album-grid-item-artist").text()
        val releaseDate = headlines.getElementsByClass("album-grid-item-aux-text album-grid-item-date").text()
        val albumArtUrl: String = headlines.getElementsByClass("cover-art").attr("src")

        val newSong = NewSong(songName, artistName, albumArtUrl, releaseDate)
        newSongList.add(newSong)

    }
    return NewSongs(newSongList)
}



