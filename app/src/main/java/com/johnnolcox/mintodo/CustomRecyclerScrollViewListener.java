package com.johnnolcox.mintodo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class CustomRecyclerScrollViewListener extends RecyclerView.OnScrollListener {
    private int scrollDist = 0;
    private boolean isVisible = true;
    private static final float MINIMUM = 20;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(isVisible && scrollDist>MINIMUM){
            Log.d("Jnolcox", "Hide "+scrollDist);
            hide();
            scrollDist = 0;
            isVisible = false;
        }
        else if(!isVisible && scrollDist < -MINIMUM){
            Log.d("Jnolcox", "Show "+scrollDist);
            show();
            scrollDist = 0;
            isVisible =true;
        }
        if((isVisible && dy>0) || (!isVisible && dy<0)){
            Log.d("Jnolcox", "Add Up "+scrollDist);
            scrollDist += dy;
        }
    }
    public abstract void show();
    public abstract void hide();
}
