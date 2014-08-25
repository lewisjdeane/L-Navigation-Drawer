#L Navigation Drawer
* * *

Library allowing you to easily replicate the new style of navigation drawer from Android L. This library consists a custom listview that you place in your navigation drawer and will handle the work for you.

* * *

##Set up (Android Studio)

Download the aar here: https://www.dropbox.com/s/x6rnd4z6ek4fijr/lnavigationdrawer.aar?dl=0

You can rename the aar and then place it in the libs directory of your project.

Go into your build.gradle and add the following:
```java

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'uk.me.lewisdeane.lnavigationdrawer:RENAMED_FILE_NAME_HERE@aar'
}

repositories{
    flatDir{
        dirs 'libs'
    }
}

```

# Usage

To use this library, use the tag
```java
<uk.me.lewisdeane.lnavigationdrawer.NavigationListView /* All normal attributes here */ />
```

In your java file you can now say

```java
NavigationListView navigationListView = (NavigationListView) findViewById(R.id.ID_OF_XML_LIST);
```

This list is populated with an object called NavigationItem which has 3 properties, title, image and whether it's selected or not.
It has 3 constructors as follows and each item is unselected by default:
```java
new NavigationItem(String title); // Just a piece of text
new NavigationItem(String title, int imgRes); // Image and text
new NavigationItem(String title, imgRes, boolean selected); // Image, text and ability to set selected.
```

To populate your list you can simply call a variety of methods:
```java
.addNavigationItem(String title, int imgRes, boolean selected); // Adds a new item with specified properties from parameters.
.addNavigationItem(NavigationItem navigationItem); // Adds a new item with specified properties.
.setItems(String[] items); // Set the items appearing in the list.
.setImages(int[] imgsRes); // Set the images to appear in the list.
```

Now we have a populated list we can use other methods such as:
```java
navigationListView.setSelectedColor(String hex); // Set hex colour as selected colour
navigationListView.setSelectedColor(int colorRes); // Set colour from resources as selected colour.
navigationListView.setSelectedItem(int position); // Sets the current selected item to one specified in parameter.
```

To handle click events simply call:
```java
navigationListView.setNavigationItemClickListener(new NavigationItemClickListener(){
    @Override
    public void onNavigationItemSelected(String item, ArrayList<NavigationItem> items, int position){
        // Do your stuff when an item is selected, do not worry about changing the colour or anything as it is handled for you.
    }
});
```

I will try and bring more features to this library but for now it should be fine, you can use this in your app for free, enjoy!
