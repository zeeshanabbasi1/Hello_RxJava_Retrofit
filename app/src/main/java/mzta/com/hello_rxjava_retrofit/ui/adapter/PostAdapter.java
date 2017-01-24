package mzta.com.hello_rxjava_retrofit.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mzta.com.hello_rxjava_retrofit.R;
import mzta.com.hello_rxjava_retrofit.model.Post;

/**
 * Created by Zee Abbasi on 1/23/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> posts = new ArrayList<>();

    public void setData(ArrayList<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post= posts.get(position);

        holder.postTitle.setText(post.getTitle());
        holder.postBody.setText(post.getBody());
    }


    class PostViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.post_title)
        TextView postTitle;
        @BindView(R.id.post_body)
        TextView postBody;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
