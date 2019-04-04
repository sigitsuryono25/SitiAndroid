package com.lauwba.surelabs.sitiandroid

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_itemberita.view.*

class BeritaAdapter (
private val mValue: List<BeritaModel>
): RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {
    private var context : Context?=null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.fragment_itemberita, p0, false)
    context=p0.context
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
       val item= mValue[p1]
        p0.judul.text=item.judul
        p0.tanggal.text=item.tanggal
        p0.btnread.setOnClickListener {
            val intent =  Intent(context, DetailBerita::class.java)
            intent.putExtra(ConfigApp.ID_BERITA,item.id_berita)
            intent.putExtra("from","berita")
            context?.startActivity(intent)
        }
        context?.let { Glide.with(it).load(ConfigApp.URL_GAMBAR + item.gambar).into(p0.image)}
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.image
        val judul: TextView = itemView.judul
        val tanggal: TextView = itemView.tanggal
        val btnread: Button = itemView.btnread

    }
}



