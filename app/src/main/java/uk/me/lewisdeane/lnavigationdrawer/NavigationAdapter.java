package uk.me.lewisdeane.lnavigationdrawer;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lewis on 24/08/2014.
 */
public class NavigationAdapter extends ArrayAdapter<NavigationItem>{

    private Context mContext;
    private ArrayList<NavigationItem> mNavigationItems = new ArrayList<NavigationItem>();

    public NavigationAdapter(Context context, int resource,
                       ArrayList<NavigationItem> drawerItems) {

        super(context, resource, drawerItems);
        this.mContext = context;
        this.mNavigationItems = drawerItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        // Inflate layout.
        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_navigation, null);

        NavigationItem navigationItem = mNavigationItems.get(position);

        Typeface typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "Roboto-Medium.ttf");

        TextView itemView = (TextView) v.findViewById(R.id.item_navigation_title);
        ImageView imgView = (ImageView) v.findViewById(R.id.item_navigation_img);

        itemView.setText(navigationItem.getItem());
        imgView.setImageDrawable(getColouredDrawable(mContext.getResources().getDrawable(navigationItem.getImg()), navigationItem.getIsSelected()));

        itemView.setTextColor(navigationItem.getIsSelected() ? NavigationListView.mSelectedColour : mContext.getResources().getColor(R.color.dark_grey));

        itemView.setTypeface(typeface);

        return v;
    }

    private Drawable getColouredDrawable(Drawable _drawable, boolean _isSelected){
        int iColor = _isSelected ? NavigationListView.mSelectedColour : mContext.getResources().getColor(R.color.dark_grey);

        int red = (iColor & 0xFF0000) / 0xFFFF;
        int green = (iColor & 0xFF00) / 0xFF;
        int blue = iColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                , 0, 0, 0, 0, green
                , 0, 0, 0, 0, blue
                , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        _drawable.setColorFilter(colorFilter);

        return _drawable;
    }
}
