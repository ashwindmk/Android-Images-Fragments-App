package com.example.ashwin.imagesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwin on 29/10/16.
 */

public class ImagesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Image> imagesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_images, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.images_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImagesAdapter(imagesList);

        prepareImagesData();

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Image image = imagesList.get(position);
                Toast.makeText(getActivity(), image.getRes() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SelectedImageActivity.class);
                intent.putExtra("res", image.getRes());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Image image = imagesList.get(position);
                Toast.makeText(getActivity(), image.getRes() + " is long pressed", Toast.LENGTH_LONG).show();
            }
        }));
    }

    private void prepareImagesData() {
        Image img = new Image(R.drawable.image1);
        imagesList.add(img);

        img = new Image(R.drawable.image2);
        imagesList.add(img);

        img = new Image(R.drawable.image3);
        imagesList.add(img);

        img = new Image(R.drawable.image4);
        imagesList.add(img);

        img = new Image(R.drawable.image5);
        imagesList.add(img);

        mAdapter.notifyDataSetChanged();
    }
}
