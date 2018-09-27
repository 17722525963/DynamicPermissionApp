package com.yxkj.dynamicpermissionapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.get)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPermission();
                    }
                });

        findViewById(R.id.getNext)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MainActivity.this, "有网络权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "没有网络权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getPermission() {
        //检查当前是否有权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();

            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 200);


            //如果用户拒绝权限请求，下面方法会返回true,此时需要向用户展示为什么需要这个权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "用户拒绝了权限", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "有", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 用户请求权限结果回传
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("???", permissions[i]);
                Log.i("???-->", "" + grantResults[i]);
                if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, permissions[i]+"权限申请成功~", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, permissions[i]+"权限申请失败失败~~~~~~~", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
