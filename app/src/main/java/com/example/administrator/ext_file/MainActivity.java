package com.example.administrator.ext_file;

import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button :
                isWritable();
                break;
            case R.id.button2 :
                isReadable();
                break;
            case R.id.button3 :
                saveText();
                break;
        }
    }

    public void saveText(){
        try{
            String path = Environment.getExternalStorageDirectory()+"/tt";
            File file = new File(path);
            if(!file.exists())
                file.mkdirs();
            file = new File(file,"data.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            String msg ="테스트용 파일입니다.";
            fileOutputStream.write(msg.getBytes());
            fileOutputStream.close();

        }catch(FileNotFoundException e) {
            Log.e("deg",e.toString());
            Toast.makeText(this,"지정된 파일을 생성할 수 없습니다.",Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            Toast.makeText(this,"Cant detected device",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            Toast.makeText(this,"writable success",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public boolean isReadable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            Toast.makeText(this,"readable success",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
