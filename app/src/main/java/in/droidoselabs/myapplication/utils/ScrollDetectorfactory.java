package in.droidoselabs.myapplication.utils;

/**
 * Created by clicklabs on 6/19/17.
 */

import android.view.View;


public interface ScrollDetectorfactory {
    /**
     * Create new instance of {@link ScrollDetectors.ScrollDetector} based on the parameter v
     *
     * @param v
     * @return
     */
    public ScrollDetectors.ScrollDetector newScrollDetector(View v);
}

