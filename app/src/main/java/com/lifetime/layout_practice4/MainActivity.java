package com.lifetime.layout_practice4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.nio.file.Path;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edit_text;
    ImageButton book_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //make translucent statusBar on kitkat devices
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        book_mark = findViewById(R.id.button1);
        edit_text = findViewById(R.id.edit_text);

//        book_mark.setBackgroundResource(R.drawable.ic_book_mark_off);
        initView();

//        book_mark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                book_mark.setBackgroundResource(R.drawable.ic_book_mark_off);
//            }
//        });

    }


    public static void setWindowFlag(Activity activity, final int bits, boolean on){
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if(on){
            winParams.flags |=bits;
        }else{
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void initView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList arrayList = new ArrayList<>();
        arrayList.add(new Search());
        arrayList.add(new Info(R.drawable.cat1,"Snowbirds-Mature, Reliable guy can drive your car To or From...","2hrs ago"));
        arrayList.add(new Info(R.drawable.cat2,"Yorkie Poodle Puppies","5hrs ago"));
        arrayList.add(new Info(R.drawable.cat3,"AKC Golden Retriever litter of 5 males and a female pups…","12hrs ago"));
        arrayList.add(new Info(R.drawable.cat4,"Sweethearts Child Care/Full time special for 2+yr. old…","18hrs ago"));
        arrayList.add(new Info(R.drawable.cat4,"Sweethearts Child Care/Full time special for 2+yr. old…","18hrs ago"));
        arrayList.add(new Info(R.drawable.cat4,"Sweethearts Child Care/Full time special for 2+yr. old…","18hrs ago"));
        arrayList.add(new Info(R.drawable.cat4,"Sweethearts Child Care/Full time special for 2+yr. old…","18hrs ago"));

        Adapter adapter = new Adapter(arrayList, new Adapter.OnItemClickListener(){

            @Override
            public void onItemClick(Info info) {
                Toast.makeText(MainActivity.this, "Item Clicked", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(Search search) {
                Toast.makeText(MainActivity.this, "Item Clicked", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
