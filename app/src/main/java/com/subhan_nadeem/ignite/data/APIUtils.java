package com.subhan_nadeem.ignite.data;

/**
 * Created by Subhan Nadeem on 2017-09-09.
 */

public class APIUtils {
        public static final String BASE_URL = "https://reddit.com";

        public static RedditService getRedditService() {
            return RedditServiceClient.getClient(BASE_URL).create(RedditService.class);
        }
}
