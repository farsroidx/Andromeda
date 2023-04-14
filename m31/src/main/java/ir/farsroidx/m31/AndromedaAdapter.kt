@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ir.farsroidx.m31.additives.autoViewDataBinding

abstract class AndromedaAdapter<VDB: ViewDataBinding, M: Any>
    : RecyclerView.Adapter<AndromedaAdapter.AndromedaViewHolder<VDB>>()
{

    protected lateinit var context: Context

    private lateinit var layoutInflater: LayoutInflater

    protected var mOnActionClicked: (item: M) -> Unit = {}

    protected var mOnItemClicked: (item: M) -> Unit = {}

    protected var mOnItemLongClicked: (item: M) -> Unit = {}

    class AndromedaViewHolder<DB: ViewDataBinding> (val dataBinding: DB)
        : RecyclerView.ViewHolder(dataBinding.root)

    private val mItems = mutableListOf<M>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndromedaViewHolder<VDB> {
        return AndromedaViewHolder(
            autoViewDataBinding(
                context, layoutInflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AndromedaViewHolder<VDB>, position: Int) {
        onBindViewHolder(holder.dataBinding, mItems[position], position)
    }

    @CallSuper
    open fun addItem(item: M) {
        synchronized(mItems) {
            this.mItems.add(item)
            notifyItemInserted(itemCount - 1)
        }
    }

    @CallSuper
    open fun addItems(items: List<M>) {
        synchronized(mItems) {
            val iCount = itemCount
            this.mItems.addAll(items)
            notifyItemRangeInserted(iCount, itemCount)
        }
    }

    @CallSuper
    open fun addItemAt(position: Int, item: M) {
        synchronized(mItems) {
            this.mItems.add(position, item)
            notifyItemInserted(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    @CallSuper
    open fun addItemsAt(position: Int, items: List<M>) {
        synchronized(mItems) {
            this.mItems.addAll(position, items)
            notifyItemRangeInserted(position, items.size)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    @CallSuper
    open fun updateItem(item: M) {
        synchronized(mItems) {
            val position = this.mItems.indexOf(item)
            this.mItems[position] = item
            notifyItemChanged(position)
        }
    }

    @CallSuper
    open fun updateItemAt(position: Int, items: M) {
        synchronized(mItems) {
            this.mItems[position] = items
            notifyItemChanged(position)
        }
    }

    @CallSuper
    open fun removeItem(item: M) {
        synchronized(mItems) {
            val position = this.mItems.indexOf(item)
            this.mItems.remove(item)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    @CallSuper
    open fun removeItemAt(position: Int) {
        synchronized(mItems) {
            this.mItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    @CallSuper
    open fun clear() {
        synchronized(mItems) {
            val lastItemCount = itemCount
            this.mItems.clear()
            notifyItemRangeRemoved(0, lastItemCount)
            notifyItemChanged(0, itemCount)
        }
    }

    fun setOnDeleteActionClicked(onClicked: (item: M) -> Unit) {
        this.mOnActionClicked = onClicked
    }

    fun setOnItemClicked(onClicked: (item: M) -> Unit) {
        this.mOnItemClicked = onClicked
    }

    fun setOnItemLongClicked(onClicked: (item: M) -> Unit) {
        this.mOnItemLongClicked = onClicked
    }

    protected abstract fun onBindViewHolder(dataBinding: VDB, item: M, position: Int)

    protected abstract fun onViewRecycled(dataBinding: VDB)

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.context = recyclerView.context
        this.layoutInflater = LayoutInflater.from(context)
    }

    @CallSuper
    override fun onViewRecycled(holder: AndromedaViewHolder<VDB>) {
        super.onViewRecycled(holder)
        onViewRecycled( holder.dataBinding )
    }

    @CallSuper
    override fun getItemCount(): Int = mItems.size
}
