package com.nasyith.compinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvProducts: RecyclerView
    private val list = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProducts = findViewById(R.id.rv_products)
        rvProducts.setHasFixedSize(true)

        list.addAll(listProducts)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val showAboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(showAboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val listProducts: ArrayList<Product>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataProcessor = resources.getStringArray(R.array.data_processor)
            val dataMemory = resources.getStringArray(R.array.data_memory)
            val dataVideoGraphic = resources.getStringArray(R.array.data_video_graphic)
            val dataStorage = resources.getStringArray(R.array.data_storage)
            val dataPhoto = resources.getStringArray(R.array.data_photo)
            val listProduct = ArrayList<Product>()
            for (i in dataName.indices) {
                val product = Product(dataName[i], dataDescription[i], dataProcessor[i], dataMemory[i], dataVideoGraphic[i], dataStorage[i], dataPhoto[i])
                listProduct.add(product)
            }
            return listProduct
        }

    private fun showRecyclerList() {
        rvProducts.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListProductAdapter(list)
        rvProducts.adapter = listProductAdapter

        listProductAdapter.setOnItemClickCallback(object : ListProductAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Product) {
                showDetailProduct(data)
            }
        })
    }

    private fun showDetailProduct(product: Product) {
        val showDetailProductIntent = Intent(this@MainActivity, DetailProductActivity::class.java)
        showDetailProductIntent.putExtra(DetailProductActivity.EXTRA_PRODUCT, product)
        startActivity(showDetailProductIntent)
    }
}