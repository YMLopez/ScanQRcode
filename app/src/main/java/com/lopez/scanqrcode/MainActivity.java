package com.lopez.scanqrcode;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cn.bertsir.zbar.QRActivity;
import cn.bertsir.zbar.QrConfig;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    private static final int permissionsCode = 0x002;//拍照权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan(View view) {
        requestPermissions();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d("==w", "赋予了权限");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d("==w", "拒绝了权限");
    }

    @AfterPermissionGranted(permissionsCode)
    private void requestPermissions() {
        String[] perms = {
                // 把你想要申请的权限放进这里就行，注意用逗号隔开
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            //Toast.makeText(this, "Permissions Granted!", Toast.LENGTH_SHORT).show();
            gotoScan();
            Log.d("==w", "已获取权限");
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "请开启拍照权限", permissionsCode, perms);
            Log.d("==w", "尚未获取权限");
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.d("==w", "-----------------------------------赋予了权限");
        gotoScan();
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        Log.d("==w", "-----------------------------------拒绝了权限");
    }

    private void gotoScan() {
        //startActivity(new Intent(this, ScanActivity.class));
        QrConfig options = new QrConfig.Builder().create();
        Intent intent = new Intent(this, QRActivity.class);
        intent.putExtra(QrConfig.EXTRA_THIS_CONFIG, options);
        this.startActivity(intent);
    }

}
