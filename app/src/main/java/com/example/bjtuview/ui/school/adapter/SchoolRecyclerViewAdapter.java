package com.example.bjtuview.ui.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bjtuview.R;
import com.example.bjtuview.bean.Goods;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Goods>data;
    private LayoutInflater layoutInflater;
    private OnItemClickListener listener;
    private RecyclerView recyclerView;

    public SchoolRecyclerViewAdapter(RecyclerView recyclerView,Context context, List<Goods> data){
        this.recyclerView=recyclerView;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.data=data;
    }

    public void setGoods(List<Goods>data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =layoutInflater.inflate(viewType,parent,false);
        view.setOnClickListener(this);
        if(viewType==R.layout.school_recycler_image_text){
            return new MultiViewHolder(view);
        }
//        else if(viewType==R.layout.school_recycler_banner){
//            System.out.println("find banner layout");
//            Button enterBt=view.findViewById(R.id.enter);
//            Banner banner=(Banner) view.findViewById(R.id.banner);
//            enterBt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    System.out.println("hhh");
//                }
//            });
//            BannerViewHolder bannerViewHolder= new BannerViewHolder(view);
//            banner.setOnBannerListener(new OnBannerListener() {
//                @Override
//                public void OnBannerClick(Object data, int position) {
//                    System.out.println("diudg");
//                    Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show();
//                }
//            });
//            return bannerViewHolder;
////            return new BannerViewHolder(view);
//        }
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Goods goods=data.get(position);
        int itemViewType= getItemViewType(position);
        switch (itemViewType){
            case R.layout.school_recycler_banner:
                Banner banner=(Banner)holder.itemView;

                banner.setAdapter(new BannerImageAdapter<String>(goods.getBanners()) {
                            @Override
                            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                                //图片加载
                                Glide.with(holder.itemView)
                                        .load(data)
                                        .apply(RequestOptions.centerCropTransform())
                                        .into(holder.imageView);
                            }

                        }).addBannerLifecycleObserver((LifecycleOwner) context)//添加生命周期观察者
                            .setIndicator(new CircleIndicator(context));
                break;
            case R.layout.school_recycler_image:
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into((ImageView) holder.itemView);
                break;
            case R.layout.school_recycler_text:
                ((TextView)holder.itemView).setText(goods.getText());
                break;
            case R.layout.school_recycler_image_text:
                MultiViewHolder multiViewHolder=(MultiViewHolder) holder;
                multiViewHolder.textView.setText(goods.getText());
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(multiViewHolder.imageView);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods =data.get(position);
        if(goods.getBanners()!=null){
            return R.layout.school_recycler_banner;
        }else if(goods.getImageUrl()==null){
            //text
            return R.layout.school_recycler_text;
        }else if(goods.getText()==null){
            //image
            return R.layout.school_recycler_image;
        }else{
            //text and image
            return R.layout.school_recycler_image_text;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {

        if(listener!=null){
            int position=recyclerView.getChildAdapterPosition(view);
            listener.onItemClick(data.get(position));
        }
    }

    class SingleViewHolder extends RecyclerView.ViewHolder{

        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class BannerViewHolder extends  RecyclerView.ViewHolder{
        Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
        }
    }
    class MultiViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            imageView=itemView.findViewById(R.id.image);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public interface OnItemClickListener{
        void onItemClick(Goods goods);
    }
}
