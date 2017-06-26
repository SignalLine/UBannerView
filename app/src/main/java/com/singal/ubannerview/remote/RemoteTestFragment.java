package com.singal.ubannerview.remote;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.singal.bannerview.BannerView;
import com.singal.bannerview.holder.BannerHolderCreator;
import com.singal.bannerview.holder.BannerViewHolder;
import com.singal.ubannerview.R;
import com.singal.ubannerview.http.Fault;
import com.squareup.picasso.Picasso;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by zhouwei on 17/6/8.
 */

public class RemoteTestFragment extends Fragment {
   private MovieLoader mMovieLoader;
   private BannerView mMZBannerView;
   private Handler mHandler = new Handler();
    public static RemoteTestFragment newInstance(){
        RemoteTestFragment fragment = new RemoteTestFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remote_test_layout,null);
        mMZBannerView = (BannerView) view.findViewById(R.id.my_banner);
        mMovieLoader = new MovieLoader();
        getMovieList();
        return view;
    }


    /**
     * 获取电影列表
     */
    private void getMovieList(){
        mMovieLoader.getMovie(0,10).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                Log.e("zhouwei","get data suceess");
                Log.e("zhouwei",movies.toString());

                setBanner(movies);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("TAG","error message:"+throwable.getMessage());
                if(throwable instanceof Fault){
                    Fault fault = (Fault) throwable;
                    if(fault.getErrorCode() == 404){
                        //错误处理
                    }else if(fault.getErrorCode() == 500){
                        //错误处理
                    }else if(fault.getErrorCode() == 501){
                        //错误处理
                    }
                }
            }
        });

    }

    private void setBanner(List<Movie> movies){
        mMZBannerView.setPages(movies, new BannerHolderCreator<BannerViewHolder1>() {
            @Override
            public BannerViewHolder1 createViewHolder() {
                return new BannerViewHolder1();
            }
        });

        mMZBannerView.start();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMZBannerView.pause();
    }

    public static class BannerViewHolder1 implements BannerViewHolder<Movie> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.remote_item_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, Movie movie) {
            Log.e("zhouwei","current position:"+i);
            Picasso.with(context).load(movie.images.large).into(mImageView);
        }
    }
}
