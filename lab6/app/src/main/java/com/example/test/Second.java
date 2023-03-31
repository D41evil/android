package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Second extends AppCompatActivity {
    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "CID";

    private static NotificationManager notificationManager;

    private static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Toast toast = Toast.makeText(this, "Добро пожаловать", Toast.LENGTH_LONG);
        toast.show();

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }

        imageView = findViewById(R.id.img1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void Spend(View view)
    {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Уведомление")
                        .setContentText("Заявка отправлена");

        Notification notification = builder.build();
        notificationManager.notify(NOTIFY_ID, notification);
    }

    public void Camera(View view)
    {
        imageView.setImageResource(R.drawable.test);
        View textv = findViewById(R.id.pt3);
        new Thread(() ->
        {
            try
            {
                String content = getContent("https://jsonplaceholder.typicode.com/posts/1");
                textv.post(() -> ((TextView)textv).setText(content));
            }
            catch (IOException ex) {
                textv.post(() -> ((TextView) textv).setText("Ошибка"));
                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    private String getContent(String path) throws IOException
    {
        BufferedReader reader = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;

        try
        {
            URL url = new URL(path);

            connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();

            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buf = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                buf.append(line).append("\n");
            }
            return buf.toString();
        }
        finally
        {
            if (reader != null) reader.close();
            if (stream != null) stream.close();
            if (connection != null) connection.disconnect();
        }
    }
}
