package net.samael.villette.myapplication.activities.photos;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.samael.villette.myapplication.BuildConfig;
import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.adapters.PhotoAdapter;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.models.Image;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity
{
    Context context = PhotoActivity.this;

    Intent toCameraIntent;

    CoordinatorLayout coordinatorLayout;

    GridView imagesGrid;
    FloatingActionButton takePictureButton;

    Uri photoURI;

    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.camera_coordinator_layout);

        canTakePicture();

        imagesGrid = (GridView)findViewById(R.id.grid_images);
        imagesGrid.setAdapter(new PhotoAdapter(context, R.layout.grid_photo_item, getTakenImages()));

        takePictureButton = (FloatingActionButton) findViewById(R.id.take_picture);
        takePictureButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                toCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (toCameraIntent.resolveActivity(getPackageManager()) != null)
                {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {

                    }

                    if (photoFile != null) {
                        photoURI = FileProvider.getUriForFile(context,
                                BuildConfig.APPLICATION_ID + ".fileprovider", photoFile);
                        toCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(toCameraIntent, MyConstants.CAMERA_INTENT_CODE);
                    }
                }
            }
        });


    }

    public Image[] getTakenImages()
    {
        return new Image[]{};
    }

    public void canTakePicture()
    {
        if (hasCameraHarware())
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                askPermissions();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.no_camera);
            builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    finish();
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void askPermissions()
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.autorize_photo_title);
                builder.setMessage(R.string.should_autorize_photo);
                builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MyConstants.CAMERA_PERMISSION_REQUEST_CODE);
                    }
                });
                builder.show();
            } else {
                requestPermissions(new String[] {Manifest.permission.CAMERA},
                        MyConstants.CAMERA_PERMISSION_REQUEST_CODE);
            }
        }

        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, MyConstants.STORAGE_PERMISSIONS_CODE);
        }
    }

    public boolean hasCameraHarware()
    {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case MyConstants.CAMERA_PERMISSION_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Snackbar.make(coordinatorLayout, R.string.camera_permission_granted, Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(coordinatorLayout, R.string.camera_permission_denied, Snackbar.LENGTH_LONG).show();
                    finish();
                }
                break;

            case MyConstants.STORAGE_PERMISSIONS_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Snackbar.make(coordinatorLayout, R.string.storage_permission_granted, Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(coordinatorLayout, R.string.storage_permission_denied, Snackbar.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {
            case MyConstants.CAMERA_INTENT_CODE:
                if (resultCode == RESULT_OK)
                {

                    Snackbar.make(coordinatorLayout, R.string.picture_taken, Snackbar.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Snackbar.make(coordinatorLayout, R.string.picture_not_taken, Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private File createImageFile() throws IOException
    {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "JPG_" + timestamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                filename, ".jpg", storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
