package zikrulla.production.dictionary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.model.Dictionary
import zikrulla.production.dictionary.databinding.ItemDictionarySelectorBinding

class DictionarySelectorAdapter(private var list: List<Dictionary>) : Adapter<DictionarySelectorAdapter.Vh>() {

    inner class Vh(private val binding: ItemDictionarySelectorBinding) : ViewHolder(binding.root) {
        fun bind(dictionary: Dictionary, position: Int) {
            binding.apply {
                key.text = dictionary.key
                value.text = dictionary.value
                check.setImageResource(if (dictionary.isSelected) R.drawable.ic_checked else R.drawable.ic_unchecked)
                check.setOnClickListener {
                    list[position].isSelected = !dictionary.isSelected
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemDictionarySelectorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind(list[position], position)
    }

}