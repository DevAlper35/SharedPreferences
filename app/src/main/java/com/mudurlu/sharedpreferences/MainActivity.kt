package com.mudurlu.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mudurlu.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var depolama : SharedPreferences
    var hafizaIsim : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        depolama = getSharedPreferences("com.mudurlu.sharedpreferences", MODE_PRIVATE)
        hafizaIsim = depolama.getString("isim","")
        binding.textView.text = hafizaIsim
    }

    fun btnSave(view : View){
        val ad = binding.editAd.text.toString()
        if (ad.isNullOrEmpty()) {
            Toast.makeText(this, "İsim giriniz", Toast.LENGTH_LONG).show()
        }
        else{
            depolama.edit().putString("isim", ad).apply()
            binding.textView.text = ad
            Toast.makeText(this, "Hoşgeldin, $ad", Toast.LENGTH_LONG).show()
        }
    }

    fun btnDelete(view : View){
        binding.textView.text = ""
        depolama.edit().remove("isim").apply()
        Toast.makeText(this, "Silme işlemi başarılı", Toast.LENGTH_LONG).show()
    }
}