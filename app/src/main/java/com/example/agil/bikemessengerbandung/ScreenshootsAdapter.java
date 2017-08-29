package com.example.agil.bikemessengerbandung;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.agil.bikemessengerbandung.R;

/**
 * Created by Agil on 7/21/2017.
 */

public class ScreenshootsAdapter extends PagerAdapter {

    private int[] image_resources = {
            R.drawable.abaout01,
            R.drawable.abaout02,
            R.drawable.about03,
    };
    private Context ctx;

    private LayoutInflater layoutInflater;

    public ScreenshootsAdapter(Context ctx) {
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object){
        return (view == (LinearLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container,int posisition){
        layoutInflater = (LayoutInflater)ctx.getSystemService (Context.LAYOUT_INFLATER_SERVICE);

        View item_view = layoutInflater.inflate(R.layout.screenshoots,container,false);

        ImageView imageView = (ImageView)item_view.findViewById(R.id.slider_image);

        imageView.setImageResource( image_resources[posisition]);
        container.addView(item_view);

        return item_view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((LinearLayout) object);
    }
}
