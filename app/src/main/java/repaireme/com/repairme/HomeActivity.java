package repaireme.com.repairme;

/**
 * Created by YESH on 27-05-2018.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;




    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;

    private Handler handler;
    int page=0;
    int delay=5000;


    Runnable runnable = new Runnable() {
        public void run() {
            if (mCustomPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            mViewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };



    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<Message> adapter;


    EditText emojiconEditText;
    ConstraintLayout CompRepairButton,MobileRepairButton;
    ConstraintLayout activity_home;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_home,"You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(activity_home,"Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();
                //displayChatMessage();
            }
            else{
                Snackbar.make(activity_home,"We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_client);

        handler = new Handler();

        mTextMessage = (TextView) findViewById(R.id.message);


        mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        activity_home = (ConstraintLayout)findViewById(R.id.home_activity);


        CompRepairButton = (ConstraintLayout) findViewById(R.id.SelectComputer);


        CompRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start a fragment

                Intent i = new Intent(getApplicationContext(), RepairForm.class).putExtra("ServiceType", "Computer");;
                startActivity(i);

            }
        });
        MobileRepairButton = (ConstraintLayout) findViewById(R.id.SelectMobile);


        MobileRepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start a fragment

                Intent i = new Intent(getApplicationContext(), RepairForm.class).putExtra("ServiceType", "Mobile");;
                startActivity(i);

            }
        });

        //Check if not sign-in then navigate Signin page
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        else
        {
            Snackbar.make(activity_home,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
            //Load content
            //displayChatMessage();
        }


        ConstraintLayout mDialButton = (ConstraintLayout) findViewById(R.id.btn_dial);

        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String dial = "tel:" + "9838179392";
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        ConstraintLayout mLocateButton=(ConstraintLayout)findViewById(R.id.bttn_map);
        mLocateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=25.4367340,82.8535220(RepairMe WorkShop)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }




    @Override
    protected void onResume()
    {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


}
