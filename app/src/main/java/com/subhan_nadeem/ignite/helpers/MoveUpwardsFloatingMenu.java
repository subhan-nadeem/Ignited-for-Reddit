package com.subhan_nadeem.ignite.helpers;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by Subhan Nadeem on 2017-10-02.
 */

@CoordinatorLayout.DefaultBehavior(MoveUpwardBehavior.class)
public class MoveUpwardsFloatingMenu extends FloatingActionMenu {
    public MoveUpwardsFloatingMenu(Context context) {
        super(context);
    }

    public MoveUpwardsFloatingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveUpwardsFloatingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
