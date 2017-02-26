package com.example.administrator.androidofdialogbox;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //点击按钮，弹出一个普通对话框
    public void click1(View v){
        //用builder构造器来构造
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("谢嘉华有喜欢的女人");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("点击了确定按钮！");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("点击了取消的按钮！！");
            }
        });

        //最后一步，一定要记住  和Toast一样一定要show()出来
        builder.show();

    }
    //点击按钮，弹出一个单选对话框
    public void click2(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("请选择你喜欢的人");
        final String[]item={"科比","库里","佳漫","汤普森","杜兰特"};
        builder.setSingleChoiceItems(item, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //【1】把选择的条目取出来
                String choice=item[which];

                Toast.makeText(getApplicationContext(),"增杭喜欢"+choice,Toast.LENGTH_SHORT).show();
                //【2】把对话框关闭
                dialog.dismiss();
            }
        });
        builder.show();
    }
    //点击按钮，弹出一个复选对话框
    public void click3(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final String item[]={"篮球","健身","游泳","跑步","约会","看电影"};
        final boolean[]checkItem={true,false,false,false,false,false};
        builder.setMultiChoiceItems(item, checkItem, new DialogInterface.OnMultiChoiceClickListener() {

            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //把选中的条目显示出来
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer buffer=new StringBuffer();
                for(int i=0;i<item.length;i++){
                    //判断一下条目是否被选中
                    if(checkItem[i]){
                        String check=item[i];
                        buffer.append(check+"   ");
                    }

                }
                Toast.makeText(getApplicationContext(),"增杭喜欢"+buffer.toString(),Toast.LENGTH_SHORT).show();
                //关闭对话框
                dialog.dismiss();
            }
        });

        builder.show();
    }
    //点击按钮 弹出进度条对话框
    public void click4(View v){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("拼命加载中！！！！");

        //设置一下进度条的样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        //最后一步记得show（）；出来
        dialog.show();

        //创建一个子线程:与进度相关的可以在线程中更新Ui
        new Thread(){
             public void run() {
                    //设置进度条最大值
                 dialog.setMax(100);
                 //设置当前的进度
                 for(int i=0;i<100;i++){
                     dialog.setProgress(i);
                    //睡眠一会
                     SystemClock.sleep(50);
                 }
                 //关闭对话框
                 dialog.dismiss();
            }
        }.start();

    }
}
