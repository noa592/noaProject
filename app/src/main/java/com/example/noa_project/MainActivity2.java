       package com.example.noa_project;

       import static com.example.noa_project.MainActivity.fbModule;

       import android.content.Intent;
       import android.os.Bundle;
       import android.view.View;

       import androidx.activity.result.ActivityResultLauncher;
       import androidx.appcompat.app.AppCompatActivity;
       import androidx.fragment.app.FragmentResultListener;
       import androidx.viewpager2.widget.ViewPager2;

       import com.google.android.material.tabs.TabLayout;
       import com.google.android.material.tabs.TabLayoutMediator;


       public class MainActivity2 extends AppCompatActivity {

        private ViewPager2 viewPager; // שימוש במשתנים גלובליים
        private TabLayout tabLayout;
        private ActivityResultLauncher<Intent> activityResultLauncher;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);



            // מציאת ה-ViewPager2 ו-TabLayout מתוך ה-XML
            tabLayout = findViewById(R.id.tablayout);
            viewPager = findViewById(R.id.viewPager);

            // יוצרים מתאם לפרגמנטים
            FragmentAdapter adapter = new FragmentAdapter(this);
            viewPager.setAdapter(adapter);



            // TODO: 04/02/2025
            //   setResult(RESULT_OK, intent);


            // מחברים את ה-TabLayout ל-ViewPager2
            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(TabLayout.Tab tab, int position) {
                    if (position == 0) {
                        tab.setText("Tab 1"); // שם לטאב הראשון
                    } else if (position == 1) {
                        tab.setText("Tab 2"); // שם לטאב השני
                    } else {
                        tab.setText("Unknown Tab"); // שם ברירת מחדל
                    }
                }
            }).attach();



           getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
               @Override
               public void onFragmentResult(String requestKey, Bundle result) {
                   if (requestKey.equals("requestKey")) {
                       String color = result.getString("color");
                       if (color != null) {
                          // Toast.makeText(MainActivity2.this, ""+ color, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                          intent.putExtra("color", color);
                          setResult(RESULT_OK,intent);
                            finish();
                       }
                   }
               }
           });
        }

           }





