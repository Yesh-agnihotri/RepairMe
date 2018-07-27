package repaireme.com.repairme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        TextView name,address,phoneno,problem,problem_desc,company_name,coupon,prod_id,model_no;
        Button back_button;

        name=(TextView)findViewById(R.id.username);
        address=(TextView)findViewById(R.id.user_address);
        phoneno=(TextView)findViewById(R.id.user_phone);
        problem=(TextView)findViewById(R.id.prob);
        problem_desc=(TextView)findViewById(R.id.prob_desc);
        model_no = (TextView) findViewById(R.id.model_no);
        company_name=(TextView)findViewById(R.id.company_name);
        prod_id=(TextView)findViewById(R.id.product_id);
        coupon=(TextView)findViewById(R.id.coupon);

        Intent intent = getIntent();
        Log.d("company mane",intent.getStringExtra("CompanyName"));
        name.setText("Customer Name : "+intent.getStringExtra("Name"));
        address.setText("Customer Address : "+intent.getStringExtra("Address"));
        phoneno.setText("Customer Phone Number : "+intent.getStringExtra("Phone"));
        problem.setText("Customer Problem : "+intent.getStringExtra("Problem"));
        problem_desc.setText("Problem Description : "+intent.getStringExtra("ProblemDescription"));
        company_name.setText("Company Name : "+intent.getStringExtra("CompanyName"));
        model_no.setText("Model Number : "+intent.getStringExtra("ModelNo"));
        prod_id.setText("Product ID : "+intent.getStringExtra("ProdId"));
        coupon.setText(intent.getStringExtra("Coupon"));

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
