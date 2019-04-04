package com.lauwba.surelabs.sitiandroid

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_berita.*
import org.json.JSONObject

class DetailBerita : AppCompatActivity() {
    private var id: String? = null
    private var pd: ProgressDialog? = null
    private var from: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)
        id = intent.getStringExtra(ConfigApp.ID_BERITA)
        from = intent.getStringExtra("from")
        Toast.makeText(this@DetailBerita, from, Toast.LENGTH_SHORT).show()
        getdetailberita().execute()

    }

    inner class  getdetailberita : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val request=RequestHandler()
            return request.sendGetRequest(ConfigApp.URL_BERITA_DETAIL+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailBerita,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                judul.text=data.getString("judul")
                isi.text=data.getString("isi")
                tanggal.text=data.getString("tanggal")
                Glide.with(this@DetailBerita)
                    .load(ConfigApp.URL_GAMBAR+data.getString("gambar")).into(gambarberita)
            }
        }
    }

    inner class getdetail :AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val request=RequestHandler()
            return request.sendGetRequest(ConfigApp.URL_BERITA_DETAIL+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailBerita,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailBerita, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    judul.text = data.getString("judul")
                    isi.text = data.getString("isi")
                    tanggal.text = data.getString("tanggal")
                    Glide.with(this@DetailBerita)
                        .load(ConfigApp.URL_GAMBAR + data.getString("gambar")).into(gambarberita)
                }
            }
        }

    }


}



