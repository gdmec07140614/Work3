package com.example.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button bt;
    private EditText et;
    private CheckBox manCheckBox;
    private CheckBox womenCheckBox;
    private TextView tv5;


    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et.getText().toString().trim().equals("")) {
                    if (manCheckBox.isChecked() || womenCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(et.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("---------评估结果--------- \n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高为：");
                            double result = evaluateHight(weight, "男");
                            sb.append((int) result + "(厘米)");
                        }
                        if (womenCheckBox.isChecked()) {
                            sb.append("女性标准身高为：");
                            double result = evaluateHight(weight, "女");
                            sb.append((int) result + "(厘米)");
                        }
                        tv5.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");
                    }
                } else {
                    showMessage("请输入体重！");
                }
            }
        });
    }

    private double evaluateHight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    private void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt= (Button) findViewById(R.id.calculator);
        et= (EditText) findViewById(R.id.editText);
        manCheckBox= (CheckBox) findViewById(R.id.man);
        womenCheckBox= (CheckBox) findViewById(R.id.women);
        tv5= (TextView) findViewById(R.id.result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
