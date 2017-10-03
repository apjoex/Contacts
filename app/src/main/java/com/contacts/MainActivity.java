package com.contacts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.contacts.models.ContactData;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    RelativeLayout loadingScreen;
    ListView favList, otherList;
    Context context;
    ArrayList<ContactData> favContacts = new ArrayList<>();
    ArrayList<ContactData> otherContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        favList = (ListView)findViewById(R.id.favList);
        otherList = (ListView)findViewById(R.id.otherList);
        loadingScreen = (RelativeLayout)findViewById(R.id.loadingScreen);

        getContacts();
    }



    private void getContacts() {

        String url = "https://s3.amazonaws.com/technical-challenge/v3/contacts.json";

        Fuel.get(url).responseString(new Handler<String>() {
            @Override
            public void success(@NotNull Request request, @NotNull Response response, String s) {
                try {
                    JSONArray baseArray = new JSONArray(s);
                    for (int i = 0; i < baseArray.length(); i++){
                        JSONObject object = baseArray.getJSONObject(i);
                        ContactData data = new ContactData(object.optString("name"),
                                object.optString("id"),
                                object.optString("companyName"),
                                object.getBoolean("isFavorite"),
                                object.optString("smallImageURL"),
                                object.optString("largeImageURL"),
                                object.optString("emailAddress"),
                                object.optString("birthdate"),
                                object.getJSONObject("phone").optString("work"),
                                object.getJSONObject("phone").optString("home"),
                                object.getJSONObject("phone").optString("mobile"),
                                object.getJSONObject("address").optString("street"),
                                object.getJSONObject("address").optString("city"),
                                object.getJSONObject("address").optString("state"),
                                object.getJSONObject("address").optString("country"),
                                object.getJSONObject("address").optString("zipCode"));

                        if(data.isFavourite()){
                            favContacts.add(data);
                        }else{
                            otherContacts.add(data);
                        }
                    }


                    updateUI();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError fuelError) {

            }
        });
    }

    private void updateUI() {

        loadingScreen.setVisibility(View.GONE);

        //set height for fav contacts listview
        int fav_list_height = Utilities.dp2px(context,70) * favContacts.size();
        favList.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, fav_list_height));

        //Sort alphabetically
        Collections.sort(favContacts, new Comparator<ContactData>() {
            public int compare(ContactData c1, ContactData c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });

        ContactAdapter favAdapter = new ContactAdapter(context, favContacts);
        favList.setAdapter(favAdapter);

        favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", favContacts.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        //set height for other contacts listview
        int other_list_height = Utilities.dp2px(context,70) * otherContacts.size();
        otherList.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, other_list_height));

        //Sort alphabetically
        Collections.sort(otherContacts, new Comparator<ContactData>() {
            public int compare(ContactData c1, ContactData c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });


        ContactAdapter otherAdater = new ContactAdapter(context, otherContacts);
        otherList.setAdapter(otherAdater);

        otherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", otherContacts.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private class ContactAdapter extends BaseAdapter{

        Context mContext;
        ArrayList<ContactData> mContacts;


        public ContactAdapter(Context context, ArrayList<ContactData> contacts) {
            mContacts = contacts;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mContacts.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.contacts_item,null);

                holder.avatar = (ImageView)convertView.findViewById(R.id.avatar);
                holder.fav_icon = (ImageView)convertView.findViewById(R.id.fav_icon);
                holder.name = (TextView)convertView.findViewById(R.id.name);
                holder.companyName = (TextView)convertView.findViewById(R.id.companyName);
                convertView.setTag(holder);

            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            if(mContacts.get(position).isFavourite()){
                holder.fav_icon.setVisibility(View.VISIBLE);
            }else{
                holder.fav_icon.setVisibility(View.GONE);
            }
            holder.name.setText(mContacts.get(position).getName());
            holder.companyName.setText(mContacts.get(position).getCompanyName());
            Glide.with(mContext).load(mContacts.get(position).getSmallImageUrl()).into(holder.avatar);

            return convertView;
        }

        private class ViewHolder{
            ImageView avatar, fav_icon;
            TextView name, companyName;
        }
    }
}
