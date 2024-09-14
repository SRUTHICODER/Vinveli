package com.example.vinveli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tv;
    TextView tv1;
    String MissionSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv = findViewById(R.id.textViewMissionSummary);
        tv1=findViewById(R.id.textViewSummaryContent);
        Intent intent = getIntent();
        String mission = intent.getStringExtra("mission");
        tv.setText(mission);

        if (mission.equals("Aryabhata (1975)")) {
              MissionSummary="Aryabhata, launched in 1975, marked India's first step into space exploration. It was a satellite rather than a moon mission," +
                      " designed for conducting scientific experiments in X-ray astronomy, aeronomy, and solar physics. " +
                      "Aryabhata's successful launch by India made the country a notable player in the field of space research and technology.";
        }

        else if (mission.equals("Mangalyaan (Mars Orbiter Mission) (2013)")) {
              MissionSummary="Mangalyaan, also known as the Mars Orbiter Mission, was an Indian space mission launched in 2013. " +
                      "It aimed to explore Mars and study its surface, atmosphere, and mineralogy. Notably, it was India's first interplanetary mission and achieved global recognition " +
                      "for its successful insertion into Martian orbit at a remarkably low cost, showcasing India's space capabilities.";
        }

        else if (mission.equals("Chandrayaan-1 (2008)")) {
              MissionSummary="Chandrayaan-1, India's first lunar mission launched in 2008, aimed to explore the Moon's surface and detect water molecules. " +
                      "It discovered water ice in polar regions and provided valuable data about the Moon's mineral composition and topography. " +
                      "The mission ended prematurely due to a communication loss, " +
                      "but its findings significantly advanced lunar science and India's space exploration capabilities.";
        }

        else if (mission.equals("Chandrayaan-3")) {
             MissionSummary="Chandrayaan-3 is India's third lunar mission, planned to land on the Moon. It is a follow-up to Chandrayaan-2, " +
                     "focusing on a soft landing attempt. Chandrayaan-3 aims to further explore the lunar surface, study its geology, and " +
                     "potentially conduct experiments. The mission seeks to build upon the " +
                     "successes and lessons learned from previous lunar missions and contribute to India's space exploration endeavors.";
        }

        else if (mission.equals("RISAT Satellites")) {
             MissionSummary="RISAT (Radar Imaging Satellite) satellites are a series of Indian Earth observation spacecraft equipped with " +
                     "synthetic aperture radar (SAR) technology. They provide all-weather, day-and-night imaging capabilities for applications such as agriculture, " +
                     "forestry, and disaster management. RISAT satellites are critical tools for monitoring " +
                     "and managing land and water resources, environmental changes, and disaster response.";
        }

        else if (mission.equals("Aditya-L1 (Upcoming)")) {
             MissionSummary="Aditya-L1, an upcoming Indian lunar mission, aims to study the Sun's outermost layer, the solar corona. " +
                     "It will be equipped with advanced instruments to observe solar activities and their impact on Earth's climate and space weather. " +
                     "Aditya-L1's primary goal is to enhance our understanding of solar phenomena, " +
                     "ultimately contributing to space weather forecasting and its implications on communication and navigation systems.";
        }

        else if (mission.equals("GSAT Satellites")) {
            MissionSummary="The GSAT (Geo-Stationary Satellite) series of Indian communication satellites aims to enhance telecommunications, " +
                    "broadcasting, and broadband services. These satellites are positioned in geostationary orbits, " +
                    "providing extensive coverage across India and neighboring regions. They play a crucial role in connecting remote areas, " +
                    "supporting telecommunication infrastructure, " +
                    "and improving internet connectivity, contributing to India's digital communication development.";
        }
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();

        if(mission!=null){
            dbpopulate(mission,MissionSummary,tv1);
        }
    }
    public void dbpopulate(String mission, String MissionSummary, TextView tv1){
        String content;
        ContentValues values=new ContentValues();
        values.put("mission_title",mission);
        values.put("mission_summary",MissionSummary);
        db.insert("moon_missions",null,values);
        StringBuffer buffer=new StringBuffer();
        Cursor c=db.rawQuery("select * from moon_missions",null);
        while(c.moveToNext())
        {
            buffer.append("\t"+c.getString(0));
            buffer.append("\t"+c.getString(1));
        }
        Toast.makeText(this,buffer.toString(),Toast.LENGTH_LONG).show();



        tv1.setText(MissionSummary);


    }
}
