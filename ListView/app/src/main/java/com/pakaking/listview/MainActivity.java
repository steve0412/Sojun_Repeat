package com.pakaking.listview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<FoodItem> data = new ArrayList<>();
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);

        data.add(new FoodItem(R.drawable.chicken, "치킨"));
        data.add(new FoodItem(R.drawable.jockbal, "족발"));
        data.add(new FoodItem(R.drawable.pizza, "피자"));

        adapter = new FoodAdapter(this,R.layout.item, data);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long I) {
                Toast.makeText(getApplicationContext(), data.get(i).getName() + "(이)가 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder der = new AlertDialog.Builder(MainActivity.this);
                android.app.AlertDialog.Builder builder = null;
                builder.setTitle(data.get(position).getName()+"(을)를 정말 삭제하시겠습니까?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        data.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "",Toast.LENGTH_SHORT).show();
                    }
                });
                android.app.AlertDialog dialog=builder.create();
                dialog.show();
                return true;
            }
        });
    }

    public void addClick(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("음식 추가");
        alert.setMessage("음식 이름 입력");

        final EditText name = new EditText(this);
        name.setHint("음식 이름");
        alert.setView(name);

        alert.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String foodname = name.getText().toString();
                data.add(new FoodItem(R.mipmap.ic_launcher, foodname));
                adapter.notifyDataSetChanged();
            }
        });

        alert.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }
}
