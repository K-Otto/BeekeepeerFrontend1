package com.otto.beekeeperbackendfinal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.otto.beekeeperbackendfinal.Model.User;
import com.otto.beekeeperbackendfinal.Repositories.rest.RestUserAPI;

import java.util.LinkedList;
import java.util.List;

public class Add_Person extends AppCompatActivity {


    Button btnSave;
    EditText txtStaffName, txtStaffSurname, txtUserName, txtUserPass;
    int id = 0;

    //STAFF
    static User userer = new User();
    List<User> staff = new LinkedList<User>();
    RestUserAPI restUserAPI = new RestUserAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__person);
        txtStaffName = (EditText) findViewById(R.id.txtStaffName);
        txtStaffSurname = (EditText) findViewById(R.id.txtStaffSurname);
        txtUserName = (EditText) findViewById(R.id.txtUserN);
        txtUserPass = (EditText) findViewById(R.id.txtPass);



        btnSave = (Button) findViewById(R.id.btnStaffSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id == 0){
                    userer.setName(txtStaffName.getText().toString());
                    userer.setSurname(txtStaffSurname.getText().toString());
                    userer.setPhone(txtUserName.getText().toString());
                    userer.setIDnumber(txtUserPass.getText().toString());

                }else{

                    userer.setName(txtStaffName.getText().toString());
                    userer.setSurname(txtStaffSurname.getText().toString());
                    userer.setPhone(txtUserName.getText().toString());
                    userer.setIDnumber(txtUserPass.getText().toString());

                }
                new HttpRequestTask().execute();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {

            userer.setName(extras.getString("firstName"));
            userer.setSurname(extras.getString("lastName"));
            userer.setPhone(extras.getString("username"));
            userer.setIDnumber(extras.getString("password"));



            txtStaffName.setText(userer.getName());
            txtStaffSurname.setText(userer.getSurname());
            txtUserName.setText(userer.getPhone());
            txtUserPass.setText(userer.getIDnumber());


        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {
        ProgressDialog pd = new ProgressDialog(Add_Person.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Loading...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            if(id != 0){
                try{
                    restUserAPI.post(userer);
                }catch (Exception e){

                }
                return "Successfully Updated Staff Member";
            }
            else{
                try{
                    restUserAPI.post(userer);
                }catch (Exception e){

                }
                return "Successfully Created New Staff Member";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}
