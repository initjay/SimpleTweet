package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {

    // tweet to display
    Tweet tweet;

    // view objects
    TextView tvScreenName, tvBody, tvCreatedAt;
    ImageView ivProfileImage, ivMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        tvScreenName = findViewById(R.id.tvScreenName);
        tvBody = findViewById(R.id.tvBody);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvScreenName.setText(tweet.user.screenName);
        tvBody.setText(tweet.getBody());
        tvCreatedAt.setText(tweet.getCreatedAt());

        String profileImage = tweet.getProfileImage();

        Glide.with(this).load(profileImage).circleCrop().into(ivProfileImage);

//        if (tweet.media != null) {
//            Glide.with(this).load(tweet.media).override(tweet.mWidth, tweet.mHeight).into(ivMedia);
//        }

    }
}