package com.utc.single.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

/**
 * Cài đặt các thiết lập mặc định cho một Fragment
 * @author Donly Conan
 * @since 04/02/2021
 */
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId), Initialzation, DirectInteraction,
    SwitchFragment {

    private data class Result(var resultCode: Int, var bundle: Bundle?)

    private var result: Result? = null
    private var interaction: DirectInteraction? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Khởi tạo các cài đặt ban đầu cho fragment
        initialize(savedInstanceState)
        // Chạy trình đăng ký sự kiện
        register(savedInstanceState)
    }


    override fun onNewBundle(newBundle: Bundle?) {
        super.onNewBundle(newBundle)
        arguments?.putAll(newBundle)
    }


    override fun <T : Fragment> startFragment(@IdRes frameId: Int, box: Box<T>): T {
        val fragment = box.cls.newInstance().apply {
            arguments = box.bundle
            (this as? BaseFragment)?.setDirectInteraction(this@BaseFragment)
        }
        startNewFragment(frameId, fragment, box.getClassName())
        return fragment
    }


    override fun <T : Fragment> startFragmentForResult(
        @IdRes frameId: Int,
        requestCode: Int,
        box: Box<T>
    ): T {
        // Tạo instance cho fragment
        val fragment = box.cls.newInstance().apply {
            arguments = box.bundle
            (this as? BaseFragment)?.setDirectInteraction(this@BaseFragment)
            setTargetFragment(this@BaseFragment, requestCode)
        }
        startNewFragment(frameId, fragment, box.getClassName())
        return fragment
    }


    /**
     *  Xử lý các hành động liên quan tới flag
     *  Các cờ có thể có là: SINGLE_TOP, NEW_TASK, BRING_TO_FONT, SEND_TO_BACK
     *  @param frameId
     *  @param fragment
     *  @param box
     *  @return Unit
     */
    private fun <T : Fragment> handleFlag(@IdRes frameId: Int, fragment: T, box: Box<T>) {
        val sfm = requireActivity().supportFragmentManager
        var prefragment = sfm.findFragmentById(frameId) as? BaseFragment
        val target = sfm.findFragmentByTag(box.getClassName()) as? BaseFragment

        when (box.flag) {
            /**
             * Nếu 1 fragment đang được thực thi và lưu lại trạng thái ở backstack
             * thì nó sẽ được đưa nên đầu stack và hàm onNewBundle sẽ được gọi lại
             * Giả định: A > B > C > D
             * Show:     A
             * Kết quả:  B > C > D > A
             */
            Box.FLAG_BRING_TO_FONT -> {
                if (target == null) {
                    // Bắt đầu 1 fragment mới khi target null
                    startNewFragment(frameId, fragment, box.getClassName())
                } else {
                    prefragment = target
                    sfm.commit {
                        target.onNewBundle(box.bundle)
                        show(target)
                    }
                }
            }
            /**
             * Đưa một fragment xuống cuối stack và hiển thị fragment phía sau nó đồng thời gọi
             * tới hàm newBundle của fragment đó
             * Giả định: A > B > C > D
             * Hide:     D
             * Kết quả:  D > A > B > C
             */
            Box.FLAG_SEND_TO_BACK -> {
                if (prefragment != null) {
                    sfm.commit {
                        hide(prefragment!!)
                        prefragment = sfm.findFragmentById(frameId) as BaseFragment
                        prefragment!!.onNewBundle(box.bundle)
                    }
                } else {
                    startNewFragment(frameId, fragment, box.getClassName())
                }
            }

            /**
             * Đưa một fragment xuống cuối stack và hiển thị fragment phía sau nó đồng thời gọi
             * tới hàm newBundle của fragment đó
             * Giả định: A > B > C > D
             * Start:    B
             * Kết quả:  A > B
             */
            Box.FLAG_SINGLE_TASK -> {
                if (target == null) {
                    startNewFragment(frameId, fragment, box.getClassName())
                } else {
                    while (target != prefragment && sfm.popBackStackImmediate()) {
                        prefragment = sfm.findFragmentById(frameId) as BaseFragment
                    }
                    prefragment?.onNewBundle(box.bundle)
                }
            }

            /**
             * Đưa một fragment xuống cuối stack và hiển thị fragment phía sau nó đồng thời gọi
             * tới hàm newBundle của fragment đó
             * Start:    D
             * Giả định: A > B > C > D -> A > B > C > D
             * Giả định: A > B > D > C -> A > B > D > C > D
             */
            Box.FLAG_SINGLE_TOP -> {
                if (prefragment != null && box.cls == prefragment!!::class.java) {
                    prefragment!!.onNewBundle(box.bundle)
                } else {
                    startNewFragment(frameId, fragment, box.getClassName())
                }
            }
            /**
             * Ở cờ mặc định sẽ khởi tạo một instance của fragment và thêm vào stack
             * Start:    D
             * Giả định: A > B > C > D
             * Kết quả:  A > B > C > D > D
             */
            else -> {
                startNewFragment(frameId, fragment, box.getClassName())
            }

        }
    }

    private fun <T : Fragment> startNewFragment(@IdRes frameId: Int, fragment: T, tag: String) {
        requireActivity().supportFragmentManager.commit {
            add(frameId, fragment, tag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }


    override fun setResult(resultCode: Int, bundle: Bundle?) {
        super.setResult(resultCode, bundle)
        result = Result(resultCode, bundle)
    }

    override fun finish() {
        val sfm = requireActivity().supportFragmentManager
        if (result != null && targetFragment?.isDetached == false && targetFragment is BaseFragment) {
            val base = targetFragment as BaseFragment
            result?.apply {
                base.onFragmentResult(targetRequestCode, resultCode, bundle)
            }
        }
        sfm.popBackStack()
    }

    override fun send(code: Int, bundle: Bundle?) {
        super.send(code, bundle)
        this.interaction?.receive(code, bundle)
    }


    fun setDirectInteraction(interaction: DirectInteraction?) {
        this.interaction = interaction
    }
}