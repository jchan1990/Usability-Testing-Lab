package com.charlesdrews.usabilitytestinglab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnZodiacSignSelectedListener {

    private boolean mScreenIsLageEnoughForTwoPanes = false;
    private DetailFragment mDetailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this class - MainActivity - implements the OnZodiacSignSelectedListener interface
        // defined in ListFragment - so "this" can be set as the listener for the list fragment
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        listFragment.setListener(this);

        //TODO determine which layout file is being used (hint: is there an element in the large-screen
        //TODO  layout that's not in the regular layout?) and if the large screen layout is being used,
        //TODO  then load the detail fragment in MainActivity rather than using DetailActivity

        View containerDetail = findViewById(R.id.detail_fragment_container);

        if (containerDetail != null && containerDetail.getVisibility() == View.VISIBLE) {
            mScreenIsLageEnoughForTwoPanes = true;
            mDetailFragment = DetailFragment.newInstance(savedInstanceState);
            getSupportFragmentManager().beginTransaction().add(R.id.detail_fragment_container, mDetailFragment).commit();
        }
    }

    @Override
    public void onZodiacSignSelected(String zodiacSignSelected) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(DetailActivity.SIGN_KEY, zodiacSignSelected);
//        startActivity(intent);

        //TODO - if the detail fragment is loaded into MainActivity, update it rather than launching
        //TODO      the DetailActivity

        if (mScreenIsLageEnoughForTwoPanes == true) {
            mDetailFragment.updateWebView(zodiacSignSelected);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.SIGN_KEY, zodiacSignSelected);
            startActivity(intent);
        }
    }
}
