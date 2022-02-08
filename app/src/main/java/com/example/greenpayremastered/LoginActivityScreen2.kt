package com.example.greenpayremastered

import com.google.firebase.auth.FirebaseAuth
import models.Initiatives
import models.InitiativeData
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.android.material.navigation.NavigationView
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView


import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
//import androidx.navigation.Navi

import androidx.navigation.ui.*
import androidx.navigation.ui.AppBarConfiguration


/*
implementation "androidx.navigation:navigation-fragment:$nav_version"
implementation "androidx.navigation:navigation-ui:$nav_version"

*/

import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class LoginActivityScreen2 : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private val recyclerView: RecyclerView? = null
    private val initiatives = ArrayList<Initiatives>()
    private val initiativesDatList: List<InitiativeData> = ArrayList()

    //private RecycleAdapter recycleAdapter = new RecycleAdapter(initiatives, this, this, initiativesDatList);
    // NEEDS THIS BACK WHEN IT WAS STACTIC RECYCLEVIEW
    // public class LoginActivityScreen extends AppCompatActivity implements RecycleAdapter.IniClickInterface
    private var searchArea: SearchView? = null
    private val logoutBtn: Button? = null
    private val loginText: TextView? = null
    private val firstButton: Button? = null
    private val secondButton: Button? = null





    private var mDatabase: DatabaseReference? = null
    var actionBar: ActionBar? = null
    var toggleActionDrawer: ActionBarDrawerToggle? = null
    lateinit var navigationViewTop: NavigationView



    //lateinit var binding: LoginActivityScreen

    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var bottomNav:BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loggetscreen)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.loggedActivityFragmentView) as NavHostFragment
        var navController = navHostFragment.navController
        //OR
        //navController= findNavController(R.id.loggedActivityFragmentView)

        //NEW WAY TO SETUP BOTTOM NAVIGATION
        bottomNav = findViewById(R.id.bottom_navigation)
        navigationViewTop = findViewById(R.id.navigation_view)
        bottomNav.setupWithNavController(navController)


        drawerLayout = findViewById(R.id.drawer_layout_1)


        //appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        navController = findNavController(R.id.loggedActivityFragmentView)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment2,R.id.firstFragment2,R.id.highScoreFragment))

        setupActionBarWithNavController(navController,drawerLayout)
        navigationViewTop.setupWithNavController(navController)

        //NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        //NavigationUI.navigateUp(navController, appBarConfiguration)

        //NavigationUI.setupWithNavController(navigationViewTop,navController)
        //NavigationUI.setupWithNavController(navigationViewTop,navController)

        /*
        val finalHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.loggedActivityFragmentView, finalHost)
            .setPrimaryNavigationFragment(finalHost) // equivalent to app:defaultNavHost="true"
            .commit()
        */
        //Navigation.findNavController(view).navigate(R.id.view_transition)


        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser


        mDatabase = FirebaseDatabase.getInstance().reference

/*

LOG OUT METHOD
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(LoginActivityScreen.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

*/
    } //end of onCreate



    private fun setTheCorrectFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.loggedActivityFragmentView, fragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        val user = mAuth!!.currentUser
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    private fun setupBottomNavigation() {
        //bottomNav.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.loggedActivityFragmentView)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.loggedActivityFragmentView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
/*

    public void setListData() {
        initiativesDatList.add(new InitiativeData("Gikk til jobben A", "20"));
        initiativesDatList.add(new InitiativeData("Gjenbrukte gamle klær", "25"));
        initiativesDatList.add(new InitiativeData("Brukte sykkel til jobben", "10"));
        initiativesDatList.add(new InitiativeData("Tok med mat til jobben", "15"));
        initiativesDatList.add(new InitiativeData("Brukte sykkel til jobben", "20"));
        initiativesDatList.add(new InitiativeData("Kjøpte mat fra lokal bedrift", "5"));

    }


    public void recycleViewPopulate() {

        initiatives.add((new Initiatives("Gikk til jobben", R.drawable.mal2, "25 poeng ")));
        initiatives.add(new Initiatives("Gjenbrukte gamle klær", R.drawable.mal33, "20 poeng "));
        initiatives.add(new Initiatives("Brukte sykkel til jobben", R.drawable.mal3, "20 poeng "));
        initiatives.add(new Initiatives("Kjøpte mat fra lokal bedrift", R.drawable.mal1, "10 poeng "));
        initiatives.add(new Initiatives("Tok med mat til jobben", R.drawable.planet, "15 poeng "));
        initiatives.add(new Initiatives("Brukte sykkel til jobben", R.drawable.mal3, "20 poeng "));
        initiatives.add(new Initiatives("Kjøpte mat fra lokal bedrift", R.drawable.mal2, "5 poeng "));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleAdapter);

    }//End of recycleViewPopulate
/ *
    @Override
    public void onItemClick(int positionOfTheIni) {
        Toast.makeText(this, "Clicked: " + initiatives.get(positionOfTheIni).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, InitiativesInfo.class);
        intent.putExtra("image", initiatives.get(positionOfTheIni).getImage());
        intent.putExtra("name", initiatives.get(positionOfTheIni).getName());
        intent.putExtra("points", initiatives.get(positionOfTheIni).getPoints());
        startActivity(intent);
    }
*/
    /*
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.firstFragment:
                    actionBar.setTitle("Dashboaret");
                    FirstFragment frag1 = new FirstFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainerView2, frag1);
                    fragmentTransaction.commit();
                    return true;

                case R.id.secondFragment:
                    actionBar.setTitle("Profil");
                    SecondFragment frag2 = new SecondFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fragmentContainerView2, frag2);
                    fragmentTransaction1.commit();
                    return true;

                case R.id.highScoreFragment:
                    actionBar.setTitle("High Score");
                    HighScoreFragment frag3 = new HighScoreFragment();
                    Highscorekotlin frag3kotlin = new Highscorekotlin();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    //fragmentTransaction3.replace(R.id.fragmentContainerView2, frag3);

                    fragmentTransaction3.replace(R.id.fragmentContainerView2, frag3kotlin);
                    fragmentTransaction3.commit();
                    return true;
            }
            return false;
        });

*/
    /*
        //setContentView(R.layout.loggetscreen)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.loggetscreen)
        //setContentView(binding.root)



        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val highscore = Highscorekotlin()


        //bottomNavigationView.setOnitem
        bottomNavigationView?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setTheCorrectFragment(firstFragment)


        bottomNavigationView!!.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.firstFragment ->{
                        setTheCorrectFragment(firstFragment)
                    }
                    R.id.secondFragment->{
                        setTheCorrectFragment(secondFragment)
                    }
                    R.id.highscorekotlin->{
                        setTheCorrectFragment(highscore)
                    }
                }
            true
            }


    * */



}









