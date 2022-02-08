package com.example.greenpayremastered;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenpayremastered.R;
import com.example.greenpayremastered.databinding.ActivityImprovedMainBinding;
import com.example.greenpayremastered.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import adapter.RecycleAdapter;
import models.InitiativeData;
import models.Initiatives;
import database.UserData;
import fragment.FirstFragment;

import fragment.Highscorekotlin;
import fragment.SecondFragment;


public class LoginActivityScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;


    private ArrayList<Initiatives> initiatives = new ArrayList<>();
    private List<InitiativeData> initiativesDatList = new ArrayList<>();

    //private RecycleAdapter recycleAdapter = new RecycleAdapter(initiatives, this, this, initiativesDatList);
    // NEEDS THIS BACK WHEN IT WAS STACTIC RECYCLEVIEW
    // public class LoginActivityScreen extends AppCompatActivity implements RecycleAdapter.IniClickInterface

    private SearchView searchArea;
    private Button logoutBtn;
    private TextView loginText;
    private Button firstButton;
    private Button secondButton;
    //private View bottomNavigation;
    private BottomNavigationView bottomNavigationView;
    private DatabaseReference mDatabase;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private androidx.appcompat.widget.Toolbar toolbarproper;
    private NavigationView nvDrawer;

    ActionBar actionBar;
    BottomNavigationView navigationView;

    private LoginActivityScreen binding;

    //private ActivityMainBinding test2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loggetscreen);
        //binding = LoginActivityScreen.infate(getLayoutInflater());

        FirstFragment frag1 = new FirstFragment();

        nvDrawer = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(nvDrawer);


        /*
        toolbarproper = findViewById(R.id.properToolbar);
        setSupportActionBar(toolbarproper);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarproper.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        */
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //actionBar = getSupportActionBar();
        //actionBar.setTitle("Welcome to Greenpay");
/*

        //BottomNavigationView WHY DOES IT SHOW UP HERE?? THE SETONITEMRESELECTED
        getBinding().bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    replaceFragments(new FirstFragment());
                    break;

                case R.id.secondFragment:
                    replaceFragments(new SecondFragment());
                    break;
                case R.id.highScoreFragment:
                    replaceFragments(new Highscorekotlin());
                    break;
            }
            return true;
        });
*/




        //NavigationBarView.OnItemSelectedListener; diffrent attempt still meh.

        BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.firstFragment:
                        //actionBar.setTitle("Dashboaret");
                        FirstFragment frag1 = new FirstFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.loggedActivityFragmentView, frag1);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.secondFragment:
                        //actionBar.setTitle("Profil");
                        SecondFragment frag2 = new SecondFragment();
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.loggedActivityFragmentView, frag2);
                        fragmentTransaction1.commit();
                        return true;

                    case R.id.highScoreFragment:
                        //actionBar.setTitle("High Score");
                        //Highscorekotlin frag3 = new HighScoreFragment();
                        Highscorekotlin frag3kotlin = new Highscorekotlin();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        //fragmentTransaction3.replace(R.id.fragmentContainerView2, frag3);

                        fragmentTransaction3.replace(R.id.loggedActivityFragmentView, frag3kotlin);
                        fragmentTransaction3.commit();
                        return true;
                }
                return false;
            }
        };

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);
        // NEWER VERSION setOnNavigationItemSelectedListener

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loggedActivityFragmentView,frag1,"Dashboaret");
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //recycleViewPopulate();
        //setListData();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
    }//end of onCreate
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                }); }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.firstFragment2:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.secondFragment2:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.highScoreFragment:
                fragmentClass = Highscorekotlin.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        assert fragment != null;
        fragmentManager.beginTransaction().replace(R.id.loggedActivityFragmentView, fragment).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    public void setBinding(LoginActivityScreen binding) {
        this.binding = binding;
    }

    public LoginActivityScreen getBinding() {
        return binding;

    }


    private void replaceFragments(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.loggedActivityFragmentView,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(LoginActivityScreen.this, LoginActivity.class));
        }
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
/*
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