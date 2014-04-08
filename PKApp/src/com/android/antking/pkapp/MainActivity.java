
package com.android.antking.pkapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends Activity {
    

    private WebView pk_webView;
    
    private int screen_width;
    private int numColum;
    
    /**
     * 上面三个按钮 自动分析，再次分析，星河战舰
     */
    private TextView btn_zdfx,btn_zcfx,btn_xhzj;
    
    /**
     * 两个按钮 p,k;
     */
    private TextView btn_p,btn_k;
    /**
     * 底部选车按钮
     */
    private TextView btn_xl,btn_stn,btn_kll,btn_yg,btn_ad,btn_bm,btn_bsj,btn_lbjn,btn_bjn;
    
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    pk_webView.loadDataWithBaseURL(null,(String)msg.obj, "text/html", "utf_8",null);
                    break;
            }
        }
    }; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getResources().getDisplayMetrics();
        screen_width = dm.widthPixels;
       
        Log.e("anting_screen", screen_width+""+dm.density);
        int pxitem = Utils.dip2px(getApplicationContext(), 18);
        numColum = (screen_width-10)/(pxitem);
        
        pk_webView = (WebView)this.findViewById(R.id.pk_webView);
        WebSettings webSettings = pk_webView.getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(false);
        initUI();
        loadData();
    }
    private void initUI(){
//        btn_zdfx= (TextView)this.findViewById(R.id.btn_zdfx);
//        btn_zcfx = (TextView)this.findViewById(R.id.btn_zcfx);
//        btn_xhzj = (TextView)this.findViewById(R.id.btn_xhzj);
//        btn_zdfx.setOnClickListener(buttonListener);
//        btn_zcfx.setOnClickListener(buttonListener);
//        btn_xhzj.setOnClickListener(buttonListener);
        
        btn_p = (TextView)this.findViewById(R.id.btn_p);
        btn_p.setOnClickListener(buttonListener);
        btn_k = (TextView)this.findViewById(R.id.btn_k);
        btn_k.setOnClickListener(buttonListener);
        
    }
    
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v==btn_zdfx){
                
            }
            if(v==btn_zcfx){
                
            }
            if(v==btn_xhzj){
                
            }
            if(v==btn_p){
                
            }
            if(v==btn_k){
                
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void  loadData(){
        new Thread(){
            @Override
            public void run(){
                StringBuilder sb = new StringBuilder();
                sb.append("<html><body style='letter-spacing:8px'>");
                for(int i=0;i<50000;i++){
                    int rom = (int)(Math.random()*10)%2;
                    if(i%numColum==0&&i!=0){
                        sb.append("<br>");
                    }
                    if(rom==0){
                        sb.append("<font color='#FF0000'>P</font>");
                        
                    }else if(rom ==1){
                        sb.append("<font color='#0000FF'>K</font>");
                    }
                    
                }
                sb.append("</body></html>");
                Message msg = new Message();
                msg.what = 1;
                msg.obj = sb.toString();
                myHandler.handleMessage(msg);
            }
            
        }.start();
    }

}
