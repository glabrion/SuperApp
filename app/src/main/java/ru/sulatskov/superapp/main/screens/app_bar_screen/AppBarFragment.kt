package ru.sulatskov.superapp.main.screens.app_bar_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.snackbar
import ru.sulatskov.superapp.databinding.FragmentAppBarBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import javax.inject.Inject

class AppBarFragment : BaseFragment(), AppBarContractInterface.View {

    companion object {
        const val TAG = "AppBarFragmentTag"
    }

    @Inject
    lateinit var presenter: AppBarPresenter

    private var fragmentAppBarBinding: FragmentAppBarBinding? = null
    private var isLiked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAppBarBinding.inflate(inflater, container, false)
        fragmentAppBarBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun initToolbar() {
        fragmentAppBarBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentAppBarBinding?.toolbar?.title = getString(R.string.app_bar_text)
        fragmentAppBarBinding?.toolbar?.inflateMenu(R.menu.menu_app_bar_screen)
        fragmentAppBarBinding?.toolbar?.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
        initSearchAction()
    }

    private fun initSearchAction() {
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_like)?.isVisible = true
                fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_settings)?.isVisible =
                    true
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_like)?.isVisible = false
                fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_settings)?.isVisible =
                    false
                return true
            }
        }
        val actionSearchMenuItem =
            fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_search)
        actionSearchMenuItem?.setOnActionExpandListener(expandListener)

        (actionSearchMenuItem?.actionView as? android.widget.SearchView)?.setOnQueryTextListener(
            object :
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    presenter.onSearchChange(newText)
                    return true
                }
            })
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            showSnackbar(ToastType.ACTION_SETTING)
            true
        }
        R.id.action_like -> {
            isLiked = !isLiked
            showSnackbar(ToastType.ACTION_LIKE)
            toggleLike()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        fragmentAppBarBinding = null
        super.onDestroyView()
    }

    private fun showSnackbar(type: ToastType) {
        when (type) {
            ToastType.ACTION_SETTING -> snackbar(getString(R.string.setting_menu))
            ToastType.ACTION_LIKE -> if (isLiked) {
                snackbar(getString(R.string.like))
            } else {
                snackbar(getString(R.string.unlike))
            }
        }
    }

    private fun toggleLike() {
        if (isLiked) {
            fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_like)?.icon =
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_like_white_filled) }
        } else {
            fragmentAppBarBinding?.toolbar?.menu?.findItem(R.id.action_like)?.icon =
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_like_white_outline) }
        }
    }

    enum class ToastType {
        ACTION_SETTING,
        ACTION_LIKE
    }

    override fun showText(newText: String?) {
        fragmentAppBarBinding?.searchText?.text = newText
    }
}