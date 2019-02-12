class NewSongs(val newSong: List<NewSong>){
}

data class NewSong(val artistName: String, val songName: String , val pictureURL: String, val releaseDate: String)