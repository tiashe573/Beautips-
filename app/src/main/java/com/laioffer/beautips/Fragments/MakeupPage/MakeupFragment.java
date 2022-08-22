package com.laioffer.beautips.Fragments.MakeupPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.laioffer.beautips.Fragments.ClosetPage.ClosetImageAdapter;
import com.laioffer.beautips.MainActivity;
import com.laioffer.beautips.Models.Closet;
import com.laioffer.beautips.Models.Makeup;
import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.FragmentClosetBinding;
import com.laioffer.beautips.databinding.FragmentMakeupBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MakeupFragment extends Fragment {
    ProgressDialog pd;
    String Url = "http://makeup-api.herokuapp.com/api/v1/products.json";
    String brandPrefix = "?brand=";
    String typePrefix = "&product_type=";
    private static final String TAG = "";
    FragmentMakeupBinding binding;
    private ArrayList<Makeup> MakeupList = new ArrayList<>();
    com.laioffer.beautips.Fragments.MakeupPage.MakeupAdapter MakeupAdapter;
    public String Brand = "";
    public String ProductType = "";
    public static final String  BrandFinal = "+";
    public static final String  ProductTypeFinal = "+";
    Context context;
    RecyclerView recyclerView;

    public MakeupFragment() throws IOException, JSONException {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMakeupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {
        Log.i("size of list", String.valueOf(MakeupList.size()));
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.Makeup_results_recycler_view);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        MakeupAdapter = new MakeupAdapter(context, MakeupList);
        recyclerView.setAdapter(MakeupAdapter);
        /*ArrayList<Makeup> res = null;
        try {
            res = readJsonFromUrl("http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Makeup m = res.get(0);
        Log.d(TAG, "onViewCreated: "+ m.getName());*/
        LinearLayout typeOnclick = view.findViewById(R.id.typeOnclick);
        LinearLayout brandOnclick = view.findViewById(R.id.brandOnclick);
        Button search = view.findViewById(R.id.search);
        TextView brandPlus = view.findViewById(R.id.brandPlus);
        TextView typePlus = view.findViewById(R.id.typePlus);
        TextView clearAll = view.findViewById(R.id.cancel_button);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) search.getLayoutParams();
        ImageView loading = view.findViewById(R.id.makeuploading);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAll();
                // new JsonTask().execute("http://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl&product_type=lipstick");

                try {
                    String temp = Url;
                    if (Brand != BrandFinal) {
                        temp = temp + brandPrefix + Brand;

                    }
                    if (ProductType != ProductTypeFinal) {
                        temp = temp + typePrefix + ProductType;
                    }
                    String str_result= new JsonTask().execute(temp).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
                ArrayList <Makeup> search = new ArrayList<>();
                Log.d(TAG, "onClick: size of MakeupList" + MakeupList.size());
                for (int i = 0; i < MakeupList.size(); i++) {

                        /*if (ClosetList.get(i).getTopSize().compareTo(TopSize) == 0 ){

                        }*/
                    search.add(MakeupList.get(i));
                }
                Log.d(TAG, "SEARCHLIST SIZE" + search.size());

                MakeupAdapter = new MakeupAdapter(context, search);
                recyclerView.setAdapter(MakeupAdapter);
                TextView total = view.findViewById(R.id.MakeuptotalMatched);
                total.setText("" + search.size() + " sets match");
                MakeupAdapter.notifyDataSetChanged();
                if(params.width == 206) {


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
                //BodyShape = BodyShapeFinal;
                //Occasion = OccasionFinal;
                //DressCode = DressCodeFinal;
                Brand = BrandFinal;
                ProductType = ProductTypeFinal;
                //TopSize = TopSizeFinal;
                // BottomSize = BottomSizeFinal;
                brandPlus.setText(Brand);
                typePlus.setText(ProductType);
                view.findViewById(R.id.hideType).setVisibility(View.GONE);
                //occasionPlus.setText(Occasion);
                view.findViewById(R.id.hideBrand).setVisibility(View.GONE);
                //bodyShapePlus.setText(BodyShape);
                //view.findViewById(R.id.hideSize).setVisibility(View.GONE);
                //view.findViewById(R.id.hideDressCode).setVisibility(View.GONE);
                //dressCodePlus.setText(DressCode);
                search.setBackgroundResource(R.drawable.search_btn);
                //topSize.setText(TopSize);
                //bottomSize.setText(BottomSize);
                //clearBackGroundAllForDressCode(view);
                //clearBackGroundAllForOccasion(view);
                //clearBackGroundAllForBodyShape(view);
                clearBackGroundAllForBrand(view);
                clearBackGroundAllForType(view);
                params.width = 206;

            }
        });
        typeOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hideList = view.findViewById(R.id.hideType);

                if (hideList.getVisibility() == View.GONE) {
                    hideList.setVisibility(View.VISIBLE);
                    search.setBackgroundResource(R.drawable.save);
                    typePlus.setText("-");
                    params.width = 205; //change the size of search button as a flag
                } else {
                    hideList.setVisibility(View.GONE);
                    typePlus.setText("+");

                    /*if (view.findViewById(R.id.hideDressCode).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideSize).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideBodyShape).getVisibility() == View.GONE) {
                        search.setBackgroundResource(R.drawable.search_btn);
                        params.width = 206;
                    }*/
                }

            }
        });
        brandOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hideList = view.findViewById(R.id.hideBrand);

                if (hideList.getVisibility() == View.GONE) {
                    hideList.setVisibility(View.VISIBLE);
                    search.setBackgroundResource(R.drawable.save);
                    brandPlus.setText("-");
                    params.width = 205; //change the size of search button as a flag
                } else {
                    hideList.setVisibility(View.GONE);
                    brandPlus.setText("+");

                    /*if (view.findViewById(R.id.hideDressCode).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideSize).getVisibility() == View.GONE &&
                            view.findViewById(R.id.hideBodyShape).getVisibility() == View.GONE) {
                        search.setBackgroundResource(R.drawable.search_btn);
                        params.width = 206;
                    }*/
                }

            }
        });


        /*private void clearBackGroundAllForOccasion(View view) {
            view.findViewById(R.id.weddingIcon).setBackgroundResource(R.drawable.blackborder);
            view.findViewById(R.id.partyIcon).setBackgroundResource(R.drawable.blackborder);
            view.findViewById(R.id.beachIcon).setBackgroundResource(R.drawable.blackborder);
            view.findViewById(R.id.dinnerIcon).setBackgroundResource(R.drawable.blackborder);
            view.findViewById(R.id.dailyLifeIcon).setBackgroundResource(R.drawable.blackborder);
            view.findViewById(R.id.datingIcon).setBackgroundResource(R.drawable.blackborder);
            view.findViewById(R.id.meetingIcon).setBackgroundResource(R.drawable.blackborder);

        }*/
        TextView MaybellineIcon = view.findViewById(R.id.MaybellineIcon); // first-hiding-list icon

        MaybellineIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBrand(view);
                MaybellineIcon.setBackgroundResource(R.drawable.rainbowborder);
                Brand = "maybelline";
                MaybellineIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });

        TextView diorIcon = view.findViewById(R.id.diorIcon); // first-hiding-list icon

        diorIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBrand(view);
                diorIcon.setBackgroundResource(R.drawable.rainbowborder);
                Brand = "dior";
                diorIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView LOrealIcon = view.findViewById(R.id.LOrealIcon); // first-hiding-list icon

        LOrealIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBrand(view);
                LOrealIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                Brand = "l'oreal";
                LOrealIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView orlyIcon = view.findViewById(R.id.orlyIcon); // first-hiding-list icon
        orlyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBrand(view);
                orlyIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                Brand = "orly";
                orlyIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView cliniqueIcon = view.findViewById(R.id.cliniqueIcon); // first-hiding-list icon
        cliniqueIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForBrand(view);
                cliniqueIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                Brand = "clinique";
                cliniqueIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView foundationIcon = view.findViewById(R.id.foundationIcon); // first-hiding-list icon
        foundationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                foundationIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "foundation";
                foundationIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView lipstickIcon = view.findViewById(R.id.lipstickIcon); // first-hiding-list icon
        lipstickIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                lipstickIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "lipstick";
                lipstickIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView blushIcon = view.findViewById(R.id.blushIcon); // first-hiding-list icon
        blushIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                blushIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "blush";
                blushIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView eyeshadowIcon = view.findViewById(R.id.eyeshadowIcon); // first-hiding-list icon
        eyeshadowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                eyeshadowIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "eyeshado";
                eyeshadowIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView liplinerIcon = view.findViewById(R.id.liplinerIcon); // first-hiding-list icon
        liplinerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                liplinerIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "lip_liner";
                liplinerIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView eyelinerIcon = view.findViewById(R.id.eyelinerIcon); // first-hiding-list icon
        eyelinerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                eyelinerIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "eyeliner";
                eyelinerIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        TextView nailpolishIcon = view.findViewById(R.id.nailpolishIcon); // first-hiding-list icon
        nailpolishIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackGroundAllForType(view);
                nailpolishIcon.setBackgroundResource(R.drawable.rainbowborder);
                //BodyShape = pearIcon.getText().toString();
                ProductType = "nail_polish";
                nailpolishIcon.setPadding(30,2,0,0); // still don't understand why pear will go to the left
            }
        });
        Button backToTop = view.findViewById(R.id.toTop); // back to top
        backToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView scrollView = view.findViewById(R.id.makeupScroll);
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });


    }




    private void clearBackGroundAllForType(View view) {
        view.findViewById(R.id.foundationIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.lipstickIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.blushIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.eyeshadowIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.liplinerIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.eyelinerIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.nailpolishIcon).setBackgroundResource(R.drawable.blackborder);
    }
    private void clearBackGroundAllForBrand(View view) {
        view.findViewById(R.id.MaybellineIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.diorIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.LOrealIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.orlyIcon).setBackgroundResource(R.drawable.blackborder);
        view.findViewById(R.id.cliniqueIcon).setBackgroundResource(R.drawable.blackborder);
    }



    private void ClearAll () {
        if (MakeupList!= null) {
            MakeupList.clear();

            if(MakeupAdapter != null) {
                MakeupAdapter.notifyDataSetChanged();
            }
        }
        MakeupList = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                // URL url = new URL("http://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl&product_type=lipstick");
                URL url = new URL (params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                String JsonText = readAll(reader);
                JSONArray json = new JSONArray(JsonText);
                Log.d(TAG, "doInBackground: " + JsonText);
                if (json != null) {

                    //Iterating JSON array
                    for (int i=0;i<json.length();i++){

                        //Adding each element of JSON array into ArrayList
                        ObjectMapper objectMapper = new ObjectMapper();
                        Makeup obj = objectMapper.readValue(json.get(i).toString(), Makeup.class);

                        MakeupList.add(obj);
                        Log.d(TAG, "doInBackground: add" + obj.getName());
                    }
                }

                /*StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }*/

                //MakeupList = readJsonFromUrl("http://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl&product_type=lipstick");
                //Log.d(TAG, "doInBackground: " + MakeupList.size());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
        public ArrayList readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONArray json = new JSONArray(jsonText);
                System.out.println("JSON Object");
                System.out.println(json);

                ArrayList<Makeup> listdata = new ArrayList<Makeup>();
                if (json != null) {

                    //Iterating JSON array
                    for (int i=0;i<json.length();i++){

                        //Adding each element of JSON array into ArrayList
                        ObjectMapper objectMapper = new ObjectMapper();
                        Makeup obj = objectMapper.readValue(json.get(i).toString(), Makeup.class);

                        listdata.add(obj);
                    }
                }
                System.out.println("Each element of ArrayList");
                for(int i=0; i<listdata.size(); i++) {
                    //Printing each element of ArrayList
                    Log.e(TAG, "readJsonFromUrl: " + listdata.get(i).getName());

                }
                return null;
                //return json;
            } finally {
                is.close();
            }
        }
        public String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }
    }
}