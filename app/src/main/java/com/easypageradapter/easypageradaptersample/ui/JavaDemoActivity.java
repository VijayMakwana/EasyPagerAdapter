package com.easypageradapter.easypageradaptersample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easypageradapter.EasyPagerAdapter;
import com.easypageradapter.ItemType;
import com.easypageradapter.PagerCallBack;
import com.easypageradapter.easypageradaptersample.BR;
import com.easypageradapter.easypageradaptersample.R;
import com.easypageradapter.easypageradaptersample.data.ImageModel;
import com.easypageradapter.easypageradaptersample.data.PersonDetail;
import com.easypageradapter.easypageradaptersample.databinding.ActivityJavaDemoBinding;
import com.easypageradapter.easypageradaptersample.databinding.ItemViewPagerImageBinding;
import com.easypageradapter.easypageradaptersample.databinding.ItemViewPagerPersonDetailBinding;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class JavaDemoActivity extends AppCompatActivity {

    private ActivityJavaDemoBinding mBinding;

    private ArrayList<ImageModel> mImageList = new ArrayList<>();

    private ArrayList<Object> mPageList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_java_demo);
        initViews();
    }

    private void initViews() {
        mImageList.add(new ImageModel(R.drawable.image_1, "Android"));
        mImageList.add(new ImageModel(R.drawable.image_2, "Google Android"));
        mImageList.add(new ImageModel(R.drawable.image_3, "Happy Android"));
        mImageList.add(new ImageModel(R.drawable.image_4, "Noty Android"));

        mPageList.add(new ImageModel(R.drawable.image_1, "Android"));
        mPageList.add(new PersonDetail("ABD", "22", "Android Developer", R.drawable.image_1));
        mPageList.add(new ImageModel(R.drawable.image_2, "Google Android"));
        mPageList.add(new PersonDetail("XYZ", "23", "Android Developer", R.drawable.image_2));
        mPageList.add(new ImageModel(R.drawable.image_3, "Happy Android"));
        mPageList.add(new PersonDetail("PQR", "24", "Android Developer", R.drawable.image_3));


        /*Check all the cases on by one by uncommenting the code*/

        // Here BR.item is the Binding Resource variable which you declared in your layout

        //setSimplePager();

        // what if need on clicks
        //setPagerWithOnClicks();

        // data variable in different layout with same name
        //setPagerWithDifferentLayouts();

        // data variable in different layout with different name
        //setPagerWithDifferentDataVariableName();


        // with on clicks
        //setPagerWithDifferentLayoutWithOnClicks();

        // with ItemType
        setPagerWithItemTypes();

        /*
         * Other methods available
         * pageTitle()  if want to add page title
         * pageWidth() If want to change page width
         */
    }

    private void setPagerWithItemTypes() {
        ItemType<ItemViewPagerPersonDetailBinding> itemPersonType = new ItemType<>
                (R.layout.item_view_pager_person_detail, new PagerCallBack<ItemViewPagerPersonDetailBinding>() {
                    @Override
                    public void onBind(final ItemViewPagerPersonDetailBinding itemBind) {
                        itemBind.btnSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(JavaDemoActivity.this,
                                        "Submit Button Clicked in the Person " + itemBind.getPerson().getName() +
                                                " Page", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

        new EasyPagerAdapter(mPageList, BR.item)
                .map(ImageModel.class, R.layout.item_view_pager_image)
                .map(PersonDetail.class, itemPersonType, BR.person)
                .into(mBinding.viewPager);
    }

    private void setPagerWithDifferentLayoutWithOnClicks() {
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
                                                "Submit Button Clicked in the Person " + itemBind.getPerson().getName() +
                                                        " Page", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        })
                .into(mBinding.viewPager);
    }

    private void setPagerWithDifferentDataVariableName() {
        new EasyPagerAdapter(mPageList)
                .map(ImageModel.class, R.layout.item_view_pager_image, BR.item)
                .map(PersonDetail.class, R.layout.item_view_pager_person_detail, BR.person)
                .into(mBinding.viewPager);
    }

    private void setPagerWithDifferentLayouts() {
        new EasyPagerAdapter(mPageList, BR.item)
                .map(ImageModel.class, R.layout.item_view_pager_image)
                .map(PersonDetail.class, R.layout.item_view_pager_person_detail_item)
                .into(mBinding.viewPager);
    }

    private void setPagerWithOnClicks() {
        new EasyPagerAdapter(mImageList, BR.item)
                .map(ImageModel.class, R.layout.item_view_pager_image, new PagerCallBack<ItemViewPagerImageBinding>() {
                    @Override
                    public void onBind(final ItemViewPagerImageBinding itemBind) {
                        /*itemBind.textImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // here itemBind is your layout binding instance with the help of you can get all the layout components
                                Toast.makeText(JavaDemoActivity.this, itemBind.getItem().getName(), Toast.LENGTH_SHORT).show();
                            }
                        });*/
                    }
                })
                .into(mBinding.viewPager);
    }

    private void setSimplePager() {
        new EasyPagerAdapter(mImageList, BR.item)
                .map(ImageModel.class, R.layout.item_view_pager_image)
                .into(mBinding.viewPager);
    }
}
