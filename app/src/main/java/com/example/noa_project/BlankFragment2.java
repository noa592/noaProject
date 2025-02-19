package com.example.noa_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;


            public class BlankFragment2 extends Fragment {
                private String chooseColor;
                private RadioButton radioButton1;
                private RadioButton radioButton2;
                private RadioGroup radioGroup;
                private FbModule fbModule;

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    View rootView = inflater.inflate(R.layout.fragment_blank2, container, false);




                        radioButton1 =rootView.findViewById(R.id.radiobutton1);
                        radioButton2 =rootView.findViewById(R.id.radiobutton2);
                        radioGroup =rootView.findViewById(R.id.radiogroup);


                        // מאזין לשינוי בחירת הצבע
                            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

                        {
                            @Override
                            public void onCheckedChanged (RadioGroup group,int checkedId){
                            if (checkedId == R.id.radiobutton1) {
                                chooseColor = "light";  // צבע בהיר (לבן)
                            } else if (checkedId == R.id.radiobutton2) {
                                chooseColor = "dark";   // צבע כהה (שחור)
                            }
                            sendColorToMainActivity2(chooseColor);
                        }
                        });

                            return rootView;
                    }

                    private void sendColorToMainActivity2 (String color){
                        // שלח את הצבע לאקטיביטי הראשי
                        //Intent intent = new Intent(getActivity(), MainActivity.class);
                       /// Intent intent = new Intent();
                        ///intent.putExtra("color", color);
                        Bundle result = new Bundle();
                        result.putString("color", color); // שינוי צבע לדוגמה
                        getParentFragmentManager().setFragmentResult("requestKey", result);

                        //startActivity(intent);
                    }
                }






