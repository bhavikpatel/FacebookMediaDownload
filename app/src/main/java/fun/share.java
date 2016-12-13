package fun;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class share {

    public static void mShare(String filepath, Activity activity){

        Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse(String.valueOf(filepath)));
        File file = new File(filepath);

        if(!filepath.contains(".mp4")){

            intent.setDataAndType(Uri.fromFile(file), "image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            activity.startActivity(Intent.createChooser(intent, "Share Image using"));

        }else{

            intent.setDataAndType(Uri.fromFile(file), "video/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            activity.startActivity(Intent.createChooser(intent, "Share video using"));

        }

    }

    public static void mShareText(String text, Activity activity){

        Intent myapp = new Intent(Intent.ACTION_SEND);
        myapp.setType("text/plain");
        myapp.putExtra(Intent.EXTRA_TEXT, text);
        activity.startActivity(myapp);

    }
}