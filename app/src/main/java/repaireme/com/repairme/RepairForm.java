package repaireme.com.repairme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by YESH on 27-05-2018.
 */

public class RepairForm extends AppCompatActivity {

    Button SubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_form);
        {
            final EditText name = (EditText) findViewById(R.id.username);
            final EditText address = (EditText) findViewById(R.id.user_address);
            final EditText phoneno = (EditText) findViewById(R.id.user_phone);
            final EditText problem = (EditText) findViewById(R.id.prob);
            final EditText problem_desc = (EditText) findViewById(R.id.prob_desc);

            SubmitButton = (Button)findViewById(R.id.submit_button);
            //emojiconEditText = (EditText)findViewById(R.id.emojicon_edit_text);
            // emojIconActions = new EmojIconActions(getApplicationContext(),activity_main,emojiButton,emojiconEditText);
            //emojIconActions.ShowEmojicon();

            SubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //for directly merchant
                    if(name.getText().toString().equalsIgnoreCase("Oceino")&&phoneno.getText().toString().equalsIgnoreCase("7836083959"))
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        FirebaseDatabase.getInstance().getReference().push().setValue(new Message(name.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), address.getText().toString(), phoneno.getText().toString(), problem.getText().toString(), problem_desc.getText().toString()));

                        //Start a fragment
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                    }
                }
            });




        }
    }
}
