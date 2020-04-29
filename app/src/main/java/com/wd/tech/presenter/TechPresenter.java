package com.wd.tech.presenter;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.contract.IContract;
import com.wd.tech.model.TechModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TechPresenter extends BasePresenter<IContract.IView> implements IContract.IPresenter {

    private TechModel techModel;

    @Override
    protected void initModel() {
        techModel = new TechModel();
    }

    @Override
    public void getNoParams(String url, Class cls) {
        techModel.getNoParams(url, cls, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getDoParams(String url, Class cls, HashMap<String, Object> map) {
        techModel.getDoParams(url, cls,map ,new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void postweixin(String url, String ak, String code, Class cls) {
        techModel.postweixin(url, ak, code, cls, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void doGetHeaderParams(String url, Class cls, Map<String, Object> map) {
        techModel.doGetHeaderParams(url, cls, map, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void postFileParams(String url, Class cls, HashMap<String, RequestBody> map) {
        techModel.postFileParams(url, cls, map, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void postDoHeadPic(String url, Class cls, MultipartBody.Part image) {
        techModel.postDoHeadPic(url, cls, image, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void postNoParams(String url, Class cls) {
        techModel.postNoParams(url, cls, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void postDoParams(String url, Class cls, HashMap<String, Object> map) {
        techModel.postDoParams(url, cls, map,new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void putNoParams(String url, Class cls) {
        techModel.putNoParams(url, cls,new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void putDoParams(String url, Class cls, HashMap<String, Object> map) {
        techModel.putDoParams(url, cls, map,new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void dltNoParams(String url, Class cls) {
        techModel.dltNoParams(url, cls, new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void dltDoParams(String url, Class cls, HashMap<String, Object> map) {
        techModel.dltDoParams(url, cls, map,new IContract.IModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
