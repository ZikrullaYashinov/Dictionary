package zikrulla.production.dictionary.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import zikrulla.production.dictionary.databinding.ItemBottomSheetBinding
import zikrulla.production.dictionary.ui.model.BottomSheetModel

class BottomSheetAdapter(
    private var list: List<BottomSheetModel>,
) : Adapter<BottomSheetAdapter.Vh>() {

    inner class Vh(private val binding: ItemBottomSheetBinding) : ViewHolder(binding.root) {
        fun bind(model: BottomSheetModel, position: Int) {
            binding.apply {
                icon.setImageResource(model.icon)
                text.text = model.text
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submit(list: List<BottomSheetModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemBottomSheetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val model = list[position]
        holder.bind(model, position)
        holder.itemView.setOnClickListener {
            model.click.invoke()
        }
    }

}