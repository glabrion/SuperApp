package ru.sulatskov.superapp.main.screens.content_provider_screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.gone
import ru.sulatskov.superapp.common.toast
import ru.sulatskov.superapp.common.visible
import ru.sulatskov.superapp.databinding.FragmentContactBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.MainActivity
import javax.inject.Inject

class ContactFragment : BaseFragment(), ContactContractInterface.View {

    companion object {
        const val TAG = "ContactFragment"
        var READ_CONTACTS_GRANTED = false
        var REQUEST_CODE_READ_CONTACTS = 1
    }

    @Inject
    lateinit var presenter: ContactPresenter

    private var fragmentContactBinding: FragmentContactBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactBinding.inflate(inflater, container, false)
        fragmentContactBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentContactBinding?.contactList?.gone()
        fragmentContactBinding?.notContact?.gone()
        val hasReadContactPermission = ContextCompat.checkSelfPermission(
            view.context,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
        if (hasReadContactPermission) {
            READ_CONTACTS_GRANTED = true
        } else {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_CODE_READ_CONTACTS
                )
            }
        }
        if (READ_CONTACTS_GRANTED) {
            showContact()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_READ_CONTACTS){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                READ_CONTACTS_GRANTED = true
            }
        }
        if (READ_CONTACTS_GRANTED){
            showContact()
        }else{
            showToastNotPermission()
        }
    }

    override fun showToastNotPermission() {
        toast(getString(R.string.not_permission))
    }

    override fun showContact() {
        view?.context?.let {
            val contacts = getContactList()
            if (contacts.isEmpty()){
                showToastContactsIsEmpty()
                fragmentContactBinding?.notContact?.visible()
                return
            }
            val adapter =
                ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, contacts)
            fragmentContactBinding?.contactList?.adapter = adapter
            fragmentContactBinding?.contactList?.visible()
        }
    }

    override fun showToastContactsIsEmpty() {
        toast(getString(R.string.not_contact))
    }

     override fun getContactList(): List<String> {
        val contacts = mutableListOf<String>()
        val contentResolver = (activity as? MainActivity)?.contentResolver
        val cursor =
            contentResolver?.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        cursor?.let {
            while (cursor.moveToNext()) {
                val contact =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                contact?.let {
                    contacts.add(it)
                }
            }
        }
        return contacts
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun initToolbar() {
        fragmentContactBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentContactBinding?.toolbar?.title = getString(R.string.list_of_contact)
    }

    override fun onDestroyView() {
        fragmentContactBinding = null
        super.onDestroyView()
    }
}