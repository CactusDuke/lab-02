package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Declaring Variables
    ListView cityList; //The actual list view
    ArrayAdapter<String> cityAdapter; //Unsure what this is
    ArrayList<String> dataList; //List of cities
    EditText editText; //Textbox
    Button addCity; //Add Button
    Button deleteCity; //Delete Button
    String city; //String to hold city



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Kept from before
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //New Stuff List Data
        cityList = findViewById(R.id.city_list); //Connecting UI to code through ID

        String[] cities = {"Edmonton", "Van", "Mos"}; //List of cities

        dataList = new ArrayList<>(); //Actually making the array list
        dataList.addAll(Arrays.asList(cities)); //Filling list with the string of cities

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList); //Tells the adapter to take the data in datalist and the form made in content.xml and translate from one to the other
        cityList.setAdapter(cityAdapter); //Setting the adapter to the view

        //Adding cities
        editText = findViewById(R.id.editText);
        addCity = findViewById(R.id.addCity);
        deleteCity = findViewById(R.id.deleteCity);

        //Adds text in the edit text when button is pressed
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0) {
                    //Collecting city name
                    city = editText.getText().toString();
                    if (!dataList.contains(city)) { //Ensure that it is a new city
                        // Clear the EditText for the next number
                        editText.setText("");
                        //Adding to dataset
                        dataList.add(city);
                        // Notify the adapter that the dataset has changed
                        cityAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        //Delete Button
        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0) {
                    //Collecting city name
                    city = editText.getText().toString();
                    if (dataList.contains(city) ) { //Ensure that it is not a new city
                        // Clear the EditText for the next number
                        editText.setText("");

                        dataList.remove(city); //Deletes from original dataset

                        // Notify the adapter that the dataset has changed
                        cityAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        //Returns the position of the clicked entry, then deletes it
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(dataList.get(i));
                //dataList.remove(i); //Deletes from original dataset
                //cityAdapter.notifyDataSetChanged(); //Update adapter and actual view
            }
        });
    }
}