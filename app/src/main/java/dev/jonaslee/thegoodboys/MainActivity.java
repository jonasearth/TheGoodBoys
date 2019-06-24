package dev.jonaslee.thegoodboys;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dev.jonaslee.thegoodboys.DAO.Control;
import dev.jonaslee.thegoodboys.DAO.Login;
import dev.jonaslee.thegoodboys.services.Backthread;
import dev.jonaslee.thegoodboys.services.HttpService;
import dev.jonaslee.thegoodboys.services.MyFirebaseMessagingService;
import dev.jonaslee.thegoodboys.services.Notificacao;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nada por aqui meu amigo :P", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        final TextView warn = findViewById(R.id.warn);
        warn.setText(user);

       if (this.user == ""){
           startActivity(new Intent(this, dev.jonaslee.thegoodboys.Login.class));
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        this.finish();
        startActivity(new Intent(this, dev.jonaslee.thegoodboys.MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Backthread bt = new Backthread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final TextView usr = findViewById(R.id.user_name);
        usr.setText(this.user);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_agendar) {

        } else if (id == R.id.nav_jogadores) {
            startActivity(new Intent(this, dev.jonaslee.thegoodboys.Jogadores.class));
        } else if (id == R.id.nav_times) {
        } else if (id == R.id.nav_historicos) {

        } else if (id == R.id.nav_configuracoes) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            ArrayList<String> nomes = new ArrayList<String>();
            ArrayList extras = new ArrayList();
            nomes.add(0, "id");
            nomes.add(1, "nome");
            nomes.add(2,  "posicao");
            extras.add(0, "1");
            extras.add(1,"jonas gostosao");
            extras.add(2, "Goleiro%21Meio");
            Intent intent = new Intent(this, dev.jonaslee.thegoodboys.Jogador.class);
            Notificacao nova = new Notificacao("BORA PORRA", this, nomes, extras, intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
