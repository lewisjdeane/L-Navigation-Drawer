package uk.me.lewisdeane.lnavigationdrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lewis on 24/08/2014.
 */
public class NavigationListView extends ListView {

    private Context mContext;
    private ArrayList<NavigationItem> mNavigationItems =  new ArrayList<NavigationItem>();
    private NavigationItemClickListener mCallbacks;
    public NavigationAdapter mNavigationAdapter;
    public static int mSelectedColour;

    public NavigationListView(Context _context){
        this(_context, null, 0);
    }

    public NavigationListView(Context _context, AttributeSet _attrs){
        this(_context, _attrs, 0);
    }

    public NavigationListView(Context _context, AttributeSet _attrs, int _defStyle){
        super(_context, _attrs, _defStyle);
        mContext = _context;
        init(_attrs);
    }

    private void init(AttributeSet _attrs){
        mNavigationAdapter = new NavigationAdapter(mContext, R.layout.item_navigation, mNavigationItems);
        mSelectedColour = getResources().getColor(R.color.blue);
        setAdapter(mNavigationAdapter);

        try{
            mCallbacks = (NavigationItemClickListener) mContext;
        } catch(ClassCastException e){
            Log.w("L Navigation Drawer", mContext.getClass() + " should implement NavigationListView.NavigationItemClickListener");
        }

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mCallbacks != null){
                    mCallbacks.onNavigationItemSelected(mNavigationItems.get(i).getItem(), mNavigationItems, i);
                }

                for(NavigationItem ni : mNavigationItems)
                    ni.setIsSelected(false);

                mNavigationItems.get(i).setIsSelected(true);

                mNavigationAdapter.notifyDataSetChanged();
            }
        });
    }

    public NavigationListView setNavigationItemClickListener(NavigationItemClickListener _navigationItemClickListener){
        this.mCallbacks = _navigationItemClickListener;
        return this;
    }

    public interface NavigationItemClickListener{
        public void onNavigationItemSelected(String item, ArrayList<NavigationItem> items, int position);
    }

    public NavigationListView setItems(String[] _items){
        for(String item : _items)
            mNavigationItems.add(new NavigationItem(item));

        if(mNavigationAdapter != null)
            mNavigationAdapter.notifyDataSetChanged();
        return this;
    }

    public NavigationListView setImages(int[] _res){
        for(int i = 0; i < _res.length; i++){
            try {
                mNavigationItems.get(i).setImg(_res[i]);
            } catch(Exception e){
                mNavigationItems.add(new NavigationItem("", _res[i]));
            }
        }
        if(mNavigationAdapter != null)
            mNavigationAdapter.notifyDataSetChanged();
        return this;
    }

    public NavigationListView addNavigationItem(NavigationItem _navigationItem){
        mNavigationItems.add(_navigationItem);
        mNavigationAdapter.notifyDataSetChanged();
        return this;
    }

    public NavigationListView addNavigationItem(String _title, int _res, boolean _isSelected){
        mNavigationItems.add(new NavigationItem(_title, _res, _isSelected));
        mNavigationAdapter.notifyDataSetChanged();
        return this;
    }

    public NavigationListView setSelectedItem(int _position){
        try{
            for(NavigationItem navigationItem : mNavigationItems)
                navigationItem.setIsSelected(false);
            mNavigationItems.get(_position).setIsSelected(true);
            mNavigationAdapter.notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e){
            Log.e("L Navigation Drawer", "Index specified in setSelectedItem doesn't exist in the list.");
        }
        return this;
    }

    public NavigationListView setSelectedColor(String _colour){
        try{
            mSelectedColour = Color.parseColor(_colour);
        } catch(Exception e){
            Log.e("L Navigation Drawer", "Invalid hex code " + _colour);
        }

        if(mNavigationAdapter != null)
            mNavigationAdapter.notifyDataSetChanged();
        return this;
    }

    public NavigationListView setSelectedColor(int _colour){
        try{
            mSelectedColour = mContext.getResources().getColor(_colour);
        } catch(Exception e){
            Log.e("L Navigation Drawer", "Invalid color resource " + _colour);
        }

        if(mNavigationAdapter != null)
            mNavigationAdapter.notifyDataSetChanged();
        return this;
    }
}
