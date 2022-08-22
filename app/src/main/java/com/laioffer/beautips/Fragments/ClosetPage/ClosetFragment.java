package com.laioffer.beautips.Fragments.ClosetPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.laioffer.beautips.MainActivity2;
import com.laioffer.beautips.Models.Closet;
import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.FragmentClosetBinding;

import java.util.ArrayList;
import java.util.List;

public class ClosetFragment extends Fragment {

    FragmentClosetBinding binding;
    ClosetImageAdapter ClosetImageAdapter;
    private static final String TAG = "ClosetFragment";
    private DatabaseReference myRef;
    Context context;
    RecyclerView recyclerView;
    private ArrayList<Closet> ClosetList = new ArrayList<>();
    public static final String  BodyShapeFinal = "+";
    public static final String  OccasionFinal = "+";
    public static final String  DressCodeFinal = "+";
    public static final String  TopSizeFinal = "M";
    public static final String  BottomSizeFinal = "M";
    public static String  BodyShape = "+";
    public static String  Occasion = "+";
    public static String  DressCode = "+";
    public static String  TopSize = "M";
    public static String  BottomSize = "M";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentClosetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public ClosetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i("size of list", String.valueOf(ClosetList.size()));
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.Closet_results_recycler_view);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        ClosetImageAdapter = new ClosetImageAdapter(context, ClosetList);
        recyclerView.setAdapter(ClosetImageAdapter);
        /*recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d(TAG, "onItemClick: ");

                        openActivity2();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/
        LinearLayout occasionOnclick = view.findViewById(R.id.occasionOnclick);
        Button search = view.findViewById(R.id.search);
        TextView occasionPlus = view.findViewById(R.id.occasion);
        TextView bodyShapePlus = view.findViewById(R.id.bodyShapePlus);
        TextView clearAll = view.findViewById(R.id.cancel_button);
        TextView topSize = view.findViewById(R.id.topSize);
        TextView bottomSize = view.findViewById(R.id.bottomSize);
        TextView dressCodePlus = view.findViewById(R.id.dressCodePlus);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) search.getLayoutParams();
        ImageView loading = view.findViewById(R.id.loading);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(params.width == 206) {
                    ArrayList <Closet> search = new ArrayList<>();
                    Log.d(TAG, "onClick: size of CloseList" + ClosetList.size());
                    for (int i = 0; i < ClosetList.size(); i++) {

                        if (ClosetList.get(i).getTopSize().compareTo(TopSize) == 0 ){
                            search.add(ClosetList.get(i));
                        }
                    }
                    Log.d(TAG, "SEARCHLIST SIZE" + search.size());

                    ClosetImageAdapter = new ClosetImageAdapter(context, search);
                    recyclerView.setAdapter(ClosetImageAdapter);
                    TextView total = view.findViewById(R.id.totalMatched);
                    total.setText("" + search.size() + " suits match");

                    new CountDownTimer(1500,1500) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            loading.setVisibility(View.VISIBLE);
                        }


                        @Override
                        public void onFinish() {
                            loading.setVisibility(View.GONE);
                        }
                    }.start();
                } else {
                    new CountDownTimer(500,500) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            loading.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFinish() {
                            loading.setVisibility(View.GONE);
                        }
                    }.start();

                }
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyShape = BodyShapeFinal;
                Occasion = OccasionFinal;
                DressCode = DressCodeFinal;
                TopSize = TopSizeFinal;
                BottomSize = BottomSizeFinal;
                view.findViewById(R.id.hideOccasion).setVisibility(View.GONE);
                occasionPlus.setText(Occasion);
                view.findViewById(R.id.hideBodyShape).setVisibility(View.GONE);
                bodyShapePlus.setText(BodyShape);
                view.findViewById(R.id.hideSize).setVisibility(View.GONE);
                view.findViewById(R.id.hideDressCode).setVisibility(View.GONE);
                dressCodePlus.setText(DressCode);
                search.setBackgroundResource(R.drawable.search_btn);
                topSize.setText(TopSize);
                bottomSize.setText(BottomSize);
                clearBackGroundAllForDressCode(view);
                clearBackGroundAllForOccasion(view);
                clearBackGroundAllForBodyShape(view);
                params.width = 206;

            }
        });


        occasionOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hideList = view.findViewById(R.id.hideOccasion);

                if (hideList.getVisibility() == View.GONE) {
                    hideList.setVisibility(View.VISIBLE);
                    search.setBackgroundResource(R.drawable.save);
                    occasionPlus.setText("-");
                    params.width = 205; //change the size of search button as a flag
                } else {
                    hideList.setVisibility(View.GONE);
                    occasionPlus.setText("+");

                    if (view.findViewById(R.id.hideDressCode).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideSize).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideBodyShape).getVisibility() == View.GONE) {
                        search.setBackgroundResource(R.drawable.search_btn);
                        params.width = 206;
                    }
                }

            }
        });
        LinearLayout bodyShapeOnclick = view.findViewById(R.id.bodyShapeOnclick);

        bodyShapeOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hideList = view.findViewById(R.id.hideBodyShape);

                if (hideList.getVisibility() == View.GONE) {
                    hideList.setVisibility(View.VISIBLE);
                    search.setBackgroundResource(R.drawable.save);
                    bodyShapePlus.setText("-");
                    params.width = 205;
                } else {
                    hideList.setVisibility(View.GONE);
                    bodyShapePlus.setText(BodyShape);

                    if (view.findViewById(R.id.hideDressCode).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideSize).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideOccasion).getVisibility() == View.GONE) {
                        search.setBackgroundResource(R.drawable.search_btn);
                        params.width = 206;
                    }

                }


            }
        });
        LinearLayout dressCodeOnclick = view.findViewById(R.id.dressCodeOnclick);

        dressCodeOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hideList = view.findViewById(R.id.hideDressCode);

                if (hideList.getVisibility() == View.GONE) {
                    hideList.setVisibility(View.VISIBLE);
                    search.setBackgroundResource(R.drawable.save);
                    dressCodePlus.setText("-");
                    params.width = 205;
                } else {
                    hideList.setVisibility(View.GONE);
                    dressCodePlus.setText(DressCode);

                    if (view.findViewById(R.id.hideOccasion).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideSize).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideBodyShape).getVisibility() == View.GONE) {
                        search.setBackgroundResource(R.drawable.search_btn);
                        params.width = 206;
                    }
                }


            }
        });
        LinearLayout sizeOnclick = view.findViewById(R.id.sizeOnclick);

        sizeOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hideList = view.findViewById(R.id.hideSize);
                if (hideList.getVisibility() == View.GONE) {
                    hideList.setVisibility(View.VISIBLE);
                    search.setBackgroundResource(R.drawable.save);

                    params.width = 205;


                } else {
                    topSize.setText(TopSize);
                    bottomSize.setText(BottomSize);
                    hideList.setVisibility(View.GONE);

                    if (view.findViewById(R.id.hideOccasion).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideDressCode).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideBodyShape).getVisibility() == View.GONE) {
                        search.setBackgroundResource(R.drawable.search_btn);

                        params.width = 206;
                    }
                }

            }
        });


        TextView pearIcon = view.findViewById(R.id.pearIcon); // first-hiding-list icon

        pearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBodyShape(view);
                pearIcon.setBackgroundResource(R.drawable.rainbowborder);
                BodyShape = pearIcon.getText().toString();
                pearIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });

        TextView roundIcon = view.findViewById(R.id.roundIcon);

        roundIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBodyShape(view);
                BodyShape = roundIcon.getText().toString();
                roundIcon.setBackgroundResource(R.drawable.rainbowborder);
                roundIcon.setPadding(30,2,0,0);
            }
        });

        TextView triangleIcon = view.findViewById(R.id.triangleIcon);
        triangleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBodyShape(view);
                BodyShape = triangleIcon.getText().toString();
                triangleIcon.setBackgroundResource(R.drawable.rainbowborder);
                triangleIcon.setPadding(30,2,0,0);
            }
        });

        TextView rectangleIcon = view.findViewById(R.id.rectangleIcon);
        rectangleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBodyShape(view);
                BodyShape = rectangleIcon.getText().toString();
                rectangleIcon.setBackgroundResource(R.drawable.rainbowborder);
                rectangleIcon.setPadding(30,2,0,0);
            }
        });

        TextView hourglassIcon = view.findViewById(R.id.hourglassIcon);
        hourglassIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBodyShape(view);
                BodyShape = hourglassIcon.getText().toString();
                hourglassIcon.setBackgroundResource(R.drawable.rainbowborder);
                hourglassIcon.setPadding(30,2,0,0);
            }
        }); // end of first-hide-lists

        TextView weddingIcon = view.findViewById(R.id.weddingIcon);
        weddingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.weddingIcon));
            }
        });

        TextView partyIcon = view.findViewById(R.id.partyIcon);
        partyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.partyIcon));
            }
        });

        TextView beachIcon = view.findViewById(R.id.beachIcon);
        beachIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.beachIcon));
            }
        });

        TextView dinnerIcon = view.findViewById(R.id.dinnerIcon);
        dinnerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.dinnerIcon));
            }
        });

        TextView dailyLifeIcon = view.findViewById(R.id.dailyLifeIcon);
        dailyLifeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.dailyLifeIcon));
            }
        });

        TextView datingIcon = view.findViewById(R.id.datingIcon);
        datingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.datingIcon));
            }
        });

        TextView meetingIcon = view.findViewById(R.id.meetingIcon);
        meetingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickForOccasionButtons(view.findViewById(R.id.meetingIcon));
            }
        });

        TextView formalIcon = view.findViewById(R.id.formalIcon);
        formalIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DressCode = formalIcon.getText().toString();
                clearBackGroundAllForDressCode(view);
                setRainbowBorder(view.findViewById(R.id.formalIcon));
            }
        });

        TextView semiIcon = view.findViewById(R.id.semiIcon);
        semiIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForDressCode(view);
                DressCode = semiIcon.getText().toString();
                setRainbowBorder(view.findViewById(R.id.semiIcon));
            }
        });

        TextView informalIcon = view.findViewById(R.id.informalIcon);
        informalIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForDressCode(view);
                DressCode = informalIcon.getText().toString();
                setRainbowBorder(view.findViewById(R.id.informalIcon));
            }
        });

        TextView casualIcon = view.findViewById(R.id.casualIcon);
        casualIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForDressCode(view);
                DressCode = casualIcon.getText().toString();
                setRainbowBorder(view.findViewById(R.id.casualIcon));
            }
        }); // end of DressCode part

        // start of size part

        TextView topXSIcon = view.findViewById(R.id.topXSIcon);
        topXSIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = topXSIcon.getText().toString();
                Log.d(TAG, TopSize);
                setRainbowBorderForTinyButton(view.findViewById(R.id.topXSIcon));
            }
        });

        TextView topLIcon = view.findViewById(R.id.topLIcon);
        topLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = topLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.topLIcon));
            }
        });

        TextView topSIcon = view.findViewById(R.id.topSIcon);
        topSIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = topSIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.topSIcon));
            }
        });

        TextView topMIcon = view.findViewById(R.id.topMIcon);
        topMIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = topMIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.topMIcon));
            }
        });

        TextView topXLIcon = view.findViewById(R.id.topXLIcon);
        topXLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = topXLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.topXLIcon));
            }
        });

        TextView top2XLIcon = view.findViewById(R.id.top2XLIcon);
        top2XLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = top2XLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.top2XLIcon));
            }
        });

        TextView top3XLIcon = view.findViewById(R.id.top3XLIcon);
        top3XLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForTop(view);
                TopSize = top3XLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.top3XLIcon));
            }
        });

        TextView bottomXSIcon = view.findViewById(R.id.bottomXSIcon);
        bottomXSIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottomXSIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottomXSIcon));
            }
        });

        TextView bottomLIcon = view.findViewById(R.id.bottomLIcon);
        bottomLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottomLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottomLIcon));
            }
        });

        TextView bottomSIcon = view.findViewById(R.id.bottomSIcon);
        bottomSIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottomSIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottomSIcon));
            }
        });

        TextView bottomMIcon = view.findViewById(R.id.bottomMIcon);
        bottomMIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottomMIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottomMIcon));
            }
        });

        TextView bottomXLIcon = view.findViewById(R.id.bottomXLIcon);
        bottomXLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottomXLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottomXLIcon));
            }
        });

        TextView bottom2XLIcon = view.findViewById(R.id.bottom2XLIcon);
        bottom2XLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottom2XLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottom2XLIcon));
            }
        });

        TextView bottom3XLIcon = view.findViewById(R.id.bottom3XLIcon);
        bottom3XLIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBottom(view);
                BottomSize = bottom3XLIcon.getText().toString();
                setRainbowBorderForTinyButton(view.findViewById(R.id.bottom3XLIcon));
            }
        });






        myRef = FirebaseDatabase.getInstance().getReference(); // java based backend
        ClosetList = new ArrayList<>();
        ClearAll();
        GetDataBaseFromFireBase();


        Button backToTop = view.findViewById(R.id.toTop); // back to top
        backToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView scrollView = view.findViewById(R.id.scroll);
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

    }


    /*private void onclickForOccasionButtons(View t) {
        Log.d(TAG, t.toString());
    }*/

    private void onclickForOccasionButtons(TextView t) {
        if(t.getPaintFlags() == 0) {
            t.setPaintFlags(1);
            t.setBackgroundResource(R.drawable.darkblackborder);
        } else {
            t.setPaintFlags(0);
            t.setBackgroundResource(R.drawable.blackborder);
        }
    }
    private void setRainbowBorder(TextView t) {
        t.setBackgroundResource(R.drawable.rainbowborder);
        t.setPadding(28,2,0,0);
    }
    private void setRainbowBorderForTinyButton(TextView t) {
        t.setBackgroundResource(R.drawable.rainbowborder);
        t.setPadding(22,3,0,0);
    }
    private void clearBackGroundAllForBodyShape(View view) {
        view.findViewById(R.id.pearIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.roundIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.hourglassIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.rectangleIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.triangleIcon).setBackgroundResource(R.drawable.blackborder);
    }

    private void clearBackGroundAllForOccasion(View view) {
        view.findViewById(R.id.weddingIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.partyIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.beachIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.dinnerIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.dailyLifeIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.datingIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.meetingIcon).setBackgroundResource(R.drawable.blackborder);

    }

    private void clearBackGroundAllForDressCode(View view) {
        view.findViewById(R.id.formalIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.semiIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.informalIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.casualIcon).setBackgroundResource(R.drawable.blackborder);
    }

    private void clearBackGroundAllForTop(View view) {
        view.findViewById(R.id.topXSIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.topSIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.topMIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.topLIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.topXLIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.top2XLIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.top3XLIcon).setBackgroundResource(R.drawable.blackborder);
    }

    private void clearBackGroundAllForBottom(View view) {
        view.findViewById(R.id.bottomXSIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.bottomSIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.bottomMIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.bottomLIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.bottomXLIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.bottom2XLIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.bottom3XLIcon).setBackgroundResource(R.drawable.blackborder);
    }

    /*private void openActivity2() {
        Intent intent = new Intent(getActivity(), MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/

    private void GetDataBaseFromFireBase() {
        Query query = myRef.child("Closet");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {

                    Closet closet = new Closet();
                    closet.setBodyShape(snapshot.child("bodyShape").getValue().toString());
                    closet.setImageName(snapshot.child("imageName").getValue().toString());
                    closet.setImageUrl(snapshot.child("imageUrl").getValue().toString());
                    closet.setScore(Integer.parseInt(snapshot.child("score").getValue().toString()));
                    closet.setBottomSize(snapshot.child("bottomSize").getValue().toString());
                    closet.setDressCode(snapshot.child("dressCode").getValue().toString());
                    closet.setOccasion(snapshot.child("occasion").getValue().toString());
                    closet.setTopSize(snapshot.child("topSize").getValue().toString());
                    closet.setBottomPrice(Integer.parseInt(snapshot.child("bottomPrice").getValue().toString()));
                    closet.setTopPrice(Integer.parseInt(snapshot.child("topPrice").getValue().toString()));
                    closet.setTopUrl(snapshot.child("topUrl").getValue().toString());
                    closet.setTopName(snapshot.child("topName").getValue().toString());
                    closet.setBottomUrl(snapshot.child("bottomUrl").getValue().toString());
                    closet.setBottomName(snapshot.child("bottomName").getValue().toString());

                    ClosetList.add(closet);
                }

                ClosetImageAdapter = new ClosetImageAdapter(context, ClosetList);
                recyclerView.setAdapter(ClosetImageAdapter);
                ClosetImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void GetDataBaseFromFireBaseForSearch(String topSize){
        Query query = myRef.child("Closet");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {

                    Closet closet = new Closet();
                    closet.setBodyShape(snapshot.child("bodyShape").getValue().toString());
                    closet.setImageName(snapshot.child("imageName").getValue().toString());
                    closet.setImageUrl(snapshot.child("imageUrl").getValue().toString());
                    closet.setScore(Integer.parseInt(snapshot.child("score").getValue().toString()));
                    closet.setBottomSize(snapshot.child("bottomSize").getValue().toString());
                    closet.setDressCode(snapshot.child("dressCode").getValue().toString());
                    closet.setOccasion(snapshot.child("occasion").getValue().toString());

                    String postTop = snapshot.child("topSize").getValue().toString();
                    closet.setTopSize(postTop);

                    closet.setBottomPrice(Integer.parseInt(snapshot.child("bottomPrice").getValue().toString()));
                    closet.setTopPrice(Integer.parseInt(snapshot.child("topPrice").getValue().toString()));
                    closet.setTopUrl(snapshot.child("topUrl").getValue().toString());
                    closet.setTopName(snapshot.child("topName").getValue().toString());
                    closet.setBottomUrl(snapshot.child("bottomUrl").getValue().toString());
                    closet.setBottomName(snapshot.child("bottomName").getValue().toString());
                    if (postTop == topSize) {
                        Log.d(TAG, "onDataChange: equal");
                        ClosetList.add(closet);
                    }

                }

                ClosetImageAdapter = new ClosetImageAdapter(context, ClosetList);
                recyclerView.setAdapter(ClosetImageAdapter);
                ClosetImageAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll () {
        if (ClosetList!= null) {
            ClosetList.clear();

            if(ClosetImageAdapter != null) {
                ClosetImageAdapter.notifyDataSetChanged();
            }
        }
        ClosetList = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }


    private static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}


    }
}