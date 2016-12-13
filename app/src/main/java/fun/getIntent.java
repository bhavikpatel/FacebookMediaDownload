package fun;

import android.app.Activity;
import android.os.Bundle;

public class getIntent {
	
	public static String dataIntentText(Activity activity){
		
        //get the received intent
        Bundle extras = activity.getIntent().getExtras();

        return extras.get("android.intent.extra.TEXT").toString();

	}
	
	public static String dataIntentURL(Activity activity){
		
        //get the received intent
        Bundle extras = activity.getIntent().getExtras();
        
        return fun.reg.getBack(extras.get("android.intent.extra.TEXT").toString(), "(http:s?((?!\\s).)*");

	}
}
