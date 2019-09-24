package com.allumez.refercanada.com.allumez.refercanada.blogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allumez.refercanada.R;


public class Blog_Detail_Adpater extends BaseAdapter{

    Context c;
    public static String id;
    public static String title;
    public static String description;
    public static String image;
    public static String meta_key;
    public static String meta_description;



    public Blog_Detail_Adpater(Context c,
                               String id,
                               String title,
                               String description,
                               String image,
                               String meta_key ,
                               String meta_description)
    {
        this.c=c;
        Blog_Detail_Adpater.id = id;
        Blog_Detail_Adpater.title = title;
        Blog_Detail_Adpater.description = description;
        Blog_Detail_Adpater.meta_key = meta_key;
        Blog_Detail_Adpater.meta_description = meta_description;
        Blog_Detail_Adpater.image = image;

    }


    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.blog_details,null);


        ImageView i1= convertView.findViewById(R.id.imageViewBlogDetailImage);

        TextView t1= convertView.findViewById(R.id.textViewBlogDetailTitle);
        TextView t2= convertView.findViewById(R.id.textViewBlogDetailDescription);

        String url=null;

        t1.setText(title);
        t2.setText(description);
        url= "http://refercanada.com/uploads/blogs_img/"+image;
//        final ProgressDialog loading = ProgressDialog.show(c,"Loading Images","Please wait...",false,true);
//            Glide.with(c)
//                    .load(url)
//                    .centerCrop()
//                    .addListener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            loading.dismiss();
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            loading.dismiss();
//                            return false;
//                        }
//                    })
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(i1);


        return convertView;
    }
}