package com.example.b00sti.tripchallenge.utils.helpers.crescento_view;

import android.graphics.LinearGradient;
import android.graphics.Shader;

/**
 * Created by b00sti on 06.12.2016.
 */

public class GradientProvider {

    static Shader getShader(int gradientStartColor, int gradientEndColor, int gradientDirection, int width, int height) {
        switch (gradientDirection) {
            case CrescentoImageView.Gradient.TOP_TO_BOTTOM:
                return new LinearGradient(0, 0, 0, height, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            case CrescentoImageView.Gradient.BOTTOM_TO_TOP:
                return new LinearGradient(0, height, 0, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            case CrescentoImageView.Gradient.LEFT_TO_RIGHT:
                return new LinearGradient(0, 0, width, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            case CrescentoImageView.Gradient.RIGHT_TO_LEFT:
                return new LinearGradient(width, 0, 0, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            default:
                return new LinearGradient(0, 0, height, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
        }
    }
}
