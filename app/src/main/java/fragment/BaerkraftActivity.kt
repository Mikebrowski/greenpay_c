package fragment

import ViewPager.ViewPageAdapter
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.greenpayremastered.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


//var viewPager: ViewPager? = null




class BaerkraftActivity : AppCompatActivity() {


    private lateinit var viewPageAdapter: ViewPageAdapter
    //private lateinit var viewPager: ViewPager2
    //private lateinit var tabLayoutB : TabLayout
    lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var navHostfragment : NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baerkraft)


        val tabLayoutB = findViewById<TabLayout>(R.id.TabLayoutB)
        val viewPager2xx = findViewById<ViewPager2>(R.id.ViewPager2B)



        //var adapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        viewPager2xx.adapter = FragmentAdapter(this )



        //TabLayoutMediator(tabLayoutB, viewPager2xx){tab,position -> tab.text ="tab ${position}"}.attach()
        TabLayoutMediator(tabLayoutB, viewPager2xx, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "Bærekraft"}
                    1 -> { tab.text = "Bærekraftmål"}
                }
            }).attach()
        //Navigation.findNavController(R.id.loggedActivityFragmentView)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    class FragmentAdapter(activity: BaerkraftActivity): FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> LesOmBkraft.newInstance()
                1 -> VisBaekraft.newInstance()
                else -> {LesOmBkraft.newInstance()}
            }
        }

    }
}