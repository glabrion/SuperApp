package ru.sulatskov.superapp.main.screens.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.toast
import ru.sulatskov.superapp.databinding.FragmentRecyclerViewBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import javax.inject.Inject

class RecyclerViewFragment : BaseFragment(), RecyclerViewContractInterface.View, CityClickListener {

    companion object {
        const val TAG = "RecyclerViewFragmentTag"
    }

    @Inject
    lateinit var presenter: RecyclerViewPresenter
    private var fragmentRecyclerViewBinding: FragmentRecyclerViewBinding? = null
    private var adapter = PhotosAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRecyclerViewBinding =
            FragmentRecyclerViewBinding.inflate(inflater, container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        return fragmentRecyclerViewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentRecyclerViewBinding?.list?.adapter = adapter
        fragmentRecyclerViewBinding?.list?.layoutManager = LinearLayoutManager(context)
        fragmentRecyclerViewBinding?.list.let {
            it?.addItemDecoration(
                StickHeaderItemDecoration(
                    list = it,
                    listener = adapter
                )
            )
        }
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun initToolbar() {
        fragmentRecyclerViewBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        fragmentRecyclerViewBinding?.toolbar?.title = getString(R.string.recycler_view)
    }

    override fun onDestroyView() {
        fragmentRecyclerViewBinding = null
        super.onDestroyView()
    }

    override fun showList(data: MutableList<Pair<Any, Type>>) {
        adapter.setData(data)
    }

    override fun onCityClick(name: String?) {
        name?.let { toast(it) }
    }
}