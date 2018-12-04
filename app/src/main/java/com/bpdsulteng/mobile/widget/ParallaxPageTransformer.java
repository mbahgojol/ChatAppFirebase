package com.bpdsulteng.mobile.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knalb on 04/12/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */

public class ParallaxPageTransformer implements ViewPager.PageTransformer {

    private List<ParallaxTransformInformation> mViewsToParallax
            = new ArrayList<ParallaxTransformInformation>();

    public ParallaxPageTransformer() {
    }

    public ParallaxPageTransformer(@NotNull List<ParallaxTransformInformation> viewsToParallax) {
        mViewsToParallax = viewsToParallax;
    }

    public ParallaxPageTransformer addViewToParallax(
            @NotNull ParallaxTransformInformation viewInfo) {
        if (mViewsToParallax != null) {
            mViewsToParallax.add(viewInfo);
        }
        return this;
    }

    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();

        if (position < -1) {
            // This page is way off-screen to the left.
            view.setAlpha(1);

        } else if (position <= 1 && mViewsToParallax != null) { // [-1,1]
            for (ParallaxTransformInformation parallaxTransformInformation : mViewsToParallax) {
                applyParallaxEffect(view, position, pageWidth, parallaxTransformInformation,
                        position > 0);
            }
        } else {
            // This page is way off-screen to the right.
            view.setAlpha(1);
        }
    }

    private void applyParallaxEffect(View view, float position, int pageWidth,
                                     ParallaxTransformInformation information, boolean isEnter) {
        if (information.isValid() && view.findViewById(information.resource) != null) {
            if (isEnter && !information.isEnterDefault()) {
                view.findViewById(information.resource).setTranslationX(-position * (pageWidth / information.parallaxEnterEffect));
            } else if (!isEnter && !information.isExitDefault()) {
                view.findViewById(information.resource).setTranslationX(-position * (pageWidth / information.parallaxExitEffect));
            }

            if (information.alphaEffect) {
                // alpha
                view.findViewById(information.resource).setAlpha(1.0f - Math.abs(position));
            }
        }
    }


    /**
     * Information to make the parallax effect in a concrete view.
     * <p>
     * parallaxEffect positive values reduces the speed of the view in the translation
     * ParallaxEffect negative values increase the speed of the view in the translation
     * Try values to see the different effects. I recommend 2, 0.75 and 0.5
     */
    public static class ParallaxTransformInformation {

        public static final float PARALLAX_EFFECT_DEFAULT = -101.1986f;

        int resource = -1;
        float parallaxEnterEffect = 1f;
        float parallaxExitEffect = 1f;
        boolean alphaEffect = false;

        public ParallaxTransformInformation(int resource, float parallaxEnterEffect, float parallaxExitEffect, boolean alphaEffect) {
            this.alphaEffect = alphaEffect;
            this.resource = resource;
            this.parallaxEnterEffect = parallaxEnterEffect;
            this.parallaxExitEffect = parallaxExitEffect;
        }

        public boolean isValid() {
            return parallaxEnterEffect != 0 && parallaxExitEffect != 0 && resource != -1;
        }

        public boolean isEnterDefault() {
            return parallaxEnterEffect == PARALLAX_EFFECT_DEFAULT;
        }

        public boolean isExitDefault() {
            return parallaxExitEffect == PARALLAX_EFFECT_DEFAULT;
        }
    }
}
