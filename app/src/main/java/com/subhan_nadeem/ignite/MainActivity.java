package com.subhan_nadeem.ignite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.subhan_nadeem.ignite.posts.PostsFragment;
import com.subhan_nadeem.ignite.posts.PostsViewModel;
import com.subhan_nadeem.ignite.utilities.ActivityUtils;
import com.subhan_nadeem.ignite.utilities.ViewModelHolder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String POSTS_VIEWMODEL_TAG = "POSTS_VIEWMODEL_TAG";
    private PostsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = setupToolbar();

        initializeNavDrawer(toolbar);

        PostsFragment fragment = findOrCreateViewFragment();

        mViewModel = findOrCreateViewModel();

        mViewModel = findOrCreateViewModel();

        fragment.setViewModel(mViewModel);
    }

    private PostsViewModel findOrCreateViewModel() {
        // In a configuration change we might have a ViewModel present. It's retained using the
        // Fragment Manager.
        @SuppressWarnings("unchecked")
        ViewModelHolder<PostsViewModel> retainedViewModel =
                (ViewModelHolder<PostsViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(POSTS_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewmodel();
        } else {
            // There is no ViewModel yet, create it.
            PostsViewModel viewModel = new PostsViewModel();
            // and bind it to this Activity's lifecycle using the Fragment Manager.
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    POSTS_VIEWMODEL_TAG);
            return viewModel;
        }
    }

    @NonNull
    private PostsFragment findOrCreateViewFragment() {
        PostsFragment tasksFragment =
                (PostsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = PostsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.fragment_container);
        }
        return tasksFragment;
    }

    private Toolbar setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initializeNavDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
