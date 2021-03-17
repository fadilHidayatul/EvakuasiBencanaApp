package com.example.evakuasiapp.Informasi

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evakuasiapp.databinding.ActivityInformasiGempaBinding

class InformasiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInformasiGempaBinding

    lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiGempaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent

        if (intent.getStringExtra("bencana") == "gempa"){
            url  = "https://www.bmkg.go.id/gempabumi/gempabumi-terkini.bmkg"
        }else if (intent.getStringExtra("bencana") == "tsunami" ){
            url  = "https://www.bmkg.go.id/tsunami/"
        }else if (intent.getStringExtra("bencana") == "cuaca"){
            url = "https://www.bmkg.go.id/cuaca/prakiraan-cuaca.bmkg?Kota=Padang&AreaID=501545&Prov=32"
        }

        binding.webview.webViewClient = ClientWebView()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(url)

    }

    private class ClientWebView : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            view.loadUrl(url!!)
            return true
        }
    }
}
