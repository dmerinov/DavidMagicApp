package com.davidmerino.davidmagicapp.view.adapter

import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.GeoPoints
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopsAdapter() : RootAdapter<GeoPoints>() {

    override val itemLayoutId: Int = R.layout.item_shop

    override fun viewHolder(view: View): RootViewHolder<GeoPoints> = ViewHolder(view)

    class ViewHolder(view: View) : RootAdapter.RootViewHolder<GeoPoints>(itemView = view) {
        override fun bind(model: GeoPoints) {
            itemView.nameText.text = model.name
            itemView.addressText.text = model.address
            itemView.phoneText.text = model.phone
        }

    }
}