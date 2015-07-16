package ru.hand_build.android.githubsearcher.drawer;

/**
 * Created by Владимир on 15.07.2015.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import ru.hand_build.android.githubsearcher.R;

public class DrawerActivity extends AppCompatActivity {

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Remove line to test RTL support
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460");

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_home).withIcon(GoogleMaterial.Icon.gmd_wb_sunny).withIdentifier(1).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_searcher).withIcon(FontAwesome.Icon.faw_home).withIdentifier(2).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_favourites).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(3).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_settings).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(4).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_about).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(5).withCheckable(false)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                                   @Override
                                                   public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                                                       //check if the drawerItem is set.
                                                       //there are different reasons for the drawerItem to be null
                                                       //--> click on the header
                                                       //--> click on the footer
                                                       //those items don't contain a drawerItem

                                                       if (drawerItem != null) {
                                                           if (drawerItem.getIdentifier() == 1) {
                                                               Toast.makeText(getApplicationContext(), R.string.drawer_home, Toast.LENGTH_SHORT).show();

                                                           } else if (drawerItem.getIdentifier() == 2) {
                                                               Toast.makeText(getApplicationContext(), R.string.drawer_searcher, Toast.LENGTH_SHORT).show();
                                                           } else if (drawerItem.getIdentifier() == 3) {
                                                               Toast.makeText(getApplicationContext(), R.string.drawer_favourites, Toast.LENGTH_SHORT).show();
                                                           } else if (drawerItem.getIdentifier() == 4) {
                                                               Toast.makeText(getApplicationContext(), R.string.drawer_settings, Toast.LENGTH_SHORT).show();
                                                           } else if (drawerItem.getIdentifier() == 5) {
                                                               Toast.makeText(getApplicationContext(), R.string.drawer_about, Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                       return false;
                                                   }
                                               }
                                        )

                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

                    //only set the active selection or active profile if we do not recreate the activity
                    if(savedInstanceState==null)

                    {
                        // set the selection to the item with the identifier 11
                        result.setSelectionByIdentifier(11, false);

                        //set the active profile
                        headerResult.setActiveProfile(profile);
                    }
                }


        @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
