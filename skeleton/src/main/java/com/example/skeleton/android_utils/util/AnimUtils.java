package com.example.skeleton.android_utils.util;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.skeleton.java_utils.outside.WeakHandler;

import java.util.List;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class AnimUtils {

    public static void changeAsGoneView(final long totalTime, @NonNull final View view, final View view2, @Nullable final EndCallback callback) {
        view.animate().scaleX(0f).scaleY(0f).setDuration(totalTime / 2).start();

        view2.setScaleX(0f);
        view2.setScaleY(0f);

        new WeakHandler().postDelayed(() -> {
            view2.animate().scaleY(1f).scaleX(1f).setDuration(totalTime / 2).start();
            if (callback != null)
                new WeakHandler().postDelayed(callback::onEnd, totalTime / 2);
        }, totalTime / 2);
    }

    public static void bouncy(final View view, final float minScale, final float maxScale, final int animationDuration) {
        view.animate().scaleX(minScale).scaleY(minScale).setDuration(animationDuration / 2).start();

        new WeakHandler(Looper.getMainLooper()).postDelayed(() -> view.animate().scaleX(maxScale).scaleY(maxScale).setDuration(animationDuration / 2).start(), animationDuration / 2);
    }

    public static void animateFade(View v, float alphaStart, float alphaEnd, int duration) {
        if (v != null) {
            v.setAlpha(alphaStart);
            v.animate().alpha(alphaEnd).setDuration(duration).start();
        }
    }

    public static void animateFade(View v, float alphaStart, float alphaEnd, int duration, WeakHandler h, @Nullable final EndCallback callback) {
        animateFade(v, alphaStart, alphaEnd, duration);
        if (callback != null) {
            if (h != null) h.postDelayed(callback::onEnd, duration);
        }
    }

    public static void rotateView(@NonNull View rotatedView) {
        int w = rotatedView.getWidth(), h = rotatedView.getHeight();
        int diff = w / 2 - h / 2;
        rotatedView.setRotation(270);
        rotatedView.setTranslationX(-diff);
    }

    public static void setExitSlideAnimation(Fragment fragment, int duration, TimeInterpolator timeInterpolator) {
        Slide slide = getSlideTransition(duration, timeInterpolator);
        if (slide != null) {
            fragment.setExitTransition(slide);
        }
    }

    public static void setEnterSlideAnimation(Fragment fragment, int duration, TimeInterpolator timeInterpolator) {
        Slide slide = getSlideTransition(duration, timeInterpolator);
        if (slide != null) {
            fragment.setEnterTransition(slide);
        }
    }

    public static void setExitFadeAnimation(Fragment fragment, int duration, TimeInterpolator timeInterpolator) {
        Fade fade = getFadeTransition(duration, timeInterpolator);
        if (fade != null) {
            fragment.setExitTransition(fade);
        }
    }

    public static void setEnterFadeAnimation(Fragment fragment, int duration, TimeInterpolator timeInterpolator) {
        Fade fade = getFadeTransition(duration, timeInterpolator);
        if (fade != null) {
            fragment.setEnterTransition(fade);
        }
    }

    public static void setExitExplodeAnimation(Fragment fragment, int duration, TimeInterpolator timeInterpolator) {
        Explode explode = getExplodeTransition(duration, timeInterpolator);
        if (explode != null) {
            fragment.setExitTransition(explode);
        }
    }

    public static void setEnterExplodeAnimation(Fragment fragment, int duration, TimeInterpolator timeInterpolator) {
        Explode explode = getExplodeTransition(duration, timeInterpolator);
        if (explode != null) {
            fragment.setEnterTransition(explode);
        }
    }

    @Nullable
    public static Fade getFadeTransition(int duration, TimeInterpolator timeInterpolator) {
        Fade fade = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            fade = new Fade();
            fade.setDuration(duration);
            fade.setInterpolator(timeInterpolator);
        }
        return fade;
    }

    @Nullable
    public static Slide getSlideTransition(int duration, TimeInterpolator timeInterpolator) {
        Slide slide = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slide = new Slide();
            slide.setDuration(duration);
            slide.setInterpolator(timeInterpolator);
        }
        return slide;
    }

    @Nullable
    public static Explode getExplodeTransition(int duration, TimeInterpolator timeInterpolator) {
        Explode explode = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            explode = new Explode();
            explode.setDuration(duration);
            explode.setInterpolator(timeInterpolator);
        }
        return explode;
    }

    public static void changeFragmentsWithSharedElems(@NonNull Fragment fragmentCurrent, @NonNull Fragment fragmentTarget, Transition transition, List<View> views, Object exitTransition, Object enterTransition, int container) {

        fragmentCurrent.setExitTransition(exitTransition);
        fragmentTarget.setEnterTransition(enterTransition);

        fragmentCurrent.setSharedElementReturnTransition(transition);
        fragmentTarget.setSharedElementEnterTransition(transition);

        FragmentTransaction fragmentManager = fragmentCurrent.getFragmentManager()
                .beginTransaction()
                .replace(container, fragmentTarget);

        for (View view : views) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fragmentManager.addSharedElement(view, view.getTransitionName());
            }
        }

        fragmentManager.commitAllowingStateLoss();
    }

    public static void changeAsFadeViews(Context c, final ImageView v, final int new_image) {
        final Animation anim_out = android.view.animation.AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in = android.view.animation.AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setDuration(100);
        anim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setImageResource(new_image);
                anim_in.setDuration(100);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

    private static void fadein(View view) {
        animateFromResource(view, android.R.anim.fade_in);
    }

    private static void fadeout(View view) {
        animateOutFromResource(view, android.R.anim.fade_out);
    }

    public static void animateFromResource(final View view, int animationId) {
        animateInFromResource(view, animationId);
    }

    public static void animateInFromResource(final View view, int animationId) {
        animateFromResource(view, animationId, false);
    }

    public static void animateOutFromResource(final View view, int animationId) {
        animateFromResource(view, animationId, true);
    }

    public static void animateFromResource(final View view, int animationId, final boolean out) {
        if (view == null) return;

        final Animation animation = AnimationUtils.loadAnimation(view.getContext(), animationId);
        final float alpha = view.getAlpha();

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (out) {
                    view.setVisibility(View.GONE);
                    view.setAlpha(alpha);
                }
            }
        });

        if (!out) view.setVisibility(View.VISIBLE);

        view.startAnimation(animation);
    }

    public static ScaleAnimation exitScaleAnim(float toXscale, float toYscale) {
        return new ScaleAnimation(1.0f, toXscale, 1.0f, toYscale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    }

    public static ScaleAnimation enterScaleAnim(float fromXscale, float fromYscale) {
        return new ScaleAnimation(fromXscale, 1.0f, fromYscale, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    }

    public static ScaleAnimation enterScaleAnim(float fromXscale, float fromYscale, int duration) {
        ScaleAnimation animation = enterScaleAnim(fromXscale, fromYscale);
        animation.setDuration(duration);
        return animation;
    }


    public interface EndCallback {
        void onEnd();
    }

    public interface MidCallback {
        void onMid();
    }
}
