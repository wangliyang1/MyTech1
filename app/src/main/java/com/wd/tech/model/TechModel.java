package com.wd.tech.model;

import com.wd.tech.contract.IContract;
import com.wd.tech.util.NetUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TechModel implements IContract.IModel {
    @Override
    public void getNoParams(String url, Class cls, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().getNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void getDoParams(String url, Class cls, HashMap<String, Object> map, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().getDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void doGetHeaderParams(String url, Class cls, Map<String, Object> map, IContract.IModelCallback modelCallback) {
        NetUtil.getInstance().doGetHeaderParams(url, cls, map, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                modelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                modelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postDoHeadPic(String url, Class cls, MultipartBody.Part image, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postDoHeadPic(url, cls, image,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postNoParams(String url, Class cls, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postDoParams(String url, Class cls, HashMap<String, Object> map, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postFileParams(String url, Class cls, HashMap<String, RequestBody> map, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postFileParams(url, cls, map, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void putNoParams(String url, Class cls, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().putNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void putDoParams(String url, Class cls, HashMap<String, Object> map, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().putDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void dltNoParams(String url, Class cls, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().dltNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void dltDoParams(String url, Class cls, HashMap<String, Object> map, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().dltDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postweixin(String url, String ak, String code, Class cls, IContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postweixin(url, cls, ak, code, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }
}
