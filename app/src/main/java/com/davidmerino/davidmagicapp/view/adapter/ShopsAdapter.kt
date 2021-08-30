package com.davidmerino.davidmagicapp.view.adapter

import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.GeoPoints
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopsAdapter(
    private val onPhoneClickListener: (GeoPoints) -> Unit,
    onItemClickListener: (GeoPoints) -> Unit
) :
    RootAdapter<GeoPoints>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.item_shop

    override fun viewHolder(view: View): RootViewHolder<GeoPoints> =
        ViewHolder(onPhoneClickListener = { onPhoneClickListener(items[it]) }, view = view)

    class ViewHolder(private val onPhoneClickListener: (Int) -> Unit, view: View) :
        RootAdapter.RootViewHolder<GeoPoints>(itemView = view) {

        init {
            itemView.phoneText.setOnClickListener {
                onPhoneClickListener(adapterPosition)
            }
        }

        override fun bind(model: GeoPoints) {
            itemView.nameText.text = model.name
            itemView.addressText.text = model.address
            itemView.phoneText.text = model.phone
        }

    }
}