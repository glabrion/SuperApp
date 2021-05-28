package ru.sulatskov.superapp.main.screens.image_view_screen

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.databinding.FragmentImageViewBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.MainActivity
import javax.inject.Inject


class ImageViewFragment : BaseFragment(), ImageViewContractInterface.View {

    companion object {
        const val TAG = "ImageViewFragment"
        const val ACTION_REQUEST_GALLERY = 1
    }

    @Inject
    lateinit var presenter: ImageViewPresenter
    private var fragmentAppBarBinding: FragmentImageViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentImageViewBinding.inflate(inflater, container, false)
        fragmentAppBarBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentAppBarBinding?.selectImage?.setOnClickListener {
            presenter.onSelectImageClick()
        }
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun initToolbar() {
        fragmentAppBarBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentAppBarBinding?.toolbar?.title = getString(R.string.app_bar_text)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun onDestroyView() {
        fragmentAppBarBinding = null
        super.onDestroyView()
    }

    override fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        val chooser = Intent.createChooser(intent, "Choose a Picture")
        startActivityForResult(chooser, ACTION_REQUEST_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val galleryImageUri: Uri? = data?.data
        try {
            fragmentAppBarBinding?.image?.setImageURI(galleryImageUri)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}