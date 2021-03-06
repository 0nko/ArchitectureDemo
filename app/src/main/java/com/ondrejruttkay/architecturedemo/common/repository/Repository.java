package com.ondrejruttkay.architecturedemo.common.repository;

import com.ondrejruttkay.architecturedemo.common.util.RxUtils;
import com.ondrejruttkay.architecturedemo.model.Post;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Onko on 08/09/2016.
 */
public class Repository implements IRepository {

    protected HashMap<Integer, Post> posts;

    public Repository(Bus bus) {
        posts = new HashMap<>();
        int id = 0;

        // Fake posts
        posts.put(id, new Post(bus, id++, "Crush your enemies", "I'll give 1 million dollars CASH " +
                "to the first man to fill this urn with my opponent's ashes-JK!",
                "https://twitter.com/pete_schultz/status/763605581936599040",
                "100", "5", "https://pbs.twimg.com/media/CpjfOAtWYAAQelu.jpg"));

        posts.put(id, new Post(bus, id++, "Data Binding Rulez", "Having the views and business " +
                "logic (BL) mixed up in 5000 line-long activities is nice and all but it doesn’t " +
                "have to be that way. One of the key advantages of using data binding is a chance " +
                "to nicely separate the presentation layer from the BL.",
                "https://hogwartsp2.wordpress.com/2016/07/28/the-magic-of-android-data-binding-library/",
                "5", "4", "https://i2.wp.com/cases.azoft.com/images/2015/12/pattern-mvvm-scheme.png"));

        posts.put(id, new Post(bus, id++, "Saturday renamed Caturday", "Lolcats take over the planet.",
                "https://hogwartsp2.wordpress.com/2016/08/09/hackday-fluxc-rest-improvements/",
                "1", "14", null));
    }

    @Override
    public Observable<List<Post>> requestPosts() {
        return Observable.just(new ArrayList<>(posts.values()))
                .compose(RxUtils.applyDelay())
                .compose(RxUtils.applySchedulers());
    }

    @Override
    public Post getPost(int id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        } else {
            return null;
        }
    }
}
