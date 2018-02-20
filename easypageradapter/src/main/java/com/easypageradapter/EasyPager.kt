package com.easypageradapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.NullPointerException
import java.util.*
import android.support.v4.util.ArrayMap


/**
 * It has one function which provide binding class instance as arguments
 * @param B the type of a member in this PagerCallBack.
 */
interface PagerCallBack<in B> {
    /**
     * This function provide binding instance
     *
     * @property itemBind type of B
     */
    fun onBind(itemBind: B) {}
}

/**
 * This is extension function of view pager which set the view pager adapter
 * It takes two arguments [pageModelList] and [itemId]
 *
 * @property pageModelList number of pages in view pager is equal to the [pageModelList] size
 *
 * @property itemId Binding Resource id of the data variable which is in item layout of view pager
 *
 * @return instance of EasyPagerAdapter class
 */
fun ViewPager.setEasyPagerAdapter(pageModelList: List<Any>, itemId: Int): EasyPagerAdapter {
    val easyPager = EasyPagerAdapter(pageModelList = pageModelList, itemId = itemId)
    this.adapter = easyPager
    return easyPager
}


/**
 * This is extension function of view pager which set the view pager adapter
 * It takes one arguments [pageModelList]
 *
 * @property pageModelList number of pages in view pager is equal to the [pageModelList] size
 *
 * @return instance of EasyPagerAdapter class
 */
fun ViewPager.setEasyPagerAdapter(pageModelList: List<Any>): EasyPagerAdapter {
    val easyPager = EasyPagerAdapter(pageModelList = pageModelList)
    this.adapter = easyPager
    return easyPager
}

/**
 * This class create item type for your view pager
 * with the help of item type single item can be with multiple pages
 *
 * @param T the type of a member in this ItemType.
 * @constructor create item type
 */
class ItemType<T>(layout: Int) {
    var mLayout = layout
    var mFunc: (itemBind: T) -> Unit = {}
    var mCallBack: PagerCallBack<T>? = null

    /**
     * @property layout item layout of item type
     *
     * @property onBind lambda function which provides binding instance as argument
     */
    constructor(layout: Int, onBind: (itemBind: T) -> Unit = {}) : this(layout) {
        mFunc = onBind
    }

    /**
     * @property layout item layout of item type
     *
     * @property callBack instance of PagerCallBack
     */
    constructor(layout: Int, callBack: PagerCallBack<T>? = null) : this(layout) {
        mCallBack = callBack
    }
}


/**
 * This class extends PagerAdapter class and also have other utility methods
 *
 * @property pageModelList it contains the all model classes of pages in view pager
 *
 * @constructor create instance of EasyPagerAdapter
 *
 */
class EasyPagerAdapter(private var pageModelList: List<Any>) : PagerAdapter() {
    var mItemId: Int = 0

    constructor(pageModelList: List<Any>, itemId: Int) : this(pageModelList) {
        mItemId = itemId
    }

    private var mLayout: Int? = null
    private var titleList: ArrayList<String> = arrayListOf()
    private var mPageWidth: Float = 1.0F
    var mItemIdsMap = ArrayMap<String, Int>()
    var mLayoutModelMap = ArrayMap<String, Int>()
    var mOnBindFuncMap = ArrayMap<String, (itemBind: ViewDataBinding) -> Unit>()
    private var mOnBindListenerMap = ArrayMap<String, PagerCallBack<ViewDataBinding>>()

    /**
     * it map layout to the model in [pageModelList]
     *
     * @param T type of Model class
     * @param B type of Binding class
     *
     * @property layout item layout of view pager
     *
     * @property itemId Binding Resource id of the data variable which is in item layout of view pager
     *
     * @property function lambda function which provides binding instance as argument
     *
     * @return instance of EasyPagerAdapter
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any, reified B : Any> map(mLayout: Int, itemId: Int = mItemId, noinline function: (itemBind: B) -> Unit = {}): EasyPagerAdapter {
        mLayoutModelMap[T::class.java.canonicalName] = mLayout
        mOnBindFuncMap[T::class.java.canonicalName] = function as (itemBind: ViewDataBinding) -> Unit
        mItemIdsMap[T::class.java.canonicalName] = itemId
        return this
    }

    /**
     * it map layout to the model in [pageModelList]
     *
     * @param T type of Model class
     *
     * @property itemType instance of ItemType class
     *
     * @property itemId Binding Resource id of the data variable which is in item layout of view pager
     *
     * @return instance of EasyPagerAdapter
     */
    @Suppress("UNCHECKED_CAST")
    @JvmOverloads
    inline fun <reified T : Any> map(itemType: ItemType<*>, itemId: Int = mItemId): EasyPagerAdapter {
        mLayoutModelMap[T::class.java.canonicalName] = itemType.mLayout
        mOnBindFuncMap[T::class.java.canonicalName] = itemType.mFunc as (itemBind: ViewDataBinding) -> Unit
        mItemIdsMap[T::class.java.canonicalName] = itemId
        return this
    }

    /**
     * it map layout to the model in [pageModelList]
     *
     * @param T type of Model class
     * @param B type of ViewDataBinding class
     *
     * @property clazz type of instance of T
     *
     * @property layout item layout of view pager
     *
     * @property itemId Binding Resource id of the data variable which is in item layout of view pager
     *
     * @property callBack instance of PagerCallBack
     *
     * @return instance of EasyPagerAdapter
     */
    @Suppress("UNCHECKED_CAST")
    @JvmOverloads
    fun <T : Any, B : ViewDataBinding> map(clazz: Class<T>, layout: Int, itemId: Int = mItemId, callBack: PagerCallBack<B>): EasyPagerAdapter {
        mLayoutModelMap[clazz.canonicalName] = layout
        mOnBindListenerMap[clazz.canonicalName] = callBack as PagerCallBack<ViewDataBinding>
        mItemIdsMap[clazz.canonicalName] = itemId
        return this
    }

    /**
     * it map layout to the model in [pageModelList]
     *
     * @param T type of Model class
     * @param B type of Binding class
     *
     * @property clazzT type of instance of T
     *
     * @property itemType instance of ItemType class
     *
     * @property itemId Binding Resource id of the data variable which is in item layout of view pager
     *
     * @return instance of EasyPagerAdapter
     */
    @Suppress("UNCHECKED_CAST")
    @JvmOverloads
    fun <T : Any, B : Any> map(clazzT: Class<T>, itemType: ItemType<B>, itemId: Int = mItemId): EasyPagerAdapter {
        mLayoutModelMap[clazzT.canonicalName] = itemType.mLayout
        mOnBindListenerMap[clazzT.canonicalName] = itemType.mCallBack as PagerCallBack<ViewDataBinding>
        mItemIdsMap[clazzT.canonicalName] = itemId
        return this
    }

    /**
     * it map layout to the model in [pageModelList]
     *
     * @param T type of Model class
     *
     * @property layout item layout of item type
     *
     * @property itemId Binding Resource id of the data variable which is in item layout of view pager
     *
     * @return instance of EasyPagerAdapter
     */
    @JvmName("ForKotlin")
    inline fun <reified T : Any> map(layout: Int, itemId: Int = mItemId): EasyPagerAdapter {
        mLayoutModelMap[T::class.java.canonicalName] = layout
        mItemIdsMap[T::class.java.canonicalName] = itemId
        return this
    }

    /**
     * it map layout to the model in [pageModelList]
     *
     * @param T type of Model class
     *
     * @property clazz type of instance of T
     *
     * @property layout item layout of item type
     *
     * @property itemId Binding Resource id of the data variable which is in item layout of view pager
     *
     * @return instance of EasyPagerAdapter
     */
    @JvmOverloads
    fun <T : Any> map(clazz: Class<T>, layout: Int, itemId: Int = mItemId): EasyPagerAdapter {
        mLayoutModelMap[clazz.canonicalName] = layout
        mItemIdsMap[clazz.canonicalName] = itemId
        return this
    }

    /**
     * it map layout to view pager
     *
     * @property layout item layout of item type
     *
     * @return instance of EasyPagerAdapter
     */
    fun map(layout: Int): EasyPagerAdapter {
        mLayout = layout
        return this
    }

    /**
     * it takes [pageTitleList] and set the title to appropriate page
     *
     * @property pageTitleList list of title
     *
     * @return instance of EasyPagerAdapter
     */
    fun pageTitle(pageTitleList: ArrayList<String>): EasyPagerAdapter {
        titleList = pageTitleList
        return this
    }

    /**
     * it takes [pageWidth] and set the page width the view pager pages
     *
     * @property pageWidth float default is 1f
     *
     * @return instance of EasyPagerAdapter
     */
    fun pageWidth(pageWidth: Float): EasyPagerAdapter {
        mPageWidth = pageWidth
        return this
    }

    /**
     * it takes [viewPager] and set adapter to it
     *
     * @property viewPager instance of ViewPager
     */
    fun into(viewPager: ViewPager) {
        viewPager.adapter = this
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titleList.size == 0 || titleList[position].isEmpty()) {
            ""
        } else {
            titleList[position]
        }
    }

    override fun getPageWidth(position: Int): Float {
        return mPageWidth
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        mLayout?.let {
            val binding: ViewDataBinding? = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(container.context),
                    it, container, false)
            mItemIdsMap[pageModelList[position]::class.java.canonicalName]?.let {
                binding?.setVariable(it, pageModelList[position])
            }
            binding?.executePendingBindings()
            container.addView(binding?.root)
            return binding?.root!!
        }

        val layoutForPos = mLayoutModelMap[pageModelList[position]::class.java.canonicalName]
                ?: throw NullPointerException("non null type map is null")
        val binding: ViewDataBinding? = layoutForPos.let {
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(container.context),
                    it, container, false)
        }
        mItemIdsMap[pageModelList[position]::class.java.canonicalName]?.let {
            binding?.setVariable(it, pageModelList[position])
        }
        binding?.executePendingBindings()
        container.addView(binding?.root)
        binding?.let {
            mOnBindListenerMap[pageModelList[position]::class.java.canonicalName]?.onBind(it)
            mOnBindFuncMap[pageModelList[position]::class.java.canonicalName]?.let { it1 -> it1(it) }
        }
        return binding?.root!!
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any as ViewGroup
    }

    override fun getCount(): Int {
        return pageModelList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(any as ViewGroup)
    }
}

/**
 * This is extension function of view pager which set the view pager to fragment pager adapter
 * It takes two arguments [supportFragmentManager] and [fragmentList]
 *
 * @property supportFragmentManager instance of FragmentManager
 *
 * @property fragmentList list of fragment which are displayed as pages in view pager
 *
 * @return instance of EasyFragmentPagerAdapter class
 */
fun ViewPager.setEasyFragmentPagerAdapter(supportFragmentManager: FragmentManager, fragmentList: List<Fragment>): EasyFragmentPagerAdapter {
    val easyFragmentPager = EasyFragmentPagerAdapter(supportFragmentManager, fragmentList)
    this.adapter = easyFragmentPager
    return easyFragmentPager
}

/**
 * This is extension function of view pager which set the view pager to fragment state pager adapter
 * It takes two arguments [supportFragmentManager] and [fragmentList]
 *
 * @property supportFragmentManager instance of FragmentManager
 *
 * @property fragmentList list of fragment which are displayed as pages in view pager
 *
 * @return instance of EasyFragmentStatePagerAdapter class
 */
fun ViewPager.setEasyFragmentStatePagerAdapter(supportFragmentManager: FragmentManager, fragmentList: List<Fragment>): EasyFragmentStatePagerAdapter {
    val easyFragmentStatePager = EasyFragmentStatePagerAdapter(supportFragmentManager, fragmentList)
    this.adapter = easyFragmentStatePager

    return easyFragmentStatePager
}

/**
 * This class extends FragmentPagerAdapter class and also have other utility methods
 * @property supportFragmentManager instance of FragmentManager
 *
 * @property fragmentList list of fragment which are displayed as pages in view pager
 *
 * @constructor create instance of EasyFragmentPagerAdapter
 */
class EasyFragmentPagerAdapter(private val supportFragmentManager: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(supportFragmentManager) {
    private val mFragmentList: List<Fragment> = fragmentList
    private var titleList: ArrayList<String> = arrayListOf()
    private var mPageWidth: Float = 1.0F

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return if (titleList.size == 0 || titleList[position].isEmpty()) {
            ""
        } else {
            titleList[position]
        }
    }

    override fun getPageWidth(position: Int): Float {
        return mPageWidth
    }

    /**
     * it takes [pageWidth] and set the page width the view pager pages
     *
     * @property pageWidth float default is 1f
     *
     * @return instance of EasyFragmentPagerAdapter
     */
    fun pageWidth(pageWidth: Float): EasyFragmentPagerAdapter {
        mPageWidth = pageWidth
        return this
    }

    /**
     * it takes [pageTitleList] and set the title to appropriate page
     *
     * @property pageTitleList list of title
     *
     * @return instance of EasyPagerAdapter
     */
    fun pageTitle(pageTitleList: ArrayList<String>): EasyFragmentPagerAdapter {
        titleList = pageTitleList
        return this
    }

    /**
     * it takes [viewPager] and set adapter to it
     *
     * @property viewPager instance of ViewPager
     */
    fun into(viewPager: ViewPager) {
        viewPager.adapter = this
    }
}

/**
 * This class extends FragmentStatePagerAdapter class and also have other utility methods
 * @property supportFragmentManager instance of FragmentManager
 *
 * @property fragmentList list of fragment which are displayed as pages in view pager
 *
 * @constructor create instance of EasyFragmentStatePagerAdapter
 */
class EasyFragmentStatePagerAdapter(private val supportFragmentManager: FragmentManager, private val fragmentList: List<Fragment>) : FragmentStatePagerAdapter(supportFragmentManager) {
    private val mFragmentList: List<Fragment> = fragmentList
    private var titleList: ArrayList<String> = arrayListOf()
    private var mPageWidth: Float = 1.0F

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titleList.size == 0 || titleList[position].isEmpty()) {
            ""
        } else {
            titleList[position]
        }
    }

    override fun getPageWidth(position: Int): Float {
        return mPageWidth
    }

    /**
     * it takes [pageWidth] and set the page width the view pager pages
     *
     * @property pageWidth float default is 1f
     *
     * @return instance of EasyFragmentStatePagerAdapter
     */
    fun pageWidth(pageWidth: Float): EasyFragmentStatePagerAdapter {
        mPageWidth = pageWidth
        return this
    }

    /**
     * it takes [pageTitleList] and set the title to appropriate page
     *
     * @property pageTitleList list of title
     *
     * @return instance of EasyPagerAdapter
     */
    fun pageTitle(pageTitleList: ArrayList<String>): EasyFragmentStatePagerAdapter {
        titleList = pageTitleList
        return this
    }

    /**
     * it takes [viewPager] and set adapter to it
     *
     * @property viewPager instance of ViewPager
     */
    fun into(viewPager: ViewPager) {
        viewPager.adapter = this
    }
}