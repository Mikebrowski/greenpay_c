package com.example.greenpayremastered

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import models.Initiatives
import models.InitiativeData
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.android.material.navigation.NavigationView
import android.os.Bundle
import com.example.greenpayremastered.R
import fragment.FirstFragment
import androidx.drawerlayout.widget.DrawerLayout
import fragment.SecondFragment
import fragment.HighScoreFragment
import fragment.Highscorekotlin
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import android.content.Intent
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.greenpayremastered.LoginActivity
import java.util.ArrayList

class LoginActivityScreen : AppCompatActivity() {
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

    private lateinit var appBarConfiguration: AppBarConfiguration

    //private View bottomNavigation;
    private val bottomNavigationView: BottomNavigationView? = null
    private var mDatabase: DatabaseReference? = null
    var actionBar: ActionBar? = null
    var navigationView: BottomNavigationView? = null
    var toggleActionDrawer: ActionBarDrawerToggle? = null
    var navigationViewTop: NavigationView? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loggetscreen)
        val frag1 = FirstFragment()

        //recyclerView = (RecyclerView) findViewById(R.id.fragment_recycleview_s);
        searchArea = findViewById<View>(R.id.searchI) as SearchView
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout_1)
        val navController : NavController = find


        navigationViewTop = findViewById(R.id.nav_view_login)
        toggleActionDrawer = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        //logoutBtn = (Button) findViewById(R.id.logoutBtn);
        //loginText = (TextView) findViewById(R.id.loggedInTextview);
        //firstButton = (Button) findViewById(R.id.firstButton);
        //secondButton = (Button) findViewById(R.id.secondButton);
        drawerLayout.addDrawerListener(toggleActionDrawer!!)
        toggleActionDrawer!!.syncState()


        //support
        actionBar = supportActionBar
        actionBar!!.setTitle("Welcome to Greenpay")
        val selectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.firstFragment -> {
                    actionBar!!.setTitle("Dashboaret")
                    val frag1 = FirstFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentContainerView2, frag1)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.secondFragment -> {
                    actionBar!!.setTitle("Profil")
                    val frag2 = SecondFragment()
                    val fragmentTransaction1 = supportFragmentManager.beginTransaction()
                    fragmentTransaction1.replace(R.id.fragmentContainerView2, frag2)
                    fragmentTransaction1.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.highScoreFragment -> {
                    actionBar!!.setTitle("High Score")
                    val frag3 = HighScoreFragment()
                    val frag3kotlin = Highscorekotlin()
                    val fragmentTransaction3 = supportFragmentManager.beginTransaction()
                    //fragmentTransaction3.replace(R.id.fragmentContainerView2, frag3);
                    fragmentTransaction3.replace(R.id.fragmentContainerView2, frag3kotlin)
                    fragmentTransaction3.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        navigationView = findViewById(R.id.bottomNavigationView)
        navigationView.setOnNavigationItemSelectedListener(selectedListener)
        // NEWER VERSION setOnNavigationItemSelectedListener
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2, frag1, "Dashboaret")
        fragmentTransaction.commit()
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser

        //recycleViewPopulate();
        //setListData();
        val highscorekotlin = Highscorekotlin()
        mDatabase = FirebaseDatabase.getInstance().reference
        /*
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("user/").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String usernameOnProfile = snapshot.getValue(UserData.class).getUsername();

                        String emailOnUser = snapshot.getValue(UserData.class).getEmail();
                        String profilePicUser = snapshot.getValue(UserData.class).getProfilePicture();
                        loginText.setText(usernameOnProfile);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        loginText.setText("Funket ikke korret");
                    }
                });


            }
        });
 */
/*
        searchArea.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String search = newText;
                recycleAdapter.getFilter().filter(newText);
                return false;
            }
        });
*/
/*
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginText.setText("I HAVE CHANGED INTO " + mAuth.getCurrentUser().getEmail());
                firstButton.setText(mAuth.getCurrentUser().getEmail());
            }
        });

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        val user = mAuth!!.currentUser
        if (user == null) {
            startActivity(Intent(this@LoginActivityScreen, LoginActivity::class.java))
        }
    } /*

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
}