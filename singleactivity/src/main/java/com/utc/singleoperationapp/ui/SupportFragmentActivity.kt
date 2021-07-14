package com.utc.singleoperationapp.ui


import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit


/**
 * Lớp quản lý hoạt động của một activity
 * @author Donly Conan
 * @since 03/02/2001
 */
abstract class SupportFragmentActivity(layoutId: Int) : AppCompatActivity(layoutId), Initialzation {

    private lateinit var firstName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kiểm tra và đăng ký sự kiện nếu sự kiện chưa được đăng ký
        if (checkOrRegisterPermission()) {
            runOnIfHasPermission(savedInstanceState)
        }


        // Các khởi tạo truyền thống
        initialize(savedInstanceState)

        // Đăng ký các sự kiên liên quan tới ViewModel, sự kiện
        register(savedInstanceState)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initialize(intent?.extras)
    }


    open fun <T : BaseFragment> initFragment(@IdRes frameId: Int, cls: Class<T>): BaseFragment? {
        if (supportFragmentManager.backStackEntryCount == 0) {
            firstName = cls.name
            return cls.newInstance().apply {
                supportFragmentManager.beginTransaction()
                    .add(frameId, this, firstName)
                    .setReorderingAllowed(true)
                    .addToBackStack(firstName)
                    .commit()
            }
        } else {
            return supportFragmentManager.findFragmentByTag(firstName) as? BaseFragment
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            if (BaseFragment.flagsUsed == Box.FLAG_BRING_TO_FRONT || BaseFragment.flagsUsed == Box.FLAG_HIDE) {
                supportFragmentManager.commit { showAll() }
            }
            supportFragmentManager.popBackStack()
        } else {
            super.finish()
        }
    }

    // Hiển thị tất cả fragment đang bị ẩn
    fun FragmentTransaction.showAll() {
        val sfm = supportFragmentManager
        sfm.commit {
            for (item in sfm.fragments) {
                show(item)
            }
        }
    }

    /**********************************************************************************************/


    /**
     * Khởi chạy các hoạt động khi có đủ quyền truy cập
     * @return Unit
     */
    open fun runOnIfHasPermission(savedInstanceState: Bundle? = null) {}


    /**
     * Kiểm tra các quyền cơ bản sau khi khởi tạo
     * @return Unit
     */
    open fun checkOrRegisterPermission(): Boolean = false

}

