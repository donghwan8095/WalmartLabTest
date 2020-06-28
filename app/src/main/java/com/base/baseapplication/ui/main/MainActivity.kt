package com.base.baseapplication.ui.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.baseapplication.R
import com.base.baseapplication.data.network.models.Availability
import com.base.baseapplication.data.network.models.Car
import com.base.baseapplication.data.network.models.CarList
import com.base.baseapplication.ui.base.BaseActivity
import com.base.baseapplication.util.SharedPreferencesUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.View , AdapterView.OnItemSelectedListener{

    @Inject
    lateinit var mainPresenter : MainContract.Presenter
    @Inject
    lateinit var gson : Gson

    private val DATA_KEY = "DATA_KEY"
    private lateinit var adapter : CarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()
        mainPresenter.getCarList()
    }

    private fun initUi() {
        carList.layoutManager = LinearLayoutManager(this)
        adapter = CarListAdapter(this) { car: Car ->
            mainPresenter.getAvailability(car.id)
        }
        carList.adapter = adapter

        sortBySpinner.onItemSelectedListener = this
    }

    override fun getContentViewResource() : Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.onAttach(this)
        val data = SharedPreferencesUtil.getStringData(this, DATA_KEY, null)
        data?.let { data ->
            val listType: Type = object : TypeToken<ArrayList<Car?>?>() {}.type
            gson.run {
                adapter.updateCarList(this.fromJson<ArrayList<Car>>(data, listType))
            }
        }
    }

    override fun onPause() {
        super.onPause()
        SharedPreferencesUtil.saveStringData(this, DATA_KEY, gson.toJson(adapter.carList))
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.onDetach()
    }

    override fun updateAvailability(id: Int, availability: Availability) {
        adapter.updateAvailability(id, availability)
    }

    override fun updateCarList(carList : CarList) {
        adapter.updateCarList(carList.carList)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showToast(text: String) {
        showToastMessage(text)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        adapter.sortedWith(position)
    }
    
}
