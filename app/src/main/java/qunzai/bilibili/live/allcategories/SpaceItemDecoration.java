package qunzai.bilibili.live.allcategories;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import static qunzai.bilibili.base.BilibiliApp.getScreenWidth;

/**
 * Created by XingMingDa on 16/12/2.
 * SpaceItemDecoration设置Item间隔
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        space = (getScreenWidth() - view.getHeight() * 3) / 6;
        outRect.left = space;
        outRect.right = space;
        outRect.top = space / 2;
    }
}
