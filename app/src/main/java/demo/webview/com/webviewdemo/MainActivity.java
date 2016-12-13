package demo.webview.com.webviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    WebView webview;
    Button btnBack,btnNext;
    Button btnMenu;
    Context context;
    private ProgressBar progressBar;
    private String imagina="";
    private String video="";
    private String videoArray="";
    private String desc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        btnBack=(Button)findViewById(R.id.btnBack);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnMenu=(Button)findViewById(R.id.btnMenu);
        webview=(WebView)findViewById(R.id.webview);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        webview.loadUrl("https://m.facebook.com/");
        //webview.loadUrl("https://m.youtube.com/");
       // webview.loadUrl("https://google.com/");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new WebAppInterface(this), "Android");

      //  webview.setWebViewClient(new WebViewClient());
        //webview.setWebViewClient(new MyWebViewClient(this));

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                loadScript();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //super.onLoadResource(view, url);
                loadeResource();
            }
        });
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
                    webview.goBack();
                    return true;
                }
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    webview.goBack();
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webview.canGoForward()){
                    webview.goForward();
                }
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MediaDownloadOptionsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadeResource() {

        webview.loadUrl("javascript:(function prepareVideo() { "
                + "var el = document.querySelectorAll('div[data-sigil]');"
                + "var s =  document.getElementsByClassName('_4fmw grouped aclb')[0];"
                + "if(s){s.innerHTML=\"\";}"
                + "for(var i=0;i<el.length; i++)"
                + "{"
                + "var sigil = el[i].dataset.sigil;"
                + "if(sigil.indexOf('inlineVideo') > -1){"
                + "delete el[i].dataset.sigil;"
                + "var jsonData = JSON.parse(el[i].dataset.store);"
                + "el[i].setAttribute('onClick', 'mJava.getData(\"'+jsonData['src']+'\");');"
                + "}" + "}"


                +"\n" +
                "function getparent(parent){\n" +
                "\tif(typeof(parent.parentNode) !== \"undefined\"){\n" +
                "\t\tif(parent.parentNode.tagName == \"SECTION\"){\n" +
                "    \t\treturn parent.parentNode;\n" +
                "\t\t}else{\n" +
                "\t\t\treturn getparent(parent.parentNode);\n" +
                "\t\t}\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "function removeHref(parent){\n" +
                "\n" +
                "\tfor(i=0;i< parent.childNodes.length;i++){\n" +
                "\t\tif(parent.childNodes[i].tagName == \"A\"){\n" +
                "\t\t\tparent.removeChild(parent.childNodes[i]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "var imgsrc = document.querySelectorAll('._5s61 > i');\n" +
                "\n" +
                "for (j=0;j<imgsrc.length;j++){\n" +
                "\tvar child = imgsrc[j];\n" +
                "\tif (typeof(child.parentNode) !== \"undefined\" \n" +
                "\t&& typeof(child.parentNode.parentNode) !== \"undefined\") {\n" +
                "\t\tvar parent = child.parentNode;\n" +
                "        style = child.currentStyle || window.getComputedStyle(child, false);\n" +
                "        bi = style.backgroundImage.slice(4, -1).replace(/\"/g, \"\");\n" +
                "        if(typeof(bi.split(\"url=\")[1]) !== \"undefined\"){\n" +
                "        \tvar parentGetFromFunction = getparent(parent);\n" +
                "        \tparentGetFromFunction.setAttribute('onClick', 'mJava.getData(\"'+decodeURIComponent(bi.split(\"url=\")[1].split(\"&\")[0])+'\");');\n" +
                "        \tremoveHref(parentGetFromFunction);\n" +
                "        }\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "var gif = document.querySelectorAll('._5s61 > ._4o54 > i');\n" +
                "for (j=0;j<gif.length;j++){\n" +
                "\tvar child = gif[j];\n" +
                "\tif (typeof(child.parentNode) !== \"undefined\" \n" +
                "\t&& typeof(child.parentNode.parentNode.parentNode.parentNode) !== \"undefined\") {\n" +
                "\t\tvar parent = child.parentNode;\n" +
                "        style = child.currentStyle || window.getComputedStyle(child, false);\n" +
                "        bi = style.backgroundImage.slice(4, -1).replace(/\"/g, \"\");\n" +
                "\n" +
                "        if(typeof(bi.split(\"url=\")[1]) !== \"undefined\"){\n" +
                "        \tvar parentGetFromFunction = getparent(parent);\n" +
                "        \tparentGetFromFunction.setAttribute('onClick', 'mJava.getData(\"'+decodeURIComponent(bi.split(\"url=\")[1].split(\"&\")[0])+'\");');\n" +
                "        \tremoveHref(parentGetFromFunction);\n" +
                "        }\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "var images = document.querySelectorAll('._5sgg > i');\n" +
                "for(i=0;i<images.length;i++){\n" +
                "\tstyle = images[i].currentStyle || window.getComputedStyle(images[i], false);\n" +
                "        bi = style.backgroundImage.slice(4, -1).replace(/\"/g, \"\");\n" +
                "        var parent = images[i].parentNode.parentNode;\n" +
                "\tif(parent.tagName == \"A\"){\n" +
                "\t\tparent.setAttribute('onClick', 'mJava.getData(\"'+bi+'\");');\n" +
                "\t\tparent.removeAttribute('href');\n" +
                "\t\tparent.removeAttribute('data-autoid')\n" +
                "\t}else if(parent.parentNode.tagName == \"A\"){\n" +
                "\t\tparent.parentNode.setAttribute('onClick', 'mJava.getData(\"'+bi+'\");');\n" +
                "\t\tparent.parentNode.removeAttribute('href');\n" +
                "\t}\n" +
                "}\n" +
                "\n"


                + "})()");
        webview.loadUrl("javascript:( window.onload=prepareVideo;"
                + ")()");
    }

    private void loadScript() {

        webview.loadUrl("javascript:(function() { "
                + "var el = document.querySelectorAll('div[data-sigil]');"
                + "var s =  document.getElementsByClassName('_4fmw grouped aclb')[0];"
                + "if(s){s.innerHTML=\"\";}"
                + "for(var i=0;i<el.length; i++)"
                + "{"
                + "var sigil = el[i].dataset.sigil;"
                + "if(sigil.indexOf('inlineVideo') > -1){"
                + "delete el[i].dataset.sigil;"
                + "var jsonData = JSON.parse(el[i].dataset.store);"
                + "el[i].setAttribute('onClick', 'mJava.getData(\"'+jsonData['src']+'\");');"
                + "}" + "}"

                +"\n" +
                "function getparent(parent){\n" +
                "\tif(typeof(parent.parentNode) !== \"undefined\"){\n" +
                "\t\tif(parent.parentNode.tagName == \"SECTION\"){\n" +
                "    \t\treturn parent.parentNode;\n" +
                "\t\t}else{\n" +
                "\t\t\treturn getparent(parent.parentNode);\n" +
                "\t\t}\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "function removeHref(parent){\n" +
                "\n" +
                "\tfor(i=0;i< parent.childNodes.length;i++){\n" +
                "\t\tif(parent.childNodes[i].tagName == \"A\"){\n" +
                "\t\t\tparent.removeChild(parent.childNodes[i]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "var imgsrc = document.querySelectorAll('._5s61 > i');\n" +
                "\n" +
                "for (j=0;j<imgsrc.length;j++){\n" +
                "\tvar child = imgsrc[j];\n" +
                "\tif (typeof(child.parentNode) !== \"undefined\" \n" +
                "\t&& typeof(child.parentNode.parentNode) !== \"undefined\") {\n" +
                "\t\tvar parent = child.parentNode;\n" +
                "        style = child.currentStyle || window.getComputedStyle(child, false);\n" +
                "        bi = style.backgroundImage.slice(4, -1).replace(/\"/g, \"\");\n" +
                "        if(typeof(bi.split(\"url=\")[1]) !== \"undefined\"){\n" +
                "        \tvar parentGetFromFunction = getparent(parent);\n" +
                "        \tparentGetFromFunction.setAttribute('onClick', 'mJava.getData(\"'+decodeURIComponent(bi.split(\"url=\")[1].split(\"&\")[0])+'\");');\n" +
                "        \tremoveHref(parentGetFromFunction);\n" +
                "        }\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "var gif = document.querySelectorAll('._5s61 > ._4o54 > i');\n" +
                "for (j=0;j<gif.length;j++){\n" +
                "\tvar child = gif[j];\n" +
                "\tif (typeof(child.parentNode) !== \"undefined\" \n" +
                "\t&& typeof(child.parentNode.parentNode.parentNode.parentNode) !== \"undefined\") {\n" +
                "\t\tvar parent = child.parentNode;\n" +
                "        style = child.currentStyle || window.getComputedStyle(child, false);\n" +
                "        bi = style.backgroundImage.slice(4, -1).replace(/\"/g, \"\");\n" +
                "\n" +
                "        if(typeof(bi.split(\"url=\")[1]) !== \"undefined\"){\n" +
                "        \tvar parentGetFromFunction = getparent(parent);\n" +
                "        \tparentGetFromFunction.setAttribute('onClick', 'mJava.getData(\"'+decodeURIComponent(bi.split(\"url=\")[1].split(\"&\")[0])+'\");');\n" +
                "        \tremoveHref(parentGetFromFunction);\n" +
                "        }\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "var images = document.querySelectorAll('._5sgg > i');\n" +
                "for(i=0;i<images.length;i++){\n" +
                "\tstyle = images[i].currentStyle || window.getComputedStyle(images[i], false);\n" +
                "        bi = style.backgroundImage.slice(4, -1).replace(/\"/g, \"\");\n" +
                "        var parent = images[i].parentNode.parentNode;\n" +
                "\tif(parent.tagName == \"A\"){\n" +
                "\t\tparent.setAttribute('onClick', 'mJava.getData(\"'+bi+'\");');\n" +
                "\t\tparent.removeAttribute('href');\n" +
                "\t\tparent.removeAttribute('data-autoid')\n" +
                "\t}else if(parent.parentNode.tagName == \"A\"){\n" +
                "\t\tparent.parentNode.setAttribute('onClick', 'mJava.getData(\"'+bi+'\");');\n" +
                "\t\tparent.parentNode.removeAttribute('href');\n" +
                "\t}\n" +
                "}\n" +
                "\n"

                + "})()");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @JavascriptInterface
    public void getData(String pathvideo){


        if(pathvideo.contains(".mp4")){

            imagina = "";
            video = pathvideo;

        }else{

            video = "";
            imagina = pathvideo;
        }

        mDialog();

    }

    public void mDialog(){

        if(!video.isEmpty() || !imagina.isEmpty() || !videoArray.isEmpty()){

            FragmentManager fm = getSupportFragmentManager();
            InfoDialogFragment info = new InfoDialogFragment();
            Bundle args = new Bundle();
            args.putString("videoArray", videoArray);
            args.putString("video", video);
            args.putString("image", imagina);
            if(desc.length() > 300){desc=desc.substring(0,300);}
            args.putString("desc", desc);
            info.setArguments(args);
            info.show(fm, "fragment_info");

        }else{

            Toast.makeText(context, "There is No results try again with new Link!", Toast.LENGTH_LONG).show();
        }
    }
}
