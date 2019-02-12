class NewSongs(val newSong: List<NewSong>){
}

data class NewSong(val songName: String, val artistName: String , val pictureURL: String, val releaseDate: String)