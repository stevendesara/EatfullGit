package in.eatfull.eatfull;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import in.eatfull.eatfull.Task.Home_Task;


public class Item_Detail_View extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView ItemName, ItemPrice, ItemProductCode, ItemAvailablity, ItemDescription;
    ImageView ItemImg;

    int product_id;

    public Item_Detail_View() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        product_id = getIntent().getIntExtra(Home.SOME_KEY, -1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///////NAvigation bar
        //////////////////////////////

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        Toast.makeText(getApplicationContext(),String.valueOf(product_id) + "o o",Toast.LENGTH_LONG).show();

        ItemName = (TextView)findViewById(R.id.ItemNameTV);
        ItemPrice = (TextView)findViewById(R.id.ItemPriceTV);
        ItemImg = (ImageView)findViewById(R.id.ItemImg);
        ItemProductCode = (TextView) findViewById(R.id.ItemProductCodeTV);
        ItemAvailablity = (TextView)findViewById(R.id.ItemAvailablityTV);
        ItemDescription = (TextView) findViewById(R.id.ItemDescriptionTV);

        //tabs Description
        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.Description);
        spec.setIndicator("Description");
        tabHost.addTab(spec);


        //tabs Reviews

        spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.Reviews);

        spec.setIndicator("Reviews");
        tabHost.addTab(spec);

        settingItemDetails();

    }

    public void settingItemDetails(){
        for (int i = 0;i<Home_Task.oc_products.size();i++){
        if (Home_Task.oc_products.get(i).getProduct_id() == product_id){
            ItemName.setText(Home_Task.oc_products.get(i).getName());
            ItemPrice.setText(String.valueOf(Home_Task.oc_products.get(i).getPrice()));
            ItemProductCode.setText(String.valueOf(Home_Task.oc_products.get(i).getProduct_id()));
            ItemDescription.setText(Home_Task.oc_products.get(i).getDescription());
        }
        }
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
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

        if (id == R.id.nav_MyAccount) {

        } else if (id == R.id.nav_OrderHistory) {

        }else if (id == R.id.nav_WishList) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ///////////////////////////////

}
