package repaireme.com.repairme;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<Message> adapter;
    RelativeLayout activity_main;

    EditText emojiconEditText;
    Button emojiButton,RepairButton;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main,"You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                Snackbar.make(activity_main,"Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            }
            else{
                Snackbar.make(activity_main,"We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (RelativeLayout)findViewById(R.id.activity_main);


        RepairButton = (Button)findViewById(R.id.repair_button);


        RepairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start a fragment

                Intent i = new Intent(getApplicationContext(), RepairForm.class);
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
            Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
            //Load content
            displayChatMessage();
        }


    }



  private void displayChatMessage() {

        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<Message>(this,Message.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {
            @Override
            protected void populateView(View v, Message model, int position) {

                //Get references to the views of list_item.xml
                TextView messageProblem, messageUser, messageTime,messageName,messageAddress,messagePhone,messageDescription;
                messageProblem = (TextView) v.findViewById(R.id.message_problem);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);
                messageName=(TextView)v.findViewById(R.id.message_name);
                messageAddress=(TextView)v.findViewById(R.id.message_address);
                messagePhone=(TextView)v.findViewById(R.id.message_phone);
                messageDescription=(TextView)v.findViewById(R.id.message_description);

                messageProblem.setText(model.getMessageProb());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
                messageAddress.setText(model.getMessageAddr());
                messageDescription.setText(model.getMessageProbDesc());
                messageName.setText(model.getMessageName());
                messagePhone.setText(model.getMessagePhone());

            }
        };
        listOfMessage.setAdapter(adapter);
      // Item Click Listener for the listview
      AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
              // Getting the Container Layout of the ListView
              RelativeLayout relativeLayout = (RelativeLayout) container;


              String userID=((TextView)relativeLayout.getChildAt(0)).getText().toString();
              String userName=((TextView)relativeLayout.getChildAt(3)).getText().toString();
              String userAddress=((TextView)relativeLayout.getChildAt(4)).getText().toString();
              String userPhone=((TextView)relativeLayout.getChildAt(5)).getText().toString();
              String userProblem=((TextView)relativeLayout.getChildAt(2)).getText().toString();
              String userProblemDescription=((TextView)relativeLayout.getChildAt(6)).getText().toString();

              Intent i = new Intent(getApplicationContext(), ShowDetails.class);
              i.putExtra("Name", userName);
              i.putExtra("ID", userID);
              i.putExtra("Address", userAddress);
              i.putExtra("Phone", userPhone);
              i.putExtra("Problem", userProblem);
              i.putExtra("ProblemDescription", userProblemDescription);
              startActivity(i);
          }
      };

      // Setting the item click listener for the listview
      listOfMessage.setOnItemClickListener(itemClickListener);
    }
}
