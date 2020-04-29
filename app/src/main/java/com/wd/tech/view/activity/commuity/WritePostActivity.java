package com.wd.tech.view.activity.commuity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RxPartMapUtils;
import com.wd.tech.view.activity.MainActivity;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

/*
* 发表帖子
* */
public class WritePostActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.comeback)
    TextView comeback;
    @BindView(R.id.publish)
    TextView publish;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.iv)
    ImageView iv;
    private Uri uri=null;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //隐藏标题栏
        getSupportActionBar().hide();
        //设置字数
        InputFilter[] filter = {new InputFilter.LengthFilter(300)};
        edContent.setFilters(filter);
        //文字监听
        edContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.toString().length();
                num.setText(length+"/300");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_write_post;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CommunityZanBean && TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(this, ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("bb",true);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeback, R.id.publish, R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeback:
                finish();
                break;
            case R.id.publish:
                String content = edContent.getText().toString().trim();
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(this, "评论为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, RequestBody> map = new HashMap<>();
                map.put("content", RxPartMapUtils.toRequestBodyOfText(content));
                //图片为空时
                if (uri != null&&!uri.equals("")) {
                    File file = uri2File(uri);
                    RequestBody requestBody = RxPartMapUtils.toRequestBodyOfImage(file);
                    map.put("file\"; filename=\""+file.getName()+"", requestBody);
                    Toast.makeText(this, ""+file.getName()+""+requestBody, Toast.LENGTH_SHORT).show();
                    mPresenter.postFileParams(MyUrls.BASE_POST, CommunityZanBean.class,map);
                }else {
                    mPresenter.postFileParams(MyUrls.BASE_POST, CommunityZanBean.class,map);
                }
                break;
            case R.id.iv:
                Intent img = new Intent(Intent.ACTION_PICK);
                img.setType("image/*");
                startActivityForResult(img,0);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            uri = data.getData();
            Glide.with(iv).load(uri).into(iv);
        }
    }
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }
}
