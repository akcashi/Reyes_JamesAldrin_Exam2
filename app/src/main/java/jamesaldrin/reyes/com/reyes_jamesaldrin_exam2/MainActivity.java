package jamesaldrin.reyes.com.reyes_jamesaldrin_exam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText Fname, Lname, Exam1, Exam2, Average;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        Exam1 = findViewById(R.id.Exam1);
        Exam2 = findViewById(R.id.Exam2);
        Average = findViewById(R.id.Average);
    }


    public void saveExternal(View v)
    {
        File folder = getExternalFilesDir("TempFolder");
        File file = new File(folder, "Data1.txt");
        String FName = Fname.getText().toString();
        String LName = Lname.getText().toString();
        String EXam1 = Exam1.getText().toString();
        String EXam2 = Exam2.getText().toString();
        int Score1 = Integer.parseInt(EXam1);
        int Score2 = Integer.parseInt(EXam2);
        String average = (Score1 + Score2)/2 + "";

        //String averagedisplay = Float.toString(average);
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data1.txt", MODE_PRIVATE);
            fos.write(FName.getBytes());
            fos.write(LName.getBytes());
            fos.write(average.getBytes());
        } catch (Exception e) {
            Log.d("Error!", "Error!");
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Success! Data placed at " + getExternalFilesDir("Data1.txt"), Toast.LENGTH_SHORT).show();
        loadExternal();

    }

    private void loadExternal()
    {
        File folder = getExternalFilesDir("TempFolder");
        File file = new File(folder, "Data1.txt");
        try {
            FileInputStream fis = openFileInput("Data1.txt");
            int read = 0;
            StringBuffer buffer = new StringBuffer();

            while((read = fis.read()) != -1)
            {
                if(Character.isDigit((char)read))
                buffer.append((char)read);
            }
            String averages = buffer + "!";
            Average.setText(averages);

        } catch (Exception e) {
            Log.d("Error", "ERROR READING DATA!");
        }

    }
}
