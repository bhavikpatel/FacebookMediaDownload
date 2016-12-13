package fun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class runApp {
	
	public static void start(String packagename, Context context){
		
		if(fun.getlistpackage.doesPackageExist(packagename, context.getApplicationContext())){
			Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(packagename);
			context.startActivity( LaunchIntent );
			
		}else{
			try{
				context.startActivity((new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packagename))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
			}catch(Exception e){
				context.startActivity((new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+packagename))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
			}
		}
		
	}
}
