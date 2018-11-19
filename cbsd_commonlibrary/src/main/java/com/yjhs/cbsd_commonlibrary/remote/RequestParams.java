package com.yjhs.cbsd_commonlibrary.remote;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: Administrator
 * date: 2018/5/14 0014
 * desc:
 */
@SuppressWarnings({"unused", "RedundantIfStatement"})
public class RequestParams {
    //线程安全的HashMap
    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, String> fileParams = new ConcurrentHashMap<>();

    /**
     * Constructs a new empty {@code RequestParams} instance.
     */
    public RequestParams() throws FileNotFoundException {
        this((Map<String, String>) null);
    }

    /**
     * Constructs a new RequestParams instance containing the key/value string
     * params from the specified map.
     *
     * @param source the source key/value string map to add.
     */
    public RequestParams(Map<String, String> source) {
        if (source != null && source.size() > 0) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }



    /**
     * Constructs a new RequestParams instance and populate it with a single
     * initial key/value string param.
     *
     * @param key   the key name for the intial param.
     * @param value the value string for the initial param.
     */
    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    /**
     * Adds a key/value string pair to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value string for the new param.
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

  /*  public void put(String key, String value)  {
        if (key != null) {
            fileParams.put(key, value);
        }
    }*/

    public boolean hasParams() {
        if (urlParams.size() > 0 || fileParams.size() > 0) {
            return true;
        }
        return false;
    }
}
