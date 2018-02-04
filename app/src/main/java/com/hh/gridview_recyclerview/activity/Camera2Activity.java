package com.hh.gridview_recyclerview.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.content.ContextCompat;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.LogUtil;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Camera2Activity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "Camera2Activity";
    private Button takepicture, teke_phote;
    private TextureView textureView;
    private CameraManager manager;
    private CameraDevice cameraDevice;
    private HandlerThread mBackgroundThread;
    public Handler mBackgroundHandler;
    private CameraDevice.StateCallback cameraCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            LogUtil.d("CameraCallback", "Camera Opened");
            cameraDevice = camera;
            takePreview();
        }

        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            LogUtil.d("CameraCallback", "Camera Disconnected");
//            closeCameraDevice();
        }

        @Override
        public void onError(CameraDevice cameraDevice, int i) {
            LogUtil.d("CameraCallback", "Camera Error");
            ToastUtil.showToast(Camera2Activity.this, "摄像头开启失败", 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera3);
        initView();
    }

    private void initView() {
        takepicture = (Button) findViewById(R.id.takepicture);
        teke_phote = (Button) findViewById(R.id.teke_phote);
        textureView = (TextureView) findViewById(R.id.textureView);
        takepicture.setOnClickListener(this);
        teke_phote.setOnClickListener(this);

        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());

        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        textureView.setSurfaceTextureListener(mSurfaceTextureListener);
    }

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            if (ContextCompat.checkSelfPermission(Camera2Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                try {
                    manager.openCamera("0", cameraCallback, null);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {

            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };

    private void takePreview() {
        try {
            if (null == cameraDevice) {
                LogUtil.d("updatePreview error, return");
                return;
            }
            final CaptureRequest.Builder captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            SurfaceTexture texture = textureView.getSurfaceTexture();
            texture.setDefaultBufferSize(800, 800);
            Surface textureSurface = new Surface(texture);
            captureRequestBuilder.addTarget(textureSurface);
            List<Surface> surfaceList = Arrays.asList(textureSurface);
            cameraDevice.createCaptureSession(surfaceList, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
                    LogUtil.d(TAG,"预览会话开启");
                    try {
                        cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    ToastUtil.showToast(Camera2Activity.this, "Configuration change", 3000);
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private MediaRecorder mMediaRecorder;

    private void setUpMediaRecorder() {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setOutputFile(new File(getExternalCacheDir(), System.currentTimeMillis() + ".mp4").getAbsolutePath());
        mMediaRecorder.setVideoEncodingBitRate(8 * 800 * 800);
        mMediaRecorder.setCaptureRate(30);
        mMediaRecorder.setVideoFrameRate(30);
        mMediaRecorder.setVideoSize(800, 800);


        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageReader imageReader;

    private void setUpImageReader() {
        imageReader = ImageReader.newInstance(800, 800, ImageFormat.YUV_420_888, 10);
        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                Image image = reader.acquireLatestImage();
                if (image != null) {
                    image.close();
                }
                LogUtil.i("onImageAvailable");
            }
        }, mBackgroundHandler);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.takepicture:
                break;
            case R.id.teke_phote:
                break;
            default:
        }
    }
}
