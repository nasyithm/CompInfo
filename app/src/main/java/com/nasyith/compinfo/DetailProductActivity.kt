package com.nasyith.compinfo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nasyith.compinfo.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }

    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PRODUCT, Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PRODUCT)
        }

        if (product != null) {
            Glide.with(this)
                .load(product.photo)
                .into(binding.imgProductPhoto)
            binding.tvProductName.text = product.name
            binding.tvDescription.text = product.description
            binding.tvProcessor.text = product.processor
            binding.tvMemory.text = product.memory
            binding.tvVideoGraphic.text = product.videoGraphic
            binding.tvStorage.text = product.storage
        }

        fun actionSend() {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "*" + product?.name + "*\n\n"
                        + product?.description + "\n\n"
                        + "Specification\n"
                        + "Processor : " + product?.processor + "\n"
                        + "Memory : " + product?.memory + "\n"
                        + "Video Graphic : " + product?.videoGraphic + "\n"
                        + "Storage : " + product?.storage)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, null))
        }

        binding.actionShare.setOnClickListener {
            actionSend()
        }
    }
}