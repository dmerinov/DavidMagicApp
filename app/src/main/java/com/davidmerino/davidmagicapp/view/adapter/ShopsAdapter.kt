package com.davidmerino.davidmagicapp.view.adapter

import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.ShopView
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopsAdapter(
    private val onPhoneClickListener: (ShopView) -> Unit,
    private val onFavClickListener: (ShopView, Boolean) -> Unit,
    onItemClickListener: (ShopView) -> Unit
) :
    RootAdapter<ShopView>(
        onItemClickListener = onItemClickListener
    ) {

    override val itemLayoutId: Int = R.layout.item_shop

    override fun viewHolder(view: View): RootViewHolder<ShopView> =
        ViewHolder(
            onPhoneClickListener = { onPhoneClickListener(items[it]) },
            onFavClickListener = { position, isChecked ->
                onFavClickListener(
                    items[position],
                    isChecked
                )
            },
            view = view
        )

    class ViewHolder(
        private val onPhoneClickListener: (Int) -> Unit,
        private val onFavClickListener: (Int, Boolean) -> Unit,
        view: View
    ) :
        RootAdapter.RootViewHolder<ShopView>(itemView = view) {

        init {
            itemView.setOnClickListener { onItemClickListener(adapterPosition) }
            itemView.phoneText.setOnClickListener { onPhoneClickListener(adapterPosition) }
        }

        override fun bind(model: ShopView) {
            itemView.nameText.text = model.name
            itemView.addressText.text = model.address
            itemView.phoneText.text = model.phone
            itemView.isFavourite.setOnCheckedChangeListener(null)
            itemView.isFavourite.isChecked = model.isFavourite
            itemView.isFavourite.setOnCheckedChangeListener { buttonView, isChecked ->
                onFavClickListener(
                    adapterPosition,
                    isChecked
                )
            }
        }

    }
}