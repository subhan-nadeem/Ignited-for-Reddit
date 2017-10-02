package com.subhan_nadeem.ignite.network;

/**
 * Created by Subhan Nadeem on 2017-09-09.
 */

public class APIUtils {
        private static final String BASE_URL = "https://www.reddit.com";

        public static RedditService getRedditService() {
            return RedditServiceClient.getClient(BASE_URL).create(RedditService.class);
        }
}
