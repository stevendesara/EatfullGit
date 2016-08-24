package in.eatfull.eatfull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import in.eatfull.eatfull.Models.oc_product;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ItemList extends AppCompatActivity implements AdapterView.OnItemSelectedListener,NavigationView.OnNavigationItemSelectedListener {

    public static final String SOME_KEY = "some_key";
    RecyclerView ItemList;
    RecyclerView.LayoutManager ItemListLayoutManager;
    public static RecyclerView.Adapter ItemList_adapter;
    static ArrayList<oc_product> Itemlist_Array = new ArrayList<>();

    public static void ItemList(ArrayList<oc_product> item_list) {
         Itemlist_Array = item_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SortingSpinner();
//        ViewSpinner();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


        ItemList = (RecyclerView) findViewById(R.id.ItemListRV);
        ItemList.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        ItemListLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL,false);
        ItemList_adapter = new ItemList_Adapter(Itemlist_Array,getApplicationContext());

        ItemList.setLayoutManager(ItemListLayoutManager);
        ItemList.setHasFixedSize(true);
        ItemList.setAdapter(ItemList_adapter);
        ItemList.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), ItemList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                oc_product product = Itemlist_Array.get(position);
                Toast.makeText(getApplicationContext(),String.valueOf(product.getProduct_id()),Toast.LENGTH_LONG).show();
                CallItemDetailView(product.getProduct_id());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void CallItemDetailView(int product_id) {
        Intent intent = new Intent(this,Item_Detail_View.class);
        intent.putExtra(SOME_KEY,product_id);
        startActivity(intent);
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


    ////////////////////////////////////////
                                            //Spinner////////////////////////////////

    protected void SortingSpinner(){
        Spinner SortingSpinner = (Spinner) findViewById(R.id.SortingSpin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Sort, android.R.layout.simple_spinner_dropdown_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SortingSpinner.setAdapter(adapter);
        SortingSpinner.setOnItemSelectedListener(this);

    }

//    protected void ViewSpinner(){
//        Spinner ViewSpinner = (Spinner) findViewById(R.id.ViewSpin);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.View, android.R.layout.simple_spinner_dropdown_item);
//        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ViewSpinner.setAdapter(adapter);
//        ViewSpinner.setOnItemSelectedListener(this);
//
//    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        int first = 1,second = 2,third = 3,fourth = 4;
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.SortingSpin)
        {
            if(position == first){
                price_asc(view);
            }
            else if(position == second){
                price_desc(view);
            }
            else if(position == third){
                name_asc(view);
            }else if(position == fourth){
            name_desc(view);
        }
        }

//        if(spinner.getId() == R.id.ViewSpin)
//        {
//            if(position == first){
//                price_asc(view);
//            }
//            else if(position == second){
//                price_desc(view);
//            }
//        }

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    ////////////////////////////////////////
                                    ///     sorting
                                                /////////////////////////////////

    public void price_asc (View view){
        sort("price_asc");
    }
    public void price_desc (View view){
        sort("price_desc");
    }
    public void name_asc (View view){
        sort("name_asc");
    }
    public void name_desc (View view){
        sort("name_desc");
    }
    public void sort(final String order){
        Comparator<oc_product> comp = new Comparator<oc_product>() {
            @Override
            public int compare(oc_product lhs, oc_product rhs) {
                float first = Float.parseFloat(lhs.getPrice()+"");
                float second = Float.parseFloat(rhs.getPrice()+"");
                String name_first = lhs.getName();
                String name_second = rhs.getName();

                if(order == "price_asc")
                    return (first < second ?-1 : first == second? 0:1);
                else if(order == "price_desc")
                    return (first > second ?-1 : first == second? 0:1);
                else if (order == "name_asc")
                    return name_first.compareToIgnoreCase(name_second);
                else if(order == "name_desc")
                    return name_second.compareToIgnoreCase(name_first);
                else
                    return 0;
            }
        };
        Collections.sort(Itemlist_Array,comp);
        ItemList_adapter.notifyDataSetChanged();
    }


/////////////////////////////////////
                    ///////     Adapter
                                        //////////////////////////////////

    public class ItemList_Adapter extends RecyclerView.Adapter<ItemList_Adapter.RecyclerViewholder> {
        private Context context;
        ArrayList<oc_product> product;

        public ItemList_Adapter(ArrayList<oc_product> arrayList, Context context) {
            this.product = arrayList;
            this.context = context;
        }


        @Override
        public RecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
            RecyclerViewholder recyclerViewholder = new RecyclerViewholder(view);
            return recyclerViewholder;
        }

        @Override
        public void onBindViewHolder(final RecyclerViewholder holder, int position) {
            oc_product mdl = product.get(position);

            holder.ItemName.setText(mdl.getName());
            holder.ItemPrice.setText(String.valueOf(mdl.getPrice()));
            holder.ItemDescription.setText(mdl.getDescription());
            Picasso.with(context).load(mdl.getImage())/*.resize(100, 100)*/.into(holder.ItemImg);
            holder.Cart.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    holder.Cart.setEnabled(false);
                }
            });
        }

        @Override
        public int getItemCount() {
            return product.size();
        }


        public class RecyclerViewholder extends RecyclerView.ViewHolder {
            TextView ItemName ,ItemPrice, ItemDescription;
            ImageView ItemImg;
            Button Cart;
            public RecyclerViewholder(View view) {
                super(view);

                ItemName = (TextView)itemView.findViewById(R.id.ItemNameTV);
                ItemPrice = (TextView)itemView.findViewById(R.id.ItemPriceTV);
                ItemDescription = (TextView)itemView.findViewById(R.id.ItemDescriptionTV);
                ItemImg = (ImageView)itemView.findViewById(R.id.ItemImg);
                Cart = (Button) itemView.findViewById(R.id.CartBut);



            }
        }
    }


}
