package com.edgespatial.zunguka.util;

import com.edgespatial.zunguka.api.models.SearchHistory;
import com.mg.surblime.api.StaticCache;

import java.util.HashMap;

/**
 * Created by moses on 6/21/18.
 */

public class ZungukaCache extends StaticCache {
    @Override
    public void inflateCache(HashMap<Class, String> cache) {
        cache.put(SearchHistory.class, "search_history.zk");
    }
}
