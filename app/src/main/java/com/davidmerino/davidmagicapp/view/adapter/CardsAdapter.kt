package com.davidmerino.davidmagicapp.view.adapter

import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.extension.load
import com.davidmerino.davidmagicapp.model.CardView
import kotlinx.android.synthetic.main.item_card.view.*

class CardsAdapter(onItemClickListener: (CardView) -> Unit) :
    RootAdapter<CardView>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.item_card

    override fun viewHolder(view: View): RootViewHolder<CardView> = ViewHolder(view)

    class ViewHolder(view: View) : RootAdapter.RootViewHolder<CardView>(itemView = view) {
        override fun bind(model: CardView) {
            itemView.image.load(model.image)
            itemView.title.text = model.title
            itemView.expansionText.text = model.expansion
        }

    }
}