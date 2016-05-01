package com.hazem.udacity.sunshine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezah on 4/26/2016.
 */
public class ForecastFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main,container,false);

        ListView listView= (ListView) view.findViewById(R.id.listview_forecast);

        List<String> weekForecast=getDataSet();

        ArrayAdapter<String> forecastAdapter=new ArrayAdapter<>(
                // The current context
                getActivity(),
                // Id of the layout that will be used to populate the list of data
                R.layout.list_item_forecast,
                // The specific view inside the layout to be used to populate the the data inside the list
                R.id.list_item_forecast_textview,
                // The data that is required to be populated
                weekForecast);

        listView.setAdapter(forecastAdapter);

        return view;

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.option_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id){

            case R.id.refresh:
                new FetchWeatherTask().execute("360630");
                Toast.makeText(getActivity(), "Pressssssssing", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private List<String> getDataSet(){
        ArrayList<String> data=new ArrayList<>();
        data.add("Mon 6/23 - Sunny - 31/17");
        data.add("Tue 6/24 - Foggy - 21/8");
        data.add("Wed 6/25 - Cloudy - 22/17");
        data.add("Thurs 6/26 - Rainy - 18/11");
        data.add("Fri 6/27 - Foggy - 21/10");
        data.add("Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18");
        data.add("Sun 6/29 - Sunny - 20/7");

        return data;
    }
}
