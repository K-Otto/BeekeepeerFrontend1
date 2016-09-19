package com.otto.beekeeperbackendfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.otto.beekeeperbackendfinal.Model.User;
import com.otto.beekeeperbackendfinal.Repositories.rest.RestUserAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ViewPerson extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> gridItems = new ArrayList<String>();

    //STAFF
    RestUserAPI restUserAPI = new RestUserAPI();
    List<User> user = new LinkedList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        gridView = (GridView) findViewById(R.id.gridViewStaff);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Add_Person.class);

                intent.putExtra("firstName", user.get(position).getName());
                intent.putExtra("lastName", user.get(position).getSurname());
                intent.putExtra("password", user.get(position).getPhone());
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User staff1 = user.get(position);
                //String message = restStaffAPI.delete(staff1);
                restUserAPI.delete(staff1);
                Toast.makeText(ViewPerson.this, staff1.getName() + " " + position + " " + id, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        new HttpAsyncTask().execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gridView = (GridView) findViewById(R.id.gridViewStaff);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Add_Person.class);

                intent.putExtra("firstName", user.get(position).getName());
                intent.putExtra("lastName", user.get(position).getSurname());
                intent.putExtra("password", user.get(position).getPhone());

                startActivity(intent);
            }
        });
        new HttpAsyncTask().execute();
    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog pdLoading = new ProgressDialog(ViewPerson.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdLoading.setMessage("Loading...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                user = restUserAPI.getAll();
            }catch (Exception e){
                Log.e("ViewUser", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(user.size() != 0){
                String[] temp = new String[user.size()];
                for(int i = 0; i < user.size(); i++){
                    temp[i] =   user.get(i).getName() + " " + user.get(i).getSurname();
                }
                populateGridView(temp);
            }
            else{
                gridView.setAdapter(null);
            }
            pdLoading.dismiss();
        }
    }

    public void populateGridView(String[] arr2){
        gridItems.clear();

        for(int i=0; i< arr2.length; i++){
            gridItems.add(arr2[i]);
        }

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridItems);
        gridView.setAdapter(gridAdapter);
    }
}