
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
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    private StringBuilder pk_random;
    
    //Pk随机显示数量
    private int pk_number = 50000;
    
    //Pk等级
    private int pk_level = 6;
    
    //最低级level
    private static final int BASE_LEVEL = 6;
    
    //密码
    private static final String PASSWORD = "xinghezhanjian573hao";
    
    private String[] pks;
    
    private StringBuilder pksSb;
    

    private WebView pk_webView;
    
    private int screen_width;
    private int numColum;
    
    /**
     * 上面三个按钮 自动分析，再次分析，星河战舰
     */
    private RadioButton btn_zdfx,btn_zcfx,btn_xhzj;
    private RadioGroup btn_group1;
    /**
     * 两个按钮 p,k;删除按钮
     */
    private TextView btn_p,btn_k,btn_del;
    //输入框
    private TextView pk_view;
    /**
     * 底部选车按钮
     */
    private RadioButton btn_xl,btn_stn,btn_kll,btn_yg,btn_ad,btn_bm,btn_bsj,btn_lbjn,btn_bjd;
    private RadioGroup btn_group;
    
    //开始显示的密码选择框
    private EditText first_edit;
    //提交按钮
    private TextView btn_submit;
    
    private RelativeLayout pas_content;
    
    private HorizontalScrollView group_sv;
    
    private TextView btn_sj;
    
    private TextView pk_type;
    
    private LinearLayout pk_change_content;
    
    //修改输入框
    private EditText edit_num,edit_level;
    //修改按钮
    private TextView btn_num,btn_level;
    
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    pk_webView.loadDataWithBaseURL(null,(String)msg.obj, "text/html", "utf_8",null);
                    break;
                case 2:
//                    pk_webView.setLayoutParams(new FrameLayout.LayoutParams(screen_width, FrameLayout.LayoutParams.MATCH_PARENT));
                    pk_webView.loadDataWithBaseURL(null,(String)msg.obj, "text/html", "utf_8",null);
                    break;
            }
        }
    }; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getResources().getDisplayMetrics();
        screen_width = dm.widthPixels;
       
        Log.e("anting_screen", screen_width+""+dm.density);
        int pxitem = Utils.dip2px(getApplicationContext(), 18);
        numColum = (screen_width-10)/(pxitem);
        
        pks = new String[pk_level];
        pksSb = new StringBuilder(pk_level);
        
        pk_webView = (WebView)this.findViewById(R.id.pk_webView);
        WebSettings webSettings = pk_webView.getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(false);
        initUI();
        btn_zdfx.setChecked(true);
        loadData(pk_number);
    }
    private void initUI(){
        pk_change_content = (LinearLayout)this.findViewById(R.id.pk_change_content);
        btn_group1 = (RadioGroup)this.findViewById(R.id.btn_group1);
        btn_zdfx= (RadioButton)this.findViewById(R.id.btn_zdfx);
        btn_zcfx = (RadioButton)this.findViewById(R.id.btn_zcfx);
        btn_xhzj = (RadioButton)this.findViewById(R.id.btn_xhzj);
        btn_zdfx.setOnClickListener(buttonListener);
        btn_zcfx.setOnClickListener(buttonListener);
        btn_xhzj.setOnClickListener(buttonListener);
        
        btn_p = (TextView)this.findViewById(R.id.btn_p);
        btn_p.setOnClickListener(buttonListener);
        btn_k = (TextView)this.findViewById(R.id.btn_k);
        btn_k.setOnClickListener(buttonListener);
        btn_del =(TextView)this.findViewById(R.id.btn_delate);
        btn_del.setOnClickListener(buttonListener);
        pk_view = (TextView)this.findViewById(R.id.pks_view);
        pas_content = (RelativeLayout)this.findViewById(R.id.pas_content);
        
        first_edit = (EditText)this.findViewById(R.id.first_edit);
        btn_submit = (TextView)this.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(buttonListener);
        
        btn_group = (RadioGroup)this.findViewById(R.id.btn_group);
        btn_xl = (RadioButton)this.findViewById(R.id.btn_xl);
        
        btn_xl.setOnClickListener(rbtListener);
        btn_stn = (RadioButton)this.findViewById(R.id.btn_stn);
        btn_stn.setOnClickListener(rbtListener);
        btn_kll = (RadioButton)this.findViewById(R.id.btn_kll);
        btn_kll.setOnClickListener(rbtListener);
        btn_yg = (RadioButton)this.findViewById(R.id.btn_yg);
        btn_yg.setOnClickListener(rbtListener);
        btn_ad = (RadioButton)this.findViewById(R.id.btn_ad);
        btn_ad.setOnClickListener(rbtListener);
        btn_bm = (RadioButton)this.findViewById(R.id.btn_bm);
        btn_bm.setOnClickListener(rbtListener);
        btn_bsj = (RadioButton)this.findViewById(R.id.btn_bsj);
        btn_bsj.setOnClickListener(rbtListener);
        btn_lbjn = (RadioButton)this.findViewById(R.id.btn_lbjn);
        btn_lbjn.setOnClickListener(rbtListener);
        btn_bjd = (RadioButton)this.findViewById(R.id.btn_bjd);
        btn_bjd.setOnClickListener(rbtListener);
        group_sv = (HorizontalScrollView)this.findViewById(R.id.group_hs);
        checkBtn();
        
        btn_sj = (TextView)this.findViewById(R.id.btn_sj);
        btn_sj.setOnClickListener(buttonListener);
        pk_type = (TextView)this.findViewById(R.id.pk_type);
        
        edit_num = (EditText)this.findViewById(R.id.edit_num);
        edit_level = (EditText)this.findViewById(R.id.edit_level);
        btn_num = (TextView)this.findViewById(R.id.btn_num);
        btn_level = (TextView)this.findViewById(R.id.btn_level);
        btn_num.setOnClickListener(buttonListener);
        btn_level.setOnClickListener(buttonListener);
        
    }
    private View.OnClickListener rbtListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            checkBtn();
            
           
        }
    };
    private void checkBtn(){
        int length = btn_group.getChildCount();
        int count = pk_level-BASE_LEVEL;
        if(count<length&&count>=0){
            View v = btn_group.getChildAt(count);
            btn_group.check(v.getId());
            int oldX = (int)v.getX();
            group_sv.smoothScrollTo((oldX-screen_width/2), 0);
        }else{
            btn_group.clearCheck();
        }
    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v==btn_zdfx){
                btn_zdfx.setChecked(true);
                pk_change_content.setVisibility(View.GONE);
                loadData(pk_number);
            }
            if(v==btn_zcfx){
                btn_zcfx.setChecked(true);
                pk_change_content.setVisibility(View.GONE);
                loadData(pk_number);
            }
            if(v==btn_xhzj){
                pk_change_content.setVisibility(View.VISIBLE);
//                loadData(pk_number);
            }
            if(v==btn_p){
                playPks("P");
            }
            if(v==btn_k){
                playPks("K");
            }
            if(v==btn_del){
                delPks();
            }
            if(v==btn_submit){
                checkPas();
            }
            if(v==btn_sj){
               

            }
            if(v==btn_num){
                int num = Utils.toInt(edit_num.getText().toString().trim(),0);
                if(num<=0){
                    return;
                }else{
                    pk_number= num;
                }
            }
            if(v==btn_level){
                int num = Utils.toInt(edit_level.getText().toString().trim(),0);
                if(num<=6&&num>=20){
                    return;
                }else{
                    pk_level= num;
                    checkBtn();
                }
            }
        }
    };
    private void playPKType(){
         String s = pk_view.getText().toString().trim();
         String sb = pk_random.toString().trim();
         int index = sb.indexOf(s);
         if(index<0){
             pk_type.setText(Utils.getDate()+" 未发现");
             return;
         }
         Log.e("antking_index", ""+index);
         char c = sb.charAt(index+pk_level);
         if(String.valueOf(c).equals("P")){
             pk_type.setText(Utils.getDate()+" P两倍，K一倍");
         }else{
             pk_type.setText(Utils.getDate()+" P一倍，K两倍");
         }
//         findThePk(s);
    }
    private void findThePk(final String s){
        new Thread(){
            @Override
            public void run(){
                if(pk_random.length()<=0)
                    return;
                String rondom = "<html><body style='letter-spacing:8px'>"+pk_random.toString().trim()+"</body></html>";
                rondom = rondom.replace(s, "<span style='background:#FFFF00'>"+s+"</span>");               
                rondom = rondom.replace("P", "<font color='#FF0000'>P</font>");
                rondom = rondom.replace("K", "<font color='#0000FF'>K</font>");
//                for(int i=0,temp=0;i<pk_number;i++,temp = rondom.indexOf("</font>", temp)){
//                  if(i%numColum==0&&i!=0){
//                        rondom= rondom.substring(0, temp)+"<br>"+rondom.substring(temp);
//                    }
//                }
                Message msg = new Message();
                msg.what= 2;
                msg.obj = rondom;
                myHandler.sendMessage(msg);
            }
        }.start();
    }
    private void checkPas(){
        String s = first_edit.getText().toString().trim();
//        if(s.equals(PASSWORD)){
            pas_content.setVisibility(View.GONE);
//        }else{
//           Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
//        }
    }
    private void delPks(){
        int length = pksSb.length();
        if(length>0)
        pksSb.deleteCharAt(length-1);
        pk_view.setText(pksSb.toString());
    }
    private void playPks(String s){
        pksSb.append(s);
        int length = pksSb.length();
        if(length>pk_level){
            pksSb.delete(0, (length-pk_level));
        }
        if(pksSb.length()==pk_level){
            playPKType();
        }
        pk_view.setText(pksSb.toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void  loadData(final int number){
        new Thread(){
            @Override
            public void run(){
                StringBuilder sb = new StringBuilder();
                pk_random = new StringBuilder();
                sb.append("<html><body style='letter-spacing:8px'>");
                for(int i=0;i<number;i++){
                    int rom = (int)(Math.random()*10)%2;
                    if(i%numColum==0&&i!=0){
                        sb.append("<br>");
                        
                    }
                    if(rom==0){
                        sb.append("<font color='#FF0000'>P</font>");
                        pk_random.append("P");
                        
                    }else if(rom ==1){
                        sb.append("<font color='#0000FF'>K</font>");
                        pk_random.append("K");
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
