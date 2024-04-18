package com.example.thenewsapp.ui.activities

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.thenewsapp.R
import com.example.thenewsapp.databinding.ActivityNewDetailBinding
import com.example.thenewsapp.model.News
import java.util.Locale

class NewsDetailAct: AppCompatActivity(), TextToSpeech.OnInitListener  {
    private lateinit var binding: ActivityNewDetailBinding
    private var new: News? = null
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        binding.imvBack.setOnClickListener {
            try {
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.imvVoice.setOnClickListener {
            speakOut(binding.tvDescriptionNew.text.toString())
        }
    }

    private fun initViews() {
        new = intent.getSerializableExtra("data") as News?
        tts = TextToSpeech(this, this)

        binding.tvTitleNew.text = new?.title.toString()
        Glide.with(this).load(new?.imageURL)
            .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(binding.imvNew)
        binding.tvDescriptionNew.text = new?.description ?: "Người thông minh bí mật trồng 6 cây cảnh trong nhà: Vừa gia tăng vận khí lại biết 'nuốt' bụi bẩn, làm đẹp không gian"
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale("vi"))
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }


    override fun onDestroy() {
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
        super.onDestroy()
    }
}