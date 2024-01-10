package zikrulla.production.dictionary.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.Dictionary
import zikrulla.production.dictionary.databinding.ItemDictionarySelectorBinding
import zikrulla.production.dictionary.databinding.ItemFolderBinding

class FolderAdapter(
    private var list: List<FolderEntity>,
    private val onClick: (FolderEntity) -> Unit,
    private val onClickSetting: (FolderEntity) -> Unit
) : Adapter<FolderAdapter.Vh>() {

    inner class Vh(private val binding: ItemFolderBinding) : ViewHolder(binding.root) {
        fun bind(folderEntity: FolderEntity, position: Int) {
            binding.apply {
                folderName.text = folderEntity.name
                settings.setOnClickListener {
                    onClickSetting.invoke(folderEntity)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submit(list: List<FolderEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val folderEntity = list[position]
        holder.bind(folderEntity, position)
        holder.itemView.setOnClickListener {
            onClick.invoke(folderEntity)
        }
    }

}