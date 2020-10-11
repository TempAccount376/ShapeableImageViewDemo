package com.example.myshapeable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.fragment_customize.*

class CustomizeFragment : Fragment() {

    private var options = Options()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_customize, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        radiogroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.mode_rounded -> updateImage(options.copy(mode = CornerFamily.ROUNDED))
                R.id.mode_cut -> updateImage(options.copy(mode = CornerFamily.CUT))
            }
        }

        chk_top_right.setOnCheckedChangeListener { _, isChecked ->
            updateImage(options.copy(topRight = isChecked))
        }

        chk_top_left.setOnCheckedChangeListener { _, isChecked ->
            updateImage(options.copy(topLeft = isChecked))
        }

        chk_bottom_right.setOnCheckedChangeListener { _, isChecked ->
            updateImage(options.copy(bottomRight = isChecked))
        }

        chk_bottom_left.setOnCheckedChangeListener { _, isChecked ->
            updateImage(options.copy(bottomLeft = isChecked))
        }

        seekbar.max = 800
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                updateImage(options.copy(size = p1.toFloat()))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) = Unit

            override fun onStopTrackingTouch(p0: SeekBar?) = Unit

        })

    }

    private fun updateImage(options: Options) {
        this.options = options

        val shapeAppearanceModel = imageView.shapeAppearanceModel.toBuilder()
            .setTopRightCorner(options.mode, if (options.topRight) options.size else 0f)
            .setTopLeftCorner(options.mode, if (options.topLeft) options.size else 0f)
            .setBottomRightCorner(options.mode, if (options.bottomRight) options.size else 0f)
            .setBottomLeftCorner(options.mode, if (options.bottomLeft) options.size else 0f)
            .build()

        imageView.shapeAppearanceModel = shapeAppearanceModel
    }

    data class Options(
        val topRight: Boolean = true,
        val topLeft: Boolean = true,
        val bottomRight: Boolean = true,
        val bottomLeft: Boolean = true,
        val size: Float = 0f,
        @CornerFamily val mode: Int = CornerFamily.ROUNDED,
    )
}
