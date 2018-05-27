package repaireme.com.repairme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by YESH on 27-05-2018.
 */

public class ShowDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details);
        TextView name,address,phoneno,problem,problem_desc;
        Button back_button;

        name=(TextView)findViewById(R.id.tusername);
        address=(TextView)findViewById(R.id.tuser_address);
        phoneno=(TextView)findViewById(R.id.tuser_phone);
        problem=(TextView)findViewById(R.id.tprob);
        problem_desc=(TextView)findViewById(R.id.tprob_desc);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("Name"));
        address.setText(intent.getStringExtra("Address"));
        phoneno.setText(intent.getStringExtra("Phone"));
        problem.setText(intent.getStringExtra("Problem"));
        problem_desc.setText(intent.getStringExtra("ProblemDescription"));

        back_button = (Button)findViewById(R.id.back_button);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start a fragment
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
