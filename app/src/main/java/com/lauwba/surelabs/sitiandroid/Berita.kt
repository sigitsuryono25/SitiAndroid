package com.lauwba.surelabs.sitiandroid

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class Berita : AppCompatActivity(){
    private var list:MutableList<BeritaModel>?=null
    private  var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list= mutableListOf()
        get_data_berita().execute()
    }
    inner class get_data_berita : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Berita, "","Wait", true, true)

        }

        override fun doInBackground(vararg params: String?): String {
            val handler= RequestHandler()
            val result=handler.sendGetRequest(ConfigApp.URL_BERITA_)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
                if(objek.getInt("status")==1){
                    Toast.makeText(this@Berita, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else{
                    val array = objek.getJSONArray("data")
                    for (i in 0 until array.length()) {

                        val data = array.getJSONObject(i)
                        val model = BeritaModel()
                        model.id_berita = data.getString("id_berita")
                        model.judul = data.getString("judul")
                        model.kategori = data.getString("kategori")
                        model.isi = data.getString("isi")
                        model.gambar = data.getString("gambar")
                        model.penulis = data.getString("penulis")
                        model.tanggal = data.getString("tanggal")
                        list?.add(model)
                        val adapter = list?.let { BeritaAdapter(it) }
                        rc.layoutManager = LinearLayoutManager(this@Berita)
                        rc.adapter = adapter
                    }
                }
        }

    }

}

