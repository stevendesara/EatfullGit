package in.eatfull.eatfull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.eatfull.eatfull.Models.oc_product;
import in.eatfull.eatfull.Task.Home_Task;
import in.eatfull.eatfull.Task.oc_product_description_task;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SOME_KEY = "some_key";

    oc_product_description_task product_description_task = new oc_product_description_task(Home.this);

    Item_Detail_View item_detail_view = new Item_Detail_View();

    Button BriyaniZoneBut ,ChickenZoneBut, MuttonZoneBut,SeaFoodZoneBut,VegZoneBut, SweetsZoneBut;

    RecyclerView BriyaniZone;
    RecyclerView ChickenZone;
    RecyclerView MuttonZone;
    RecyclerView SeaFoodZone;
    RecyclerView VegZone;
    RecyclerView SweetsZone;

    public static RecyclerView.Adapter briyani_zone_adapter;
    public static RecyclerView.Adapter chicken_zone_adapter;
    public static RecyclerView.Adapter mutton_zone_adapter;
    public static RecyclerView.Adapter seafood_zone_adapter;
    public static RecyclerView.Adapter veg_zone_adapter;
    public static RecyclerView.Adapter sweets_zone_adapter;

    RecyclerView.LayoutManager BriyaniZonelayoutManager;
    RecyclerView.LayoutManager ChickenZonelayoutManager;
    RecyclerView.LayoutManager MuttonZonelayoutManager;
    RecyclerView.LayoutManager SeaFoodZonelayoutManager;
    RecyclerView.LayoutManager VegZonelayoutManager;
    RecyclerView.LayoutManager SweetsZonelayoutManager;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        ///////////////////////////////////////////////////////////////////////////////////
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

        BriyaniZoneBut = (Button) findViewById(R.id.BiriyaniZoneTitleBut) ;
        ChickenZoneBut = (Button) findViewById(R.id.ChickenZoneTitleBut) ;
        MuttonZoneBut = (Button) findViewById(R.id.MuttonZoneTitleBut);
        SeaFoodZoneBut = (Button) findViewById(R.id.SeaFoodZoneTitleBut);
        VegZoneBut = (Button) findViewById(R.id.VegZoneTitleBut);
        SweetsZoneBut = (Button) findViewById(R.id.SweetsZoneTitleBut);

        BriyaniZoneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ItemList.ItemList(Home_Task.briyani_zone);
                ItemList.ItemList(Home_Task.oc_products_other);
                CallItemList();
            }
        });

        ChickenZoneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList.ItemList(Home_Task.chicken_zone);
                CallItemList();
            }
        });

        MuttonZoneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList.ItemList(Home_Task.mutton_zone);
                CallItemList();
            }
        });

        SeaFoodZoneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList.ItemList(Home_Task.seafood_zone);
                CallItemList();
            }
        });

        VegZoneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList.ItemList(Home_Task.veg_zone);
                CallItemList();
            }
        });

        SweetsZoneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList.ItemList(Home_Task.sweets_zone);
                CallItemList();
            }
        });


        BriyaniZone = (RecyclerView)findViewById(R.id.BriyaniZoneRV);
        ChickenZone = (RecyclerView)findViewById(R.id.ChickenZoneRV);
        MuttonZone = (RecyclerView)findViewById(R.id.MuttonZoneRV);
        SeaFoodZone = (RecyclerView)findViewById(R.id.SeaFoodZoneRV);
        VegZone = (RecyclerView)findViewById(R.id.VegZoneRV);
        SweetsZone = (RecyclerView)findViewById(R.id.SweetsZoneRV);

        BriyaniZone.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        ChickenZone.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        MuttonZone.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        SeaFoodZone.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        VegZone.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        SweetsZone.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        product_description_task.execute();

        BriyaniZonelayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        ChickenZonelayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        MuttonZonelayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        SeaFoodZonelayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        VegZonelayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        SweetsZonelayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);


//        briyani_zone_adapter = new Home_Adapter(Home_Task.briyani_zone,getApplicationContext());
        briyani_zone_adapter = new Home_Adapter(Home_Task.oc_products_other,getApplicationContext());
        chicken_zone_adapter = new Home_Adapter(Home_Task.chicken_zone,getApplicationContext());
        mutton_zone_adapter = new Home_Adapter(Home_Task.mutton_zone,getApplicationContext());
        seafood_zone_adapter = new Home_Adapter(Home_Task.seafood_zone,getApplicationContext());
        veg_zone_adapter = new Home_Adapter(Home_Task.veg_zone,getApplicationContext());
        sweets_zone_adapter = new Home_Adapter(Home_Task.sweets_zone,getApplicationContext());



        BriyaniZone.setLayoutManager(BriyaniZonelayoutManager);
        BriyaniZone.setHasFixedSize(true);
        BriyaniZone.setAdapter(briyani_zone_adapter);
        BriyaniZone.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), BriyaniZone, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Home_Task.oc_products_other.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//
        ChickenZone.setLayoutManager(ChickenZonelayoutManager);
        ChickenZone.setHasFixedSize(true);
        ChickenZone.setAdapter(chicken_zone_adapter);
        ChickenZone.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), ChickenZone, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Home_Task.chicken_zone.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        MuttonZone.setLayoutManager(MuttonZonelayoutManager);
        MuttonZone.setHasFixedSize(true);
        MuttonZone.setAdapter(mutton_zone_adapter);
        MuttonZone.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), MuttonZone, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Home_Task.mutton_zone.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        SeaFoodZone.setLayoutManager(SeaFoodZonelayoutManager);
        SeaFoodZone.setHasFixedSize(true);
        SeaFoodZone.setAdapter(seafood_zone_adapter);
        SeaFoodZone.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), SeaFoodZone, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Home_Task.seafood_zone.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        VegZone.setLayoutManager(VegZonelayoutManager);
        VegZone.setHasFixedSize(true);
        VegZone.setAdapter(veg_zone_adapter);
        VegZone.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), VegZone, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Home_Task.veg_zone.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        SweetsZone.setLayoutManager(SweetsZonelayoutManager);
        SweetsZone.setHasFixedSize(true);
        SweetsZone.setAdapter(sweets_zone_adapter);
        SweetsZone.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), SweetsZone, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Home_Task.sweets_zone.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        // Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();

        ImageLoader.getInstance().init(config); // Do it on Application start



    }

    private void CallItemList(){
        Intent intent = new Intent(this,ItemList.class);
        startActivity(intent);
    }

//    private boolean setposition(int position){
//        Item_Detail_View item_detail_view = new Item_Detail_View();
//        item_detail_view.setPosition(position);
//        return true;
//    }

    private Boolean CallItemDetailView(int product_id){
        Intent intent = new Intent(this,Item_Detail_View.class);
        intent.putExtra(SOME_KEY,product_id);
        startActivity(intent);
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item_list clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    ////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////



    ////////////////////////////
    // /*                   ADAPTER
                                 ////////////////////////////////

    public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.RecyclerViewholder> {
        private Context context;
        ArrayList<oc_product> product = new ArrayList<>();

        public Home_Adapter(ArrayList<oc_product> arrayList, Context context) {
            this.product = arrayList;
            this.context = context;
        }


        @Override
        public RecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preview,parent,false);
            RecyclerViewholder recyclerViewholder = new RecyclerViewholder(view);
            return recyclerViewholder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewholder holder, int position) {
            oc_product mdl = product.get(position);

            holder.ItemName.setText(mdl.getName());
            holder.ItemPrice.setText(String.valueOf(mdl.getPrice()));
            Picasso.with(context).load(mdl.getImage())/*.resize(100, 100)*/.into(holder.ItemImg);

        }

        @Override
        public int getItemCount() {
            return product.size();
        }



        public class RecyclerViewholder extends RecyclerView.ViewHolder {
            TextView ItemName ,ItemPrice;
            ImageView ItemImg;
            public RecyclerViewholder(View view) {
                super(view);

                ItemName = (TextView)itemView.findViewById(R.id.ItemNameTV);
                ItemPrice = (TextView)itemView.findViewById(R.id.ItemPriceTV);
                ItemImg = (ImageView)itemView.findViewById(R.id.ItemImg);

            }
        }
    }

    ////////////////////////////
    // /*                   TASK
                                ////////////////////////////////

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
    ////////////////////description task


}
