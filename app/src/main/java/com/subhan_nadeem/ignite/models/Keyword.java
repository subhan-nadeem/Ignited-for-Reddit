package com.subhan_nadeem.ignite.models;

/**
 * Created by Subhan Nadeem on 2017-09-12.
 * Subreddit filteration keyword instance class
 */

public class Keyword {
    public static String KEYWORD_TYPE_FLAIR = "flair";
    public static String KEYWORD_TYPE_POST = "post";
    private String keyword;
    private String type;

    public Keyword(String keyword, String type) {

        // Ensure entered type is correct
        if (!(type.equals(KEYWORD_TYPE_FLAIR) || type.equals(KEYWORD_TYPE_POST)))
            throw new IllegalArgumentException("Keyword class constructed with invalid type String "
                    + type);

        this.keyword = keyword;
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
