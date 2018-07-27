package repaireme.com.repairme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by YESH on 27-05-2018.
 */

public class RepairForm extends AppCompatActivity {

    Button SubmitButton;
    Button ApplyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_form);
        final String serviceType= getIntent().getStringExtra("ServiceType");

        TextView prod_detail=(TextView)findViewById(R.id.ProductDetails);
        prod_detail.setText(serviceType+" Details");

        {
            final EditText name = (EditText) findViewById(R.id.username);
            final EditText address = (EditText) findViewById(R.id.user_address);
            final EditText phoneno = (EditText) findViewById(R.id.user_phone);
            final EditText problem = (EditText) findViewById(R.id.prob);
            final EditText problem_desc = (EditText) findViewById(R.id.prob_desc);
            final EditText coupon=(EditText)findViewById(R.id.coupon);
            final EditText model_no = (EditText) findViewById(R.id.model_no);
            final EditText company_name=(EditText)findViewById(R.id.company_name);
            final EditText prod_id=(EditText)findViewById(R.id.product_id);

            SubmitButton = (Button)findViewById(R.id.submit_button);
            ApplyButton=(Button)findViewById(R.id.apply_button);
            //emojiconEditText = (EditText)findViewById(R.id.emojicon_edit_text);
            // emojIconActions = new EmojIconActions(getApplicationContext(),activity_main,emojiButton,emojiconEditText);
            //emojIconActions.ShowEmojicon();

            SubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //for directly merchant
                    if(name.getText().toString().equalsIgnoreCase("RahulMaurya")&&phoneno.getText().toString().equalsIgnoreCase("7836083959"))
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                            Toast.makeText(RepairForm.this,"You have opened merchant view",Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                    else {
                        String tempcoupon;


                        if ((name.getText().toString().equalsIgnoreCase("")) || address.getText().toString().equalsIgnoreCase("") || phoneno.getText().toString().equalsIgnoreCase("") || problem.getText().toString().equalsIgnoreCase("") || company_name.getText().toString().equalsIgnoreCase("") || model_no.getText().toString().equalsIgnoreCase("")) {

                            Toast.makeText(RepairForm.this, "You cannot leave * field empty", Toast.LENGTH_LONG).show();
                        } else {

                            if (coupon.getText().toString().equalsIgnoreCase("Extra250")) {
                                tempcoupon = "Coupon code successfully applied";
                            } else {

                                tempcoupon = "Invalid Coupon Code";
                            }


                            FirebaseDatabase.getInstance().getReference().push().setValue(new Message(name.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), address.getText().toString(), phoneno.getText().toString(), problem.getText().toString(), problem_desc.getText().toString(), company_name.getText().toString(), model_no.getText().toString(), prod_id.getText().toString(), tempcoupon, serviceType));

                            Toast.makeText(RepairForm.this, "Submitted Successfully", Toast.LENGTH_LONG).show();
                            //Start a fragment
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(i);
                        }
                    }
                }
            });
            ApplyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    if(coupon.getText().toString().equalsIgnoreCase("Extra250"))
                    {
                        Toast.makeText(RepairForm.this,"Coupon Successfully Applied",Toast.LENGTH_LONG).show();
                    }
                    else {

                        Toast.makeText(RepairForm.this,"Invalid Coupon Code",Toast.LENGTH_LONG).show();
                    }
                }
            });




        }
    }
}
