package com.example.arteum.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.arteum.R
import com.example.arteum.ui.searchDetail.SearchDetailActivity
import com.example.arteum.util.ExtensionsViewHolder
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.android.synthetic.main.content_recycler_search.*
import kotlinx.android.synthetic.main.header_recycler_search.*
import timber.log.Timber

class SearchAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<SearchAdapterItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // viewType : Header, Exhibitions, AlbumVoice, VoiceActors(else)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            HEADER -> {
                val headerViewHolder = SearchHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_recycler_search,parent,false))
                headerViewHolder.let { holder -> // 헤더 뷰홀더
                    holder.searchHeaderMore.setOnClickListener { view -> // 더보기 textView
                        Intent(view.context, SearchDetailActivity::class.java).let { intent ->
                            intent.putExtra(SearchDetailActivity.HEADER_TITLE, holder.searchHeaderTitle.text.toString()) // header title 전달
                            intent.putExtra(SearchDetailActivity.SEARCH_KEYWORD, SearchActivity.searchKeyword)
                            view.context.startActivity(intent)
                        }
                    }
                    holder
                }
            }
            EXHIBITIONS, ALBUM_VOICE -> {
                SearchExhibitVoiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_search,parent,false))
            }
            else -> {
                SearchVoiceActorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_search,parent,false))
            }
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            HEADER -> { (holder as SearchHeaderViewHolder).bindHeader(items[position]) }
            EXHIBITIONS, ALBUM_VOICE -> { (holder as SearchExhibitVoiceViewHolder).bindExhibitVoiceData(items[position]) }
            else -> { (holder as SearchVoiceActorViewHolder).bindVoiceActorData(items[position]) }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int  = items[position].viewType!!

    companion object {
        const val HEADER = 0
        const val EXHIBITIONS = 1
        const val ALBUM_VOICE = 2
        const val VOICE_ACTORS = 3
    }
}

class SearchHeaderViewHolder(val view: View): ExtensionsViewHolder(view) {
    fun bindHeader(headerData: SearchAdapterItem){
        with(this){
            searchHeaderTitle.text = headerData.header
        }
    }
}

class SearchExhibitVoiceViewHolder(view: View): ExtensionsViewHolder(view) {

    fun bindExhibitVoiceData(contentsData: SearchAdapterItem){
        // 서버에 데이터 없을수도 있으므로 lateinit하면 에러
        var imageUrl :String? = null
        var name :String? = null
        var place :String? = null

        when(contentsData.viewType) {
            SearchAdapter.EXHIBITIONS -> {
                contentsData.exhibitionData?.run {
                    imageUrl = mainImage
                    name = title
                    place = location
                }
            }
            SearchAdapter.ALBUM_VOICE -> {
                contentsData.albumVoiceData?.run {
                    imageUrl = mainImage
                    name = title
                    place = location
                }
            }
        }
        with(this) {
            Glide.with(this.itemView)
                .load(imageUrl)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(30)))
                .into(searchResultImage)
        }
        searchResultTitle.text = name
        searchResultLocation.text = place
    }
}

class SearchVoiceActorViewHolder(view: View): ExtensionsViewHolder(view) {

    fun bindVoiceActorData(contentsData: SearchAdapterItem) {
        with(this) {
            contentsData.voiceActorData?.run {
                Glide.with(this@SearchVoiceActorViewHolder.itemView)
                    .load(mainImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(searchResultImage)
                searchResultTitle.text = name
                searchResultLocation.text = "보이스 " + audioCount.toString()
            }
        }
    }
}

