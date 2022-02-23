package fragment

import ViewPager.ViewPageAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.greenpayremastered.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BaerkraftFragment : Fragment() {

    private lateinit var viewPageAdapter: ViewPageAdapter
    private lateinit var viewPager: ViewPager2
    //


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = view?.findViewById<ViewPager2>(R.id.ViewPager2B)!!
        //var adapter = ViewPageAdapter(supportFragmentManger, lifecycle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_baerkraft, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById<ViewPager2>(R.id.ViewPager2B)
        //var adapter = ViewPageAdapter(frag, lifecycle)

    }

}

