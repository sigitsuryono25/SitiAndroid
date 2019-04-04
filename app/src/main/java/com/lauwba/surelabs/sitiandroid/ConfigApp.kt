package com.lauwba.surelabs.sitiandroid
object ConfigApp{
    private val HOST = "http://api.surelabs.xyz/"
    val URL_BERITA_ = HOST + "index.php/Berita/select/"
    val URL_BERITA_DETAIL = HOST + "index.php/Berita/select_by_get/"
    val URL_GAMBAR = HOST + "assets/gambar/"
    val URL_CARI_BERITA = HOST + "index.php/json/cari_berita?s="
    val DATA_ARRAY = "data"
    val ID_BERITA = "id_berita"
}