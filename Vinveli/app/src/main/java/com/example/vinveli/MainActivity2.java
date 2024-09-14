package com.example.vinveli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity2 extends AppCompatActivity {

    TextView tv;
    ImageView iv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.imageViewMission);

        Intent intent = getIntent();
        String mission = intent.getStringExtra("mission");
        tv.setText(mission);
        String imageUrl = "";

        if (mission.equals("Aryabhata (1975)")) {
            imageUrl = "https://space.skyrocket.de/img_sat/aryabhata-1__1.jpg";
        } else if (mission.equals("Mangalyaan (Mars Orbiter Mission) (2013)")) {
            imageUrl = "https://cdn.mos.cms.futurecdn.net/EnMM9kix2PgEEcnH4LXeQg-1200-80.png";
        } else if (mission.equals("Chandrayaan-1 (2008)")) {
            imageUrl = "https://akm-img-a-in.tosshub.com/indiatoday/images/bodyeditor/201910/chandrayaan_2_mission_payloads-x433.jpg?hCgePyVYgQAo6cgSKrF5EjQrO1bnVuUn?size=750:*";
        } else if (mission.equals("Chandrayaan-3")) {
            imageUrl = "https://im.rediff.com/news/2023/aug/23vikran-pragyan.jpg?w=670&h=900";
        } else if (mission.equals("RISAT Satellites")) {
            imageUrl = "https://www.newsbharati.com/Encyc/2019/5/9/2_06_28_26_ISRO-_1_H@@IGHT_346_W@@IDTH_700.jpg";
        } else if (mission.equals("Aditya-L1 (Upcoming)")) {
            imageUrl = "https://images.hindustantimes.com/tech/img/2023/08/27/960x540/solar_orbiter_1648650693184_1693110776754.jpg";
        } else if (mission.equals("GSAT Satellites")) {
            imageUrl = "https://static.theprint.in/wp-content/uploads/2020/01/GSAT-30.jpg";
        }

        if (!imageUrl.isEmpty()) {
            new ImageDownloader().execute(imageUrl
            );
        }
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        HttpsURLConnection httpURLConnection;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                iv.setImageBitmap(bitmap);
                showNotification("Download successful");
            } else {
                showNotification("Download Failed");
            }
        }
    }

    private void showNotification(String message) {
        // Create a notification channel if running on Android Oreo or higher
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("image_download_channel", "Image Download", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create an intent to open the app when the notification is tapped
        Intent intent = new Intent(this, MainActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        // Build the notification
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this, "image_download_channel")
                    .setContentTitle("Image Download")
                    .setContentText(message)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        // Show the notification
        NotificationManager notificationManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            notificationManager = getSystemService(NotificationManager.class);
        }
        notificationManager.notify(1, notification);
    }
}
