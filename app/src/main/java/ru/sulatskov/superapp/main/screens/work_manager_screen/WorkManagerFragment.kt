package ru.sulatskov.superapp.main.screens.work_manager_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.databinding.FragmentWorkManagerBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import javax.inject.Inject


class WorkManagerFragment : BaseFragment(), WorkManagerContractInterface.View {

    companion object {
        const val TAG = "WorkManagerFragmentTag"
    }


    private var fragmentWorkManagerBinding: FragmentWorkManagerBinding? = null

    @Inject
    lateinit var presenter: WorkManagerPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWorkManagerBinding.inflate(inflater, container, false)
        fragmentWorkManagerBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val workRequest = OneTimeWorkRequest.Builder(SuperAppWork::class.java).build()
        context?.let { WorkManager.getInstance(it).enqueue(workRequest) }
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun initToolbar() {
        fragmentWorkManagerBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        fragmentWorkManagerBinding?.toolbar?.title = getString(R.string.work_manager)
    }
}