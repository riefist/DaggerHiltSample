package com.example.learndaggerhilt.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learndaggerhilt.databinding.ActivityCurrencyConverterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyConverterBinding
    private val vm: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            vm.convert(
                amountStr = binding.etFrom.text.toString(),
                fromCurrency = binding.spFromCurrency.selectedItem.toString(),
                toCurrency = binding.spToCurrency.selectedItem.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            vm.conversion.collect { event ->
                when(event){
                    is CurrencyViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is CurrencyViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = event.resultText
                    }
                    is CurrencyViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.errorText
                    }
                    else -> Unit
                }

            }
        }
    }
}