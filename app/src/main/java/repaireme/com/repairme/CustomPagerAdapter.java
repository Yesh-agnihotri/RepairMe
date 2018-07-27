package repaireme.com.repairme;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by YESH on 10-03-2018.
 */

class CustomPagerAdapter extends PagerAdapter {

    int[] mResources = {
            R.drawable.fast,
            R.drawable.service,
            R.drawable.disountcoupon,

    };
    String[] text={
            "1",
            "2",
            "3"
    };

    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context)
    {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //TextView textView=(TextView)itemView.findViewById(R.id.myImageViewText);
        imageView.setImageResource(mResources[position]);
        //textView.setText(text[position]);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}


