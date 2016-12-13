package demo.webview.com.webviewdemo;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListenerV1;
import com.thin.downloadmanager.RetryPolicy;
import com.thin.downloadmanager.ThinDownloadManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;

import fun.reg;

/**
 * Created by student on 12/13/2016.
 */

public class InfoDialogFragment extends DialogFragment {

    private EditText saveas;
    private Button cancel;
    private Button saveVideo;
    private Button copyText;
    private TextView descripos;
    private TextView tsave;
    private TextView tprogress;
    private ProgressBar mprogress;
    private ImageView thubnail;
    private LinearLayout mOptions;
    private Button stream;
    private LinearLayout arrVideoOptions;
    private LinearLayout videoOption;
    private Button twentyFour;
    private Button thirteenSex;
    private Button fifteenFour;
    private Button seventyTwo;
    private Button tenEighty;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private SharedPreferences preferences;
    private String mBaseFolderPath="";
    private String jpgpng="";
    private String mMediaPath="";
    private RetryPolicy retryPolicy;
    private Uri downloadUri;
    private Uri destinationUri;
    private DownloadRequest downloadRequest;
    private String twentyFourstr;
    private String thirteenSexstr;
    private String fifteenFourstr;
    private String seventyTwostr;
    private String tenEightystr;
    private ImageButton aPlay;
    private ImageButton aShare;
    private ImageButton aDelete;
    private String videoArray,desc,image,video;
    private String saveasvideo;
    private String saveasImage;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialoginfo, container,
                false);

        saveas = (EditText)rootView.findViewById(R.id.saveas);
        saveVideo = (Button)rootView.findViewById(R.id.button);
        final Button saveImage = (Button) rootView.findViewById(R.id.button2);
        copyText = (Button)rootView.findViewById(R.id.button3);
        cancel = (Button)rootView.findViewById(R.id.cancel);
        descripos = (TextView)rootView.findViewById(R.id.textView);
        tsave = (TextView)rootView.findViewById(R.id.textView3);
        tprogress = (TextView)rootView.findViewById(R.id.tprogress);
        mprogress = (ProgressBar)rootView.findViewById(R.id.mprogress);
        thubnail = (ImageView)rootView.findViewById(R.id.imageView2);
        mOptions = (LinearLayout)rootView.findViewById(R.id.op);
        stream = (Button)rootView.findViewById(R.id.stream);


        mOptions = (LinearLayout)rootView.findViewById(R.id.op);
        arrVideoOptions = (LinearLayout)rootView.findViewById(R.id.arrvVideo);
        videoOption = (LinearLayout)rootView.findViewById(R.id.video);

        twentyFour = (Button)rootView.findViewById(R.id.twentyFour);
        thirteenSex = (Button)rootView.findViewById(R.id.thirteenSex);
        fifteenFour = (Button)rootView.findViewById(R.id.fifteenFour);
        seventyTwo = (Button)rootView.findViewById(R.id.seventyTwo);
        tenEighty = (Button)rootView.findViewById(R.id.tenEighty);

        context=getActivity();

        preferences = getActivity().getSharedPreferences(getResources().getString(R.string.pref_appname), Context.MODE_PRIVATE);

        twentyFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 23){

                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(twentyFourstr, "video", twentyFour);
                    }


                }else{

                    downloadManager(twentyFourstr, "video", twentyFour);

                }

            }
        });


        thirteenSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 23){

                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(thirteenSexstr, "video", thirteenSex);

                    }


                }else{

                    downloadManager(thirteenSexstr, "video", thirteenSex);

                }
            }
        });

        fifteenFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 23){

                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(fifteenFourstr , "video" , fifteenFour);

                    }


                }else{

                    downloadManager(fifteenFourstr, "video", fifteenFour);

                }

            }
        });

        seventyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 23){

                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(seventyTwostr, "video", seventyTwo);

                    }


                }else{

                    downloadManager(seventyTwostr, "video", seventyTwo);

                }

            }
        });


        tenEighty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= 23){

                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(tenEightystr, "video", tenEighty);


                    }


                }else{

                    downloadManager(tenEightystr, "video", tenEighty);


                }

            }
        });

        aPlay = (ImageButton)rootView.findViewById(R.id.imageButtonPlay);
        aShare = (ImageButton)rootView.findViewById(R.id.imageButtonShare);
        aDelete = (ImageButton)rootView.findViewById(R.id.imageButtonDelete);

        mprogress.setMax(100);
        mprogress.setProgress(0);

        preferences = getActivity().getSharedPreferences(getResources().getString(R.string.pref_appname), Context.MODE_PRIVATE);

        aPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun.player.mPlayer(mMediaPath, getActivity());
            }
        });
        aShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun.share.mShare(mMediaPath, getActivity());
            }
        });

        aDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(mMediaPath);

                if (file.exists()) {

                    boolean del = file.delete();

                    Toast.makeText(context, "File has been deleted!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        videoArray = getArguments().getString("videoArray");
        video = getArguments().getString("video");
        image = getArguments().getString("image");
        desc = getArguments().getString("desc");

        if(!videoArray.isEmpty()){

            Object json = null;
            try {
                json = new JSONTokener(videoArray).nextValue();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (json instanceof JSONObject){

                try {

                    JSONObject jsArr = new JSONObject(videoArray);

                    for(int j=0;j<jsArr.names().length();j++){

                        videoOption.setVisibility(View.GONE);
                        arrVideoOptions.setVisibility(View.VISIBLE);

                        video = jsArr.getJSONArray(jsArr.names().get(j).toString()).getJSONObject(0).getString("url");

                        switch (jsArr.names().get(j).toString()){

                            case "240" :
                                twentyFour.setVisibility(View.VISIBLE);
                                twentyFourstr = jsArr.getJSONArray(jsArr.names().get(j).toString()).getJSONObject(0).getString("url");
                                break;
                            case "380" :
                                thirteenSex.setVisibility(View.VISIBLE);
                                thirteenSexstr = jsArr.getJSONArray(jsArr.names().get(j).toString()).getJSONObject(0).getString("url");
                                break;
                            case "480" :
                                fifteenFour.setVisibility(View.VISIBLE);
                                fifteenFourstr = jsArr.getJSONArray(jsArr.names().get(j).toString()).getJSONObject(0).getString("url");
                                break;
                            case "720" :
                                seventyTwo.setVisibility(View.VISIBLE);
                                seventyTwostr = jsArr.getJSONArray(jsArr.names().get(j).toString()).getJSONObject(0).getString("url");
                                break;
                            case "1080" :
                                tenEighty.setVisibility(View.VISIBLE);
                                tenEightystr = jsArr.getJSONArray(jsArr.names().get(j).toString()).getJSONObject(0).getString("url");
                                break;
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            //you have an object
            else if (json instanceof JSONArray){

                try {

                    JSONArray jsArr = new JSONArray(videoArray);

                    for(int j=0;j<jsArr.length();j++){

                        videoOption.setVisibility(View.GONE);
                        arrVideoOptions.setVisibility(View.VISIBLE);

                        video = jsArr.getJSONObject(j).getString("url");

                        switch (jsArr.getJSONObject(j).getString("quality")){

                            case "360p" :
                                thirteenSex.setVisibility(View.VISIBLE);
                                thirteenSexstr = jsArr.getJSONObject(j).getString("url");
                                break;
                            case "540p" :
                                fifteenFour.setVisibility(View.VISIBLE);
                                fifteenFourstr = jsArr.getJSONObject(j).getString("url");
                                break;
                            case "720p" :
                                seventyTwo.setVisibility(View.VISIBLE);
                                seventyTwostr = jsArr.getJSONObject(j).getString("url");
                                break;
                            case "1080p" :
                                tenEighty.setVisibility(View.VISIBLE);
                                tenEightystr = jsArr.getJSONObject(j).getString("url");
                                break;
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        if(desc.isEmpty()){

            descripos.setVisibility(View.GONE);
            copyText.setVisibility(View.GONE);
        }

        if(!video.isEmpty()){

            saveasvideo = reg.getBack(video,"([^/]+$)");
            if(saveasvideo.contains("?")){

                saveasvideo = reg.getBack(saveasvideo, "(^[^?]+)");
            }

            saveasvideo = reg.getBack(saveasvideo, "(((?!(.mp4)).)*)");

            saveas.setText(saveasvideo);

        }else{

            saveVideo.setVisibility(View.GONE);
            stream.setVisibility(View.GONE);

        }

        if(!image.isEmpty()){

            saveasImage = reg.getBack(image, "([^/]+)$");

            if(saveasImage.contains("?")){
                saveasImage = reg.getBack(saveasImage, "(^[^?]+)");
            }

            jpgpng = reg.getBack(saveasImage,"(\\.(png|jpg|gif)$)");

            if(jpgpng.isEmpty()){

                jpgpng = (!reg.getBack(saveasImage,"(\\.[A-Za-z]{3})").isEmpty()) ? reg.getBack(saveasImage,"(\\.[A-Za-z]{3})") : ".jpg";

            }

            saveasImage = reg.getBack(saveasImage, "(((?!(.png|.jpg|.gif)).)*)");

            if(image.startsWith("http")){

                Picasso.with(getActivity()).load(image).into(thubnail);
            }

        }else{

            saveImage.setVisibility(View.GONE);
            thubnail.setImageResource(android.R.color.transparent);
        }

        if(video.isEmpty()){saveas.setText(saveasImage);}
        descripos.setText(Html.fromHtml(desc));

        stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fun.player.mPlayerStream(video , getActivity());
            }
        });


        saveVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 23){

                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(video , "video" , saveVideo);

                    }


                }else{

                    downloadManager(video, "video", saveVideo);

                }

            }
        });


        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 23){


                    boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }else{

                        downloadManager(image , "image" , saveImage);

                    }


                }else{

                    downloadManager(image, "image", saveImage);

                }

            }
        });

        copyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) getActivity()
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                final android.content.ClipData clipData = android.content.ClipData
                        .newPlainText("text label", descripos.getText());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getActivity(), "Text Copied!", Toast.LENGTH_LONG).show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return rootView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public void downloadManager(String vid_url , final String type , final Button btn){

        String folderName = getResources().getString(R.string.foldername);

        try {

            if(!preferences.getString("path", "DEFAULT").equals("DEFAULT")){

                mBaseFolderPath = preferences
                        .getString("path", "DEFAULT");

            }else{

                mBaseFolderPath = android.os.Environment
                        .getExternalStorageDirectory()
                        + File.separator
                        + folderName + File.separator;
            }

            if (!new File(mBaseFolderPath).exists()) {
                new File(mBaseFolderPath).mkdir();
            }

            String name = reg.getBack(vid_url, "/([^/]+)$");

            if(type.equals("video") && !saveas.getText().toString().isEmpty()){

                name = saveas.getText().toString() + ".mp4";

            }else if(type.equals("image") && !saveas.getText().toString().isEmpty()){

                name = saveas.getText().toString() + jpgpng;
            }

            mMediaPath = mBaseFolderPath + File.separator + name;

            mprogress.setVisibility(View.VISIBLE);
            tprogress.setVisibility(View.VISIBLE);
            saveas.setVisibility(View.GONE);
            tsave.setVisibility(View.GONE);

            retryPolicy = new DefaultRetryPolicy();


            downloadUri = Uri.parse(vid_url);
            destinationUri = Uri.parse("file://" + mMediaPath);


            if(preferences.getBoolean(getResources().getString(R.string.pref_hidenotification) , false)){

                DownloadManager.Request req = new DownloadManager.Request(downloadUri);
                req.setDestinationUri(destinationUri)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .allowScanningByMediaScanner();

                DownloadManager dm = (DownloadManager) getActivity().getSystemService(getActivity().getApplicationContext().DOWNLOAD_SERVICE);
                dm.enqueue(req);

                Toast toast = Toast.makeText(getActivity(),
                        "Download Started",
                        Toast.LENGTH_SHORT);
                toast.show();

                getDialog().dismiss();

            }else{

                downloadRequest = new DownloadRequest(downloadUri)
                        .addCustomHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                        .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
                        .setRetryPolicy(retryPolicy)
                        .setDownloadContext("Download1")
                        .setStatusListener(new MyDownloadDownloadStatusListenerV1());

                ThinDownloadManager downloadManager = new ThinDownloadManager();


                int downloadId = downloadManager.add(downloadRequest);

                btn.setVisibility(View.GONE);

                /**
                 * Change Text download SaveVideo to Cancel button by setText(""); for user
                 * to be able to cancel download
                 *
                 int status = downloadManager.cancel(downloadId);
                 saveVideo.setText("Cancel");

                 **/

            }


        } catch (Exception e) {

            if(preferences.getBoolean(getResources().getString(R.string.pref_hidenotification) , false)){

                try {

                    downloadRequest = new DownloadRequest(downloadUri)
                            .addCustomHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                            .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
                            .setRetryPolicy(retryPolicy)
                            .setDownloadContext("Download1")
                            .setStatusListener(new MyDownloadDownloadStatusListenerV1());

                    ThinDownloadManager downloadManager = new ThinDownloadManager();


                    int downloadId = downloadManager.add(downloadRequest);
                    btn.setVisibility(View.GONE);

                }catch(Exception j){

                    Log.e("trace",j.getMessage());

                    Toast toast = Toast.makeText(getActivity(),
                            "Download Failed",
                            Toast.LENGTH_SHORT);
                    toast.show();

                }

            }else{

                Log.e("trace",e.getMessage());

                Toast toast = Toast.makeText(getActivity(),
                        "Download Failed",
                        Toast.LENGTH_SHORT);
                toast.show();

            }

        }

        //Video_Managers.IsRUN = false;

    }



    class MyDownloadDownloadStatusListenerV1 implements DownloadStatusListenerV1 {


        @Override
        public void onDownloadComplete(DownloadRequest downloadRequest) {

            tprogress.setText("Download Completed!");
            mOptions.setVisibility(View.VISIBLE);

            try{
                // Tell the media scanner about the new file so that it is
                // immediately available to the user.
                MediaScannerConnection.scanFile(getActivity(), new String[] {mMediaPath }, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
//                                Log.i("ExternalStorage", "Scanned " + path + ":");
//                                Log.i("ExternalStorage", "-> uri=" + uri);
                            }
                        });

            }catch(Exception e){
            }

        }

        @Override
        public void onDownloadFailed(DownloadRequest request, int errorCode, String errorMessage) {
            final int id = request.getDownloadId();
            tprogress.setText("Download id: "+id+" Failed: ErrorCode "+errorCode+", "+errorMessage);
            mprogress.setProgress(0);

        }

        @Override
        public void onProgress(DownloadRequest request, long totalBytes, long downloadedBytes, int progress) {
            int id = request.getDownloadId();

            tprogress.setText("Downloading ... "+progress+"%"+"  "+getBytesDownloaded(progress,totalBytes));
            mprogress.setProgress(progress);

        }

        private String getBytesDownloaded(int progress, long totalBytes) {
            //Greater than 1 MB
            long bytesCompleted = (progress * totalBytes)/100;
            if (totalBytes >= 1000000) {
                return (""+(String.format("%.1f", (float)bytesCompleted/1000000))+ "/"+ ( String.format("%.1f", (float)totalBytes/1000000)) + "MB");
            } if (totalBytes >= 1000) {
                return (""+(String.format("%.1f", (float)bytesCompleted/1000))+ "/"+ ( String.format("%.1f", (float)totalBytes/1000)) + "Kb");

            } else {
                return ( ""+bytesCompleted+"/"+totalBytes );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(getActivity(), "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
