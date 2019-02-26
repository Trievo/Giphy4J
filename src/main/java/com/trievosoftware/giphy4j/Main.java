package com.trievosoftware.giphy4j;

import com.trievosoftware.giphy4j.entity.search.SearchFeed;
import com.trievosoftware.giphy4j.exception.GiphyException;

public class Main {

    public static void main(String[] args) {
        Giphy giphy = new Giphy("4zFUl8QxMZbhRSHnbSMmM6fUhyrke5eb");
        try {
            SearchFeed feed = giphy.search("balls", 1);

            System.out.println(feed.getDataList());
        } catch (GiphyException e) {
            e.printStackTrace();
        }
    }
}
