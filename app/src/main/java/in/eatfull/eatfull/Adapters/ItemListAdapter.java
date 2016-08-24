package in.eatfull.eatfull.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import in.eatfull.eatfull.Models.oc_product;
import in.eatfull.eatfull.R;

/**
 * Created by steve on 6/6/16.
 */
public class ItemListAdapter extends ArrayAdapter{

    private List<oc_product> wasteModelList;
    private int resource;
    private LayoutInflater inflater;

    public ItemListAdapter(Context context, int resource, List<oc_product> objects) {
        super(context, resource, objects);
        wasteModelList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView  = inflater.inflate(resource,null);
        }

        ImageView ivMovie;
        TextView tvMovieName;
        TextView tvYear;
        TextView tvStory;
        RatingBar rbMovie;

        ivMovie = (ImageView) convertView.findViewById(R.id.ItemImg);
        tvMovieName = (TextView) convertView.findViewById(R.id.ItemNameTV);
        tvYear = (TextView) convertView.findViewById(R.id.ItemPriceTV);
        tvStory = (TextView) convertView.findViewById(R.id.ItemDescriptionTV);
        rbMovie = (RatingBar) convertView.findViewById(R.id.ItemRatingBar);

        tvMovieName.setTextColor(Color.BLACK);
        tvYear.setTextColor(Color.BLACK);
        tvStory.setTextColor(Color.BLACK);

//        tvMovieName.setText(wasteModelList.get(position).getMovie());
//        tvYear.setText(""+wasteModelList.get(position).getYear());
//        tvStory.setText(wasteModelList.get(position).getTagline());
//        rbMovie.setRating(wasteModelList.get(position).getRating()/2);

        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.ItemImgProg);
        // Then later, when you want to display image
        ImageLoader.getInstance().displayImage(wasteModelList.get(position).getImage(), ivMovie, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
            }
        }); // Default options will be used


        return convertView;
    }
}
