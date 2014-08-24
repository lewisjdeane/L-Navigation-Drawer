package uk.me.lewisdeane.lnavigationdrawer;

/**
 * Created by Lewis on 24/08/2014.
 */
public class NavigationItem {

    private int mImg = 0;
    private String mItem = "";
    private boolean mIsSelected = false;

    public NavigationItem(){

    }

    public NavigationItem(String _item){
        setItem(_item);
    }

    public NavigationItem(String _item, int _img){
        this(_item);
        setImg(_img);
    }

    public NavigationItem(String _item, int _img, boolean _isSelected){
        this(_item, _img);
        setIsSelected(_isSelected);
    }

    public NavigationItem setItem(String _item){
        mItem = _item;
        return this;
    }

    public NavigationItem setImg(int _img){
        mImg = _img;
        return this;
    }

    public NavigationItem setIsSelected(boolean _isSelected){
        mIsSelected = _isSelected;
        return this;
    }

    public String getItem(){
        return mItem;
    }

    public int getImg(){
        return mImg;
    }

    public boolean getIsSelected(){
        return mIsSelected;
    }
}
