package com.example.galeriapublica;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    //definindo o bottonViewNavigation como atributo para utilizarmos de refências para outros métodos
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //configurando as ações que serão executadas assim que o usuário usar algum dos itens da nav dos botões

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //obtendo referências
            final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

            //setando a função de "escutador" para selecionar o item que foi clicado
            bottomNavigationView = findViewById(R.id.btNav);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    //guardando o item que foi escolhido anteriormente dentro do MainViewModel
                    vm.setNavigationOpSelected(item.getItemId());

                    //definindo as ações que serão realizadas para cada opção
                    switch (item.getItemId()) {
                        case R.id.gridViewOp:
                            GridViewFragment gridViewFragment = GridViewFragment.newInstance();
                            setFragment(gridViewFragment);
                            break;
                        case R.id.listViewOp:
                            ListViewFragment listViewFragment = ListViewFragment.newInstance();
                            setFragment(listViewFragment);
                            break;
                    }
                    }
                    return true;
                }
            });
        }
    void setFragment (Fragment fragment)
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }