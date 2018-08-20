package com.mek.hurriyethaber.articlenews.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.articlenews.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticleNewsAdapter extends RecyclerView.Adapter<ArticleNewsAdapter.ArticleNewsViewHolder> {

    private final ArticleClickListener listener;
    private final List<NewsModel> data = new ArrayList<>();

    public ArticleNewsAdapter(ArticleClickListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    @Override
    public ArticleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articles, parent, false);
        return new ArticleNewsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ArticleNewsViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(data.get(position).id());
    }

    public void setData(List<NewsModel> repos) {
        if (repos != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RepoDiffCallback(data, repos));
            data.clear();
            data.addAll(repos);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class ArticleNewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title) TextView title;
        @BindView(R.id.news_photo)
        ImageView newsPhoto;

        private NewsModel newsModel;

        ArticleNewsViewHolder(View itemView, ArticleClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (newsModel != null) {
                    listener.onClick(newsModel);
                }
            });
        }

        void bind(NewsModel newsModel) {
            this.newsModel = newsModel;
            title.setText(newsModel.title());

            if (newsModel.files() != null && newsModel.files().size() != 0){
                Glide.with(newsPhoto.getContext())
                        .load(newsModel.files().get(0).fileUrl())
                        .apply(new RequestOptions().override(600,340))
                        .thumbnail(0.2f)
                        .into(newsPhoto);
            }

        }
    }

    public interface ArticleClickListener {

        void onClick(NewsModel model);
    }
}
