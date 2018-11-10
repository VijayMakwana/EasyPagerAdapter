package com.easypageradapter.easypageradaptersample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easypageradapter.EasyPagerAdapter
import com.easypageradapter.ItemType
import com.easypageradapter.easypageradaptersample.BR
import com.easypageradapter.easypageradaptersample.R
import com.easypageradapter.easypageradaptersample.data.ImageModel
import com.easypageradapter.easypageradaptersample.data.PersonDetail
import com.easypageradapter.easypageradaptersample.databinding.ActivityKotlinDemoBinding
import com.easypageradapter.easypageradaptersample.databinding.ItemViewPagerImageBinding
import com.easypageradapter.easypageradaptersample.databinding.ItemViewPagerPersonDetailBinding
import com.easypageradapter.setEasyPagerAdapter

class KotlinDemoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityKotlinDemoBinding

    private val mImageList = listOf<ImageModel>(
            ImageModel(R.drawable.image_1, "Android"),
            ImageModel(R.drawable.image_2, "Google Android"),
            ImageModel(R.drawable.image_3, "Happy Android"),
            ImageModel(R.drawable.image_4, "Noty Android"))

    private val mPageList = listOf<Any>(
            ImageModel(
                    image = R.drawable.image_1,
                    name = "Android"),
            PersonDetail(
                    name = "ABD",
                    age = "22",
                    profilePic = R.drawable.image_1,
                    designation = "Android Developer"),
            ImageModel(
                    image = R.drawable.image_2,
                    name = "Google Android"),
            PersonDetail(
                    name = "XYZ",
                    age = "23",
                    profilePic = R.drawable.image_2,
                    designation = "Android Developer"),
            ImageModel(
                    image = R.drawable.image_3,
                    name = "Happy Android"),
            PersonDetail(
                    name = "PQR",
                    age = "24",
                    profilePic = R.drawable.image_3,
                    designation = "Android Developer"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin_demo)
        initViews()
    }

    private fun initViews() {
        // Check all the cases by calling ay of the function

        //Here BR.item is the Binding Resource variable which you declared in your layout

        //with extension function
        /*setPagerWithExtensionFunction()*/

        // without extension function
        /*setPagerWithoutExtensionFunction()*/

        // what if need on clicks
        /*setPagerWithOnClicks()*/

        // data variable in different layout with same name
        /*setPagerWithDifferentLayouts()*/

        // data variable in different layout with different name
        /*setPagerWithDifferentDataVariableName()*/

        // with on clicks
        /*setPagerWithDifferentLayoutWithOnClicks()*/

        // with ItemType
        setPagerWithItemTypes()

        /**
         * Other methods available
         * pageTitle()  if want to add page title
         * pageWidth() If want to change page width
         */
    }

    private fun setPagerWithItemTypes() {
        val itemPersonType = ItemType<ItemViewPagerPersonDetailBinding>(R.layout.item_view_pager_person_detail) { itemBind ->
            itemBind.btnSubmit.setOnClickListener {
                Toast.makeText(this@KotlinDemoActivity,
                        "Submit Button Clicked in the Person ${itemBind.person?.name} Page",
                        Toast.LENGTH_LONG).show()
            }
        }

        mBinding.viewPager.setEasyPagerAdapter(mPageList, BR.item)
                .map<ImageModel>(R.layout.item_view_pager_image)
                .map<PersonDetail>(itemPersonType, BR.person)
    }

    private fun setPagerWithDifferentLayoutWithOnClicks() {
        mBinding.viewPager.setEasyPagerAdapter(mPageList)
                .map<ImageModel>(R.layout.item_view_pager_image, BR.item)
                .map<PersonDetail, ItemViewPagerPersonDetailBinding>(R.layout.item_view_pager_person_detail, BR.person)
                { itemBind ->
                    itemBind.btnSubmit.setOnClickListener {
                        Toast.makeText(this@KotlinDemoActivity,
                                "Submit Button Clicked in the Person ${itemBind.person?.name} Page",
                                Toast.LENGTH_LONG).show()
                    }
                }
    }

    private fun setPagerWithDifferentDataVariableName() {
        mBinding.viewPager.setEasyPagerAdapter(mPageList)
                .map<ImageModel>(R.layout.item_view_pager_image, BR.item)
                .map<PersonDetail>(R.layout.item_view_pager_person_detail, BR.person)
    }

    private fun setPagerWithDifferentLayouts() {
        mBinding.viewPager.setEasyPagerAdapter(mPageList, BR.item)
                .map<ImageModel>(R.layout.item_view_pager_image)
                .map<PersonDetail>(R.layout.item_view_pager_person_detail_item)
    }

    private fun setPagerWithOnClicks() {
        mBinding.viewPager.setEasyPagerAdapter(mImageList, BR.item)
                .map<ImageModel, ItemViewPagerImageBinding>(R.layout.item_view_pager_image) { itemBind ->
                    // here itemBind is your layout binding instance with the help of you can get all the layout components
                    itemBind.textImage.setOnClickListener {
                        Toast.makeText(this@KotlinDemoActivity, itemBind.item?.name, Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun setPagerWithoutExtensionFunction() {
        EasyPagerAdapter(mImageList, BR.item)
                .map<ImageModel>(R.layout.item_view_pager_image)
                .into(mBinding.viewPager)
    }

    private fun setPagerWithExtensionFunction() {
        mBinding.viewPager.setEasyPagerAdapter(mImageList, BR.item)
                .map<ImageModel>(R.layout.item_view_pager_image)
    }
}