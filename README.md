# EasyPagerAdapter [![](https://jitpack.io/v/VijayMakwana/EasyPagerAdapter.svg)](https://jitpack.io/#VijayMakwana/EasyPagerAdapter) [![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://opensource.org/licenses/Apache-2.0)

**Easiest android ViewPager adapter implementation, Don't write ViewPager adapter ever again**

* Based on [**Android Data Binding**](https://developer.android.com/topic/libraries/data-binding/index.html)

* Written in [**Kotlin**](http://kotlinlang.org)

* No need to write the ViewPager adapter

* No need to modify your model classes

* Supports multiple item view types

* Optional Callbacks/Listeners

* Efficient and easy API

* Tiny in size

* Support [**Kotlin**](http://kotlinlang.org) and [**Java**](https://www.java.com)

* Support AndroidX

  ​

  ## Setup

  ### Gradle

  Add this in your project level `build.gradle` file (**not** your module `build.gradle` file):

  ```gradle
  allprojects {
  	repositories {
          maven { url "https://jitpack.io" } // add this line
      }
  }
  ```
  Then, add the library to your module `build.gradle`

  ```
  // apply plugin: 'kotlin-kapt' // this line only for Kotlin projects
  
  android {
      ...
      dataBinding.enabled true 
  }
  
  dependencies {
  	implementation 'com.github.VijayMakwana:EasyPagerAdapter:1.0.2'
  }
  ```

  ## Usage

  Create your item layouts with `<layout>` as root:

  ```xml
  <layout xmlns:android="http://schemas.android.com/apk/res/android">
  
      <data>
          <variable name="item"
                    type="com.easypageradapter.easypageradaptersample.data.PersonDetail"/>
      </data>
      
      <TextView
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:text="@{item.name}"/>
          
  </layout>
  ```
  ```kotlin     
  // Kotlin
  mBinding.viewPager.setEasyPagerAdapter(mPageList, BR.item)
                  .map<ImageModel>(R.layout.item_view_pager_image)
                  .map<PersonDetail>(R.layout.item_view_pager_person_detail_item)
  ```
  ```java
  // Java
  new EasyPagerAdapter(mPageList, BR.item)
                  .map(ImageModel.class, R.layout.item_view_pager_image)
                  .map(PersonDetail.class, R.layout.item_view_pager_person_detail_item)
                  .into(mBinding.viewPager);
  ```

  ### If you want to attach callbacks or listeners then you can do like this

  ```kotlin
  // Kotlin sample
       mBinding.viewPager.setEasyPagerAdapter(mPageList)
                  .map<ImageModel>(R.layout.item_view_pager_image, BR.item)
                  .map<PersonDetail, ItemViewPagerPersonDetailBinding>
  			  (R.layout.item_view_pager_person_detail, BR.person)
                  { itemBind ->
                      itemBind.btnSubmit.setOnClickListener {
                       Toast.makeText(this@KotlinDemoActivity,
                       "Submit Button Clicked in the Person ${itemBind.person?.name} Page",
                             Toast.LENGTH_LONG).show()
                      }
                  }
  ```
  ```java
  // Java sample
  new EasyPagerAdapter(mPageList)
            .map(ImageModel.class, R.layout.item_view_pager_image, BR.item)
            .map(PersonDetail.class,
                R.layout.item_view_pager_person_detail,
                BR.person, new PagerCallBack<ItemViewPagerPersonDetailBinding>() {
                    @Override
                    public void onBind(final ItemViewPagerPersonDetailBinding itemBind) {
                       itemBind.btnSubmit.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                          Toast.makeText(JavaDemoActivity.this,
                           "Submit Button Clicked in the Person " + 
  					   itemBind.getPerson().getName() +
                             " Page", Toast.LENGTH_LONG).show();
                                      }
                                  });
                              }
                          })
                  .into(mBinding.viewPager);
  ```
  ### **set fragment pager adapter**

  ```kotlin
   mBinding.viewPager.setEasyFragmentPagerAdapter
   (supportFragmentManager, listOf(FragmentA(), FragmentB()))
  ```

  ### **set fragment state pager adapter**

  ```kotlin
   mBinding.viewPager.setEasyFragmentStatePagerAdapter
   (supportFragmentManager, listOf(FragmentA(), FragmentB()))
  ```
  **Check out the wiki for detailed documentation and usage examples.**

  [Wiki](https://github.com/VijayMakwana/EasyPagerAdapter/wiki)

  > Special thanks to [LastAdapter](https://github.com/nitrico/LastAdapter) by [@Miguel Ángel Moreno](https://github.com/nitrico) for inspiring this library
