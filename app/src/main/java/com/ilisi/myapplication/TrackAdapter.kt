package com.ilisi.myapplication
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilisi.myapplication.models.MusicItem
import com.squareup.picasso.Picasso

class TrackAdapter(private val musics: List<MusicItem>) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackTitle: TextView = itemView.findViewById(R.id.trackTitle)
        private val trackArtist: TextView = itemView.findViewById(R.id.trackArtist)
        private val trackImage: ImageView = itemView.findViewById(R.id.trackImage)
        private val trackPrice: TextView = itemView.findViewById(R.id.trackPrice)
        private val trackCategory: TextView = itemView.findViewById(R.id.trackCategory)
        private val trackReleaseDate: TextView = itemView.findViewById(R.id.trackReleaseDate)
        private val listenButton: Button = itemView.findViewById(R.id.listenButton)

        fun bind(music: MusicItem) {
            trackTitle.text = music.title.label
            trackArtist.text = music.artist.label
            trackPrice.text = music.price.label
            trackCategory.text = music.category.attributes.label
            trackReleaseDate.text = music.releaseDate.attributes.label
            listenButton.setOnClickListener {
                //go to the music link
                println(music.link.label)
                Intent(Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.parse(music.link.label)
                    itemView.context.startActivity(this)
                }
            }

            // Use Glide or any other image loading library to load the image, and specify the
            val lastImage = music.images.size - 1
            val height = music.images[lastImage].attributes.height.toInt()
            Glide.with(itemView.context)
                .load(music.images[lastImage].label)
                .override(height)
                .into(trackImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(musics[position])
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}