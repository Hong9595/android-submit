package com.example.arteum.ui.main.exhibition

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.arteum.R
import com.example.arteum.entity.searchresult.ExhibitionData
import com.example.arteum.ui.detailExhibition.DetailExhibitionActivity
import com.example.arteum.util.ExtensionsViewHolder
import kotlinx.android.synthetic.main.item_exhibition_recycler.*


class ExhibitionAdapter : RecyclerView.Adapter<ExhibitionViewHolder>() {

    var items = listOf<ExhibitionData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitionViewHolder {
        val exhibitionHolder = ExhibitionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_exhibition_recycler,parent,false))
        exhibitionHolder.exhibitionImage.setOnClickListener {
            it.context.run {
                val intent = Intent(this, DetailExhibitionActivity::class.java).apply {
                    putExtra(DetailExhibitionActivity.EXHIBITION_ID,items[exhibitionHolder.adapterPosition].id)
                }
                startActivity(intent)
            }
        }
        return exhibitionHolder
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExhibitionViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ExhibitionViewHolder(val view: View) : ExtensionsViewHolder(view) {

    fun bind(data: ExhibitionData) {
        with(itemView){
            Glide.with(this)
                .load(data.mainImage)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
                .into(exhibitionImage)

            exhibitionTitle.text = data.title
            exhibitionSubTitle.text = data.title
        }
    }
}