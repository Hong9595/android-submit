package com.example.arteum.util


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


abstract class ExtensionsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
}