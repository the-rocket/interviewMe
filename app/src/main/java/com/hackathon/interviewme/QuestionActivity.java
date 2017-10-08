package com.hackathon.interviewme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by yernar on 08/10/2017.
 */

public class QuestionActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private final String VIDEO_PATH_NAME =  Environment.getExternalStorageDirectory().getPath() + "/video/" + "myvideo.mp4";
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private View mToggleButton;
    private boolean mInitSuccesful;

    TextView tx, ct;
    static int index = 0;

    String[] Question = {"1. Describe your self", "2. Where do you live", "3. What is computer science for you"};

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 555;
    public static final int MY_PERMISSIONS_REQUEST_EXSTORAGE = 444;
    public static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 333;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    onDestroy();
                }
            }
            case MY_PERMISSIONS_REQUEST_EXSTORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    onDestroy();
                }
            }
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    onDestroy();
                }
            }

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXSTORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        }


        mSurfaceView = (SurfaceView) findViewById(R.id.cameraview);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
        //initRecorder();
        tx = (TextView) findViewById(R.id.questionview);
        ct = (TextView) findViewById(R.id.trsh);
        findViewById(R.id.questionview).setVisibility(View.GONE);
        index = 0;
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
                long d = millisUntilFinished/1000;

            }

            public void onFinish() {
                dod();
            }
        }.start();

    }

    void dod() {
        if (index > 2) {
            doanother();
            return;
        }
        tx.setVisibility(View.VISIBLE);
        ct.setVisibility(View.VISIBLE);
        tx.setText(Question[index]);
        index++;
        ct.setText(String.format(Locale.ENGLISH, "%d", 10));
        findViewById(R.id.cameraview).setVisibility(View.GONE);
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long d = millisUntilFinished/1000;
                    ct.setText(String.format(Locale.ENGLISH, "%d", d));

            }

            public void onFinish() {
                findViewById(R.id.trsh).setVisibility(View.GONE);
                findViewById(R.id.cameraview).setVisibility(View.VISIBLE);
                new CountDownTimer(10000, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        dod();
                    }
                }.start();
            }
        }.start();
    }

    void doanother() {
        tx.setVisibility(View.GONE);
        ct.setVisibility(View.VISIBLE);
        ct.setText("Thank you, you are done!");
        findViewById(R.id.cameraview).setVisibility(View.GONE);
//        new CountDownTimer(4000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//            }
//
//            public void onFinish() {
//
//            }
//        }.start();
    }


//    private void initRecorder() {
//        mCamera = Camera.open();
//        mCamera.unlock();
//        Camera.Parameters parameters = null;
//        try {
//            parameters = mCamera.getParameters();
//        } catch (Exception e) {
//
//        }
//        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//        mCamera.setParameters(parameters);
//        mCamera.setDisplayOrientation(90);
//
//        try {
//            mCamera.setPreviewDisplay(mHolder);
//            mCamera.startPreview();
//
//        } catch (Exception e) {
//            Log.e("sas", "init_camera: " + e);
//            return;
//        }
//
//
//        try {
//            mCamera.stopPreview();
//        } catch (Exception e){
//            // ignore: tried to stop a non-existent preview
//        }
//
//
//        mInitSuccesful = true;
//    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mCamera = Camera.open(1);
        mCamera.setDisplayOrientation(90);
        try {
            if (mCamera != null) {
                mCamera.setPreviewDisplay(mHolder);
            }
        } catch (IOException exception) {
            Log.e("ok", "IOException caused by setPreviewDisplay()", exception);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (mCamera != null) {
            Camera.Parameters param = mCamera.getParameters();
            mCamera.setParameters(param);
            try {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
