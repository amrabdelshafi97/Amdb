package amr_ayoub.movieez.adapter

import amr_ayoub.movieez.R
import amr_ayoub.movieez.model.Review
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ReviewItemViewAdapter(private val context: Context, private val dataSource: List<Review>) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.item_review_list, parent, false)
        val author = rowView.findViewById(R.id.item_author) as TextView
        val content = rowView.findViewById(R.id.item_content) as TextView
        author.text = dataSource[position].author
        content.text = dataSource[position].content
        return rowView
    }

}