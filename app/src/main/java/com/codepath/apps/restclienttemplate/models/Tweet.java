package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public String media;
    public int mHeight;
    public int mWidth;
    public long id;
    public String profileImage;

    // empty constructor needed by the parcel library
    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");
        tweet.profileImage = tweet.user.profileImageUrl;

        JSONObject entities = jsonObject.getJSONObject("entities");
        // check if media entry exists
        if (entities.has("media")) {
            JSONObject media = entities.getJSONArray("media").getJSONObject(0);
            tweet.media = media.getString("media_url_https");
            JSONObject sizes = media.getJSONObject("sizes").getJSONObject("small");
            tweet.mHeight = sizes.getInt("h");
            tweet.mWidth = sizes.getInt("w");
        }
        else {
            tweet.media = "";
            tweet.mHeight = 0;
            tweet.mWidth = 0;
        }

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }

        return tweets;
    }

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
