package fr.ozoneprojects.gitpocket.ui.repository

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.ozoneprojects.gitpocket.R
import fr.ozoneprojects.gitpocket.databinding.ItemListRepositoryBinding
import fr.ozoneprojects.gitpocket.model.Repository

class RepositoryListAdapter : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private lateinit var repositoryList: List<Repository>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListAdapter.ViewHolder {
        val binding: ItemListRepositoryBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_repository, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryListAdapter.ViewHolder, position: Int) {
        holder.bind(repositoryList[position])
        Log.d("RepoBind", "Adapter binding ${repositoryList[position].name}")
    }

    override fun getItemCount(): Int {
        return if (::repositoryList.isInitialized) repositoryList.size else 0
    }

    fun updateRepositoryList(repositoryList: List<Repository>) {
        this.repositoryList = repositoryList
        Log.d("RepoBind", "Adapter binding $repositoryList")
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RepositoryViewModel()

        fun bind(repository: Repository) {
            viewModel.bind(repository)
            binding.viewModel = viewModel
        }

    }

}
