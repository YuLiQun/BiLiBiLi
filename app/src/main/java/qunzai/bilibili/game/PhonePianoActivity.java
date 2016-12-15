package qunzai.bilibili.game;

import android.app.ActionBar;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;

public class PhonePianoActivity extends BaseActivity {

    private Button btnQ, btnW,btnE,btnR,btnT,btnY,btnU,btnI,btnO,btnP,btnA,btnS,btnD,btnF,btnG,btnH,btnJ,btnK,btnL,btnZ,btnX,btnC,btnV,btnB,btnN,btnM;
    private TextView tv;
    private int wordcount;
    private Map<Integer, Integer> souMap;
    private SoundPool spool;
    private Spinner sp;


    @Override
    protected int getLayout() {
        return R.layout.activity_phone_piano;
    }

    @Override
    protected void initViews() {
        ActionBar bar = getActionBar();
        if(bar != null){
            bar.hide();
        }
        findID();//绑定控件。
    }

    @Override
    protected void initData() {
        music();//声音初始化
        setListener();//绑定事件源。

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View arg1, int position,
                                       long arg3) {

                String str= parent.getItemAtPosition(position).toString();
                switch (str) {
                    case "月亮代表我的心":
                        tv.setText("LOQSONQS STUVTS QPOOO QPOOO PQPOMPQP LOQSONQS STUVTS QPOOO QPOOO PQPMNOPO QSQPOSN MNMNMLQ SQPOSN MNOOOPQP LOQSONQS STUVTS QPOOO QPOOO PQPMNOPO");
                        break;
                    case "北京欢迎你":
                        tv.setText("POMOPQSPQTSSPO POMOPQSPQTSSQ PQPOSTQMQPPO QSVSTTS QQ SS QS TV WV SQ P S Q Q QS VS TV WV SQ SVT QP QS XW VV");
                        break;
                    case "生日快乐":
                        tv.setText("EEFEHG EEFEIHC EELJHGF KKJHIC");
                        break;
                    case "小星星":
                        tv.setText("OOSSTTS RRQQPPO SSRRQQP SSRRQQP OOSSTTS RRQQPPO");
                        break;
                    case "笑傲江湖曲":
                        tv.setText("MMLJIH JIHFE EFEFHIJL MLJIHI MMLJIH JIHFE EFEFHIJL MLJIHH");
                        break;
                    case "鐵血丹心":
                        tv.setText("JIHGFCF FEC FBC JIHGFCF IHF HIJ JMLMLJLI HFIJLKJ JLMLMLJLI HFIJEGF");
                        break;
                    case "同桌的你":
                        tv.setText("LLLLJKLN MMMMKML LLLLNMLK KKKKJIH OOOOLMOQ PPPPONM NNNNNOPL NNOPONO OOOOLMOQ PPPPONM NNNNNOPL NNOPONO");
                        break;
                    case "想唱就唱":
                        tv.setText("ONOPOL JKKKLJ ONOPOL LMMMON ONOPQOLJ OPOLJ ONOPQOLO RQPOQ QRST OOPQP PQRS SRQPQ QRSTSS UUVUSPQ RQRS QRST OOPQP PQS QUUQV VUVTSOO TSRQRS TS QRST OOPQP PQRS SRQPQ QRSTSS UUVUSPQ RQRS QRST OOPQP PQS QUUQV VUVTSOO TSRQRS");
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void music(){

        //该MAP集合用来保存所有的音乐播放资源
        souMap = new HashMap<>();
        //实例化后台播放池
        spool = new SoundPool(7, AudioManager.STREAM_MUSIC,5);

        //将所有的OGG音调文件加载到spool中,并将加载的顺序与soundMap的KEY对应

        souMap.put(0, spool.load(this, R.raw.sound01, 1));
        souMap.put(1, spool.load(this, R.raw.sound02, 1));
        souMap.put(2, spool.load(this, R.raw.sound03, 1));
        souMap.put(3, spool.load(this, R.raw.sound04, 1));
        souMap.put(4, spool.load(this, R.raw.sound05, 1));
        souMap.put(5, spool.load(this, R.raw.sound06, 1));
        souMap.put(6, spool.load(this, R.raw.sound07, 1));
        souMap.put(7, spool.load(this, R.raw.sound08, 1));
        souMap.put(8, spool.load(this, R.raw.sound09, 1));
        souMap.put(9, spool.load(this, R.raw.sound10, 1));
        souMap.put(10, spool.load(this, R.raw.sound11, 1));
        souMap.put(11, spool.load(this, R.raw.sound12, 1));
        souMap.put(12, spool.load(this, R.raw.sound13, 1));
        souMap.put(13, spool.load(this, R.raw.sound14, 1));
        souMap.put(14, spool.load(this, R.raw.sound15, 1));
        souMap.put(15, spool.load(this, R.raw.sound16, 1));
        souMap.put(16, spool.load(this, R.raw.sound17, 1));
        souMap.put(17, spool.load(this, R.raw.sound18, 1));
        souMap.put(18, spool.load(this, R.raw.sound19, 1));
        souMap.put(19, spool.load(this, R.raw.sound20, 1));
        souMap.put(20, spool.load(this, R.raw.sound21, 1));
        souMap.put(21, spool.load(this, R.raw.sound22, 1));
        souMap.put(22, spool.load(this, R.raw.sound23, 1));
        souMap.put(23, spool.load(this, R.raw.sound24, 1));
        souMap.put(24, spool.load(this, R.raw.sound25, 1));
        souMap.put(25, spool.load(this, R.raw.sound26, 1));
        souMap.put(26, spool.load(this, R.raw.sound27, 1));

    }
    private void findID(){

        btnQ = bindView(R.id.btnQ);
        btnW = bindView(R.id.btnW);
        btnE = bindView(R.id.btnE);
        btnR = bindView(R.id.btnR);
        btnT = bindView(R.id.btnT);
        btnY = bindView(R.id.btnY);
        btnU = bindView(R.id.btnU);
        btnI = bindView(R.id.btnI);
        btnO = bindView(R.id.btnO);
        btnP = bindView(R.id.btnP);

        btnA = bindView(R.id.btnA);
        btnS = bindView(R.id.btnS);
        btnD = bindView(R.id.btnD);
        btnF = bindView(R.id.btnF);
        btnG = bindView(R.id.btnG);
        btnH = bindView(R.id.btnH);
        btnJ = bindView(R.id.btnJ);
        btnK = bindView(R.id.btnK);
        btnL = bindView(R.id.btnL);

        btnZ = bindView(R.id.btnZ);
        btnX = bindView(R.id.btnX);
        btnC = bindView(R.id.btnC);
        btnV = bindView(R.id.btnV);
        btnB = bindView(R.id.btnB);
        btnN = bindView(R.id.btnN);
        btnM = bindView(R.id.btnM);

        sp = (Spinner)findViewById(R.id.song);
        tv = (TextView)findViewById(R.id.tv);
    }

    private void setListener(){

        setClick(new MyListener(),btnQ,btnW,btnE,btnR,btnT,btnY,btnU,btnI,btnO,btnP,btnA,btnS
                ,btnD,btnF,btnG,btnH,btnJ,btnK,btnL,btnZ,btnX,btnC,btnV,btnB,btnN,btnM);

    }

    private class MyListener implements View.OnClickListener {

        public void onClick(View v) {
            Button btn = (Button)v;
            String str1;
            int start = 0, end = 1;

            btn.setTextColor(Color.RED);
            int index = btn.getText().charAt(0)-65;
            int sid = souMap.get(index);
            spool.play(sid, 0.8f, 0.8f, 1, 0, 1f);
            btn.setTextColor(Color.BLACK);

            str1 = tv.getText().toString();
            if(str1.startsWith(" ")){
                str1=str1.substring(1);
            }

            if(btn.getText().equals(str1.substring(0, 1))){
                tv.setText(str1.substring(1));
                if(str1.length() == 1){
                    Toast.makeText(PhonePianoActivity.this, "恭喜你完成此歌曲，请重新选择", Toast.LENGTH_SHORT).show();
                }
                System.out.println(str1);
            }


        }

    }
}

