package dreamguys.in.co.gigs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dreamguys.in.co.gigs.DetailGigs;
import dreamguys.in.co.gigs.Model.GETMyGigs;
import dreamguys.in.co.gigs.R;
import dreamguys.in.co.gigs.utils.Constants;

/**
 * Created by user5 on 07-11-2017.
 */

public class MyGigsListAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<GETMyGigs.Datum> data;
    private final LayoutInflater mInflater;

    /*public MyGigsListAdapter(Context mContext, List<GETMyGigs.Datum> data) {
        this.mContext = mContext;
        this.data = data;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    public MyGigsListAdapter(Context mContext, List<GETMyGigs.Datum> data) {
        this.mContext = mContext;
        this.data = data;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public GETMyGigs.Datum getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_all_gigs, null);
            mHolder.gigsTitle = (TextView) convertView.findViewById(R.id.tv_gigs_title);
            mHolder.gigsAuthor = (TextView) convertView.findViewById(R.id.author);
            mHolder.gigsReviewCount = (TextView) convertView.findViewById(R.id.rating_count);
            mHolder.gigsImages = (ImageView) convertView.findViewById(R.id.iv_gigs_images);
            mHolder.gigsRate = (TextView) convertView.findViewById(R.id.tv_gigs_rate);
            mHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rating_gigs);
            mHolder.gigsLocation = (TextView) convertView.findViewById(R.id.tv_loc);
            mHolder.llMyGigs = (CardView) convertView.findViewById(R.id.ll_myGigs);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.gigsTitle.setText(data.get(position).getTitle());
        mHolder.gigsAuthor.setText(data.get(position).getFullname());
        mHolder.gigsRate.setText(Constants.DOLLAR_SIGN + data.get(position).getGig_price());
        mHolder.gigsLocation.setText(data.get(position).getCountry() + "," + data.get(position).getState_name());
        Picasso.with(mContext).load(Constants.BASE_URL + data.get(position).getImage()).error(R.drawable.ic_no_image).into(mHolder.gigsImages);
        mHolder.ratingBar.setRating(Float.parseFloat(data.get(position).getGig_rating()));
        mHolder.gigsReviewCount.setText("(" + data.get(position).getGig_usercount() + ")");
        mHolder.llMyGigs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myGigs = new Intent(mContext, DetailGigs.class);
                myGigs.putExtra(Constants.GIGS_ID, data.get(position).getId());
                myGigs.putExtra(Constants.GIGS_TITLE, data.get(position).getTitle());
                mContext.startActivity(myGigs);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        CardView llMyGigs;
        TextView gigsTitle, gigsAuthor, gigsLocation, gigsReviewCount, gigsRate;
        ImageView gigsImages;
        RatingBar ratingBar;
    }
}
