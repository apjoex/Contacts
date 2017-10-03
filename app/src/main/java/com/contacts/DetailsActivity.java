package com.contacts;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.contacts.models.ContactData;

public class DetailsActivity extends AppCompatActivity {

    Context context;
    ContactData contactData;
    RelativeLayout home_box, mobile_box, work_box, address_box, birthday_box, email_box;
    TextView name, company_name, home, mobile, work, address, birthdate, email;
    ImageView avatar_big;
    boolean isFav = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = this;

        avatar_big = (ImageView)findViewById(R.id.avatar_big);

        name = (TextView)findViewById(R.id.name);
        company_name = (TextView)findViewById(R.id.company_name);
        home = (TextView)findViewById(R.id.home);
        mobile = (TextView)findViewById(R.id.mobile);
        work = (TextView)findViewById(R.id.work);
        address = (TextView)findViewById(R.id.address);
        birthdate = (TextView)findViewById(R.id.birthdate);
        email = (TextView)findViewById(R.id.email);

        home_box = (RelativeLayout)findViewById(R.id.home_box);
        mobile_box = (RelativeLayout)findViewById(R.id.mobile_box);
        work_box = (RelativeLayout)findViewById(R.id.work_box);
        birthday_box = (RelativeLayout)findViewById(R.id.birthday_box);
        address_box = (RelativeLayout)findViewById(R.id.address_box);
        email_box = (RelativeLayout)findViewById(R.id.email_box);

        contactData = (ContactData) getIntent().getExtras().getSerializable("contact");

        updateUI();
    }

    private void updateUI() {

        getSupportActionBar().setTitle(contactData.getName());
        isFav = contactData.isFavourite();

        Glide.with(context).load(contactData.getLargeImageUrl()).into(avatar_big);

        name.setText(contactData.getName());
        company_name.setText(contactData.getCompanyName());

        if(contactData.getHome().equals("") || contactData.getHome().equals("null")){
            home_box.setVisibility(View.GONE);
        }else{
            home.setText(contactData.getHome());
        }

        if(contactData.getMobile().equals("") || contactData.getMobile().equals("null")){
            mobile_box.setVisibility(View.GONE);
        }else{
            mobile.setText(contactData.getMobile());
        }

        if(contactData.getWork().equals("") || contactData.getWork().equals("null")){
            work_box.setVisibility(View.GONE);
        }else{
            work.setText(contactData.getWork());
        }

        if(contactData.getBirthdate().equals("") || contactData.getBirthdate().equals("null")){
            birthday_box.setVisibility(View.GONE);
        }else{
            birthdate.setText(contactData.getBirthdate());
        }

        address.setText(contactData.getStreet()+" "+contactData.getCity()+" "+contactData.getState()+" "+contactData.getCountry()+" "+contactData.getZipcode());

        if(contactData.getEmailAddress().equals("") || contactData.getEmailAddress().equals("null")){
            email_box.setVisibility(View.GONE);
        }else{
            email.setText(contactData.getEmailAddress());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav, menu);

        MenuItem menuItem = menu.getItem(0);
        if(contactData.isFavourite()){
            menuItem.setIcon(R.drawable.ic_star);
        }else{
            menuItem.setIcon(R.drawable.ic_star_border);
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        isFav = !isFav;
        item.setIcon((isFav ? R.drawable.ic_star : R.drawable.ic_star_border));
        return super.onOptionsItemSelected(item);
    }
}
