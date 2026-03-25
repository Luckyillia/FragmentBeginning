package com.example.fragmentbeginning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Importy Twoich fragmentów (dopasuj nazwę pakietu do projektu)
// import com.example.twojaapka.FirstFragment;
// import com.example.twojaapka.SecondFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnFragment1, btnFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFragment1 = findViewById(R.id.btnFragment1);
        btnFragment2 = findViewById(R.id.btnFragment2);

        // Wyświetl pierwszy fragment domyślnie przy starcie
        loadFragment(new FirstFragment());

        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FirstFragment());
            }
        });

        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SecondFragment());
            }
        });
    }

    // Metoda pomocnicza do ładowania fragmentów
    private void loadFragment(Fragment fragment) {
        // 1. Uzyskaj FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // 2. Rozpocznij transakcję
        FragmentTransaction ft = fm.beginTransaction();
        // 3. Zamień zawartość kontenera na nowy fragment
        ft.replace(R.id.fragment_container, fragment);
        // 4. Zatwierdź transakcję
        ft.commit();
    }
}