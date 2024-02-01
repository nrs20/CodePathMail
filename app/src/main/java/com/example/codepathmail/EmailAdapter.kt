package com.example.codepathmail

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//RecyclerView.Adapter: A class that extends RecyclerView.Adapter and is
// responsible for providing the data to be displayed in the RecyclerView
class EmailAdapter(private val emails: MutableList<Email>) : RecyclerView.Adapter<EmailAdapter.ViewHolder>()
{
//source for line 13: https://stackoverflow.com/questions/59221448/mutablemapkey-boolean-returns-nullable-boolean-for-value-access-with-key
    //array of bool values the same size as emails. all initially false to show they are unread
    private val viewClicked = mutableMapOf<Int, Boolean>().withDefault { false }

    //The ViewHolder holds references to the views,
    // and these references are updated when new data needs to be displayed.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val senderTextView: TextView
        val titleTextView: TextView
        val summaryTextView: TextView
        val dateTextView: TextView


        //called when an instance of the view holder is created
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            senderTextView = itemView.findViewById(R.id.senderTv)
            titleTextView = itemView.findViewById(R.id.titleTv)
            summaryTextView = itemView.findViewById(R.id.summaryTv)
            dateTextView = itemView.findViewById(R.id.dateTv)
        }
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.email_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return emails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val email = emails[position]

        // Set item views based on views and data model
        holder.senderTextView.text = email.sender
        holder.titleTextView.text = email.title
        holder.summaryTextView.text = email.summary
        holder.dateTextView.text = email.date

        // Set text appearance based on the click status
        if (viewClicked[position] == true) {
            // if clicked, unbold
            holder.senderTextView.setTypeface(null, Typeface.NORMAL)
            holder.titleTextView.setTypeface(null, Typeface.NORMAL)
            holder.summaryTextView.setTypeface(null, Typeface.NORMAL)
            holder.dateTextView.setTypeface(null, Typeface.NORMAL)
        } else {
            // if not clicked, bold
            holder.senderTextView.setTypeface(null, Typeface.BOLD)
            holder.titleTextView.setTypeface(null, Typeface.BOLD)
            holder.summaryTextView.setTypeface(null, Typeface.BOLD)
            holder.dateTextView.setTypeface(null, Typeface.BOLD)
        }

        holder.itemView.setOnClickListener {
            // toggle the clicked status
            viewClicked[position] = !(viewClicked[position] ?: false)
            // notify the adapter
            notifyItemChanged(position)
        }
    }

}