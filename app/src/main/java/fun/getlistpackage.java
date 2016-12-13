package fun;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class getlistpackage {
		
    // fetch package for media play
    public static boolean doesPackageExist(String targetPackage ,Context context) {

        PackageManager pm = context.getPackageManager();
        
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return false;
        }
        return true;    
    }

}