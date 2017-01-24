package mzta.com.hello_rxjava_retrofit.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import rx.Subscription;
import rx.schedulers.Schedulers;

import rx.android.schedulers.AndroidSchedulers;
import mzta.com.hello_rxjava_retrofit.R;
import mzta.com.hello_rxjava_retrofit.api.JsonPlaceHolderApi;
import mzta.com.hello_rxjava_retrofit.model.Post;
import mzta.com.hello_rxjava_retrofit.networking.RetrofitProvider;
import mzta.com.hello_rxjava_retrofit.ui.adapter.PostAdapter;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.post_recyclerview)
    RecyclerView postRecyclerview;

    private JsonPlaceHolderApi api;
    private PostAdapter postAdapter;
    private ProgressDialog progressDialog;
    private Subscription subscription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configureViews();

        api= RetrofitProvider.get().create(JsonPlaceHolderApi.class);

    }

    @Override
    protected void onResume() {
        super.onResume();


        progressDialog.show();
        subscription= api.getPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(new Subscriber<List<Post>>() {
                               @Override
                               public void onCompleted() {
                                   Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_LONG).show();
                                   progressDialog.dismiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                                   progressDialog.dismiss();
                               }

                               @Override
                               public void onNext(List<Post> posts) {
                                   postAdapter.setData(new ArrayList<Post>(posts));
                               }
                           }
                );
    }

    private void configureViews() {
        //progress Dialog
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading...");
        //post Adapter
        postAdapter = new PostAdapter();
        //layout manager
        postRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        postRecyclerview.setAdapter(postAdapter);
    }
 }
