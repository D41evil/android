package com.example.test;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main  extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void Enter(View view)
    {
        //((EditText)(findViewById(R.id.pt2))).setText(((EditText)(findViewById(R.id.pt1))).getText().toString());
        if (((EditText) (findViewById(R.id.pt1))).getText().toString().equals("admin"))
        {
            AlertDialog.Builder alt_builder = new AlertDialog.Builder(this);
            alt_builder.setMessage("Подключиться?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Main.this, Second.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = alt_builder.create();
            alert.setTitle("Подтверждение входа");
            alert.show();
        }
    }
}
