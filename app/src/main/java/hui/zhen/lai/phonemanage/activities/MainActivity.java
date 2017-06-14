package hui.zhen.lai.phonemanage.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hui.zhen.lai.phonemanage.DB.ContactsDao;
import hui.zhen.lai.phonemanage.DB.DatabaseHelper;
import hui.zhen.lai.phonemanage.model.Contact;
import hui.zhen.lai.phonemanage.model.ContactAdapter;
import hui.zhen.lai.phonemanage.R;

public class MainActivity extends AppCompatActivity {

    private List<Contact> peopleList = new ArrayList<>();
    private RecyclerView contactView = null;
    private ContactAdapter adapter = null;
    ContactsDao dao = null;

    @Override
    protected void onResume() {
        super.onResume();
        intiCon();  //刷新联系人
        adapter.updateList(peopleList);
        contactView.setAdapter(adapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dao = new ContactsDao(MainActivity.this);

        contactView = (RecyclerView) findViewById(R.id.contactView);
        LinearLayoutManager layoutManger = new LinearLayoutManager(this);
        contactView.setLayoutManager(layoutManger);
        adapter = new ContactAdapter(peopleList);
        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() { //设置触摸监听
            @Override
            public void onItemClick(View view, int position) {

                Cursor cursor = dao.query(peopleList.get(position).getId());
                if(cursor!=null && cursor.moveToFirst()){
                    Intent intent = new Intent(MainActivity.this,ContactDetilActivity.class);
                    intent.putExtra("data",cur2Con(cursor));
                    startActivity(intent);
                }


            }
        });
        adapter.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"long click "+peopleList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        contactView.setItemAnimator(new DefaultItemAnimator());     //设置Item增加、移除动画


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {     //注册浮动按钮，按钮的功能为添加新的联系人，并绑定监听器
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate thenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            Toast.makeText(this, "其实是删除数据库", Toast.LENGTH_SHORT).show();
            dao.delDB(this);
            recreate();
            return true;
        } else if (id == R.id.menu_add) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void intiCon() {
        Cursor cursor = dao.queryAll();
        if (cursor != null && cursor.moveToFirst()) {
            peopleList.clear();         //清空联系人表，避免重复
            do {

                peopleList.add(cur2Con(cursor)); //显示数据
            } while (cursor.moveToNext());

        }
    }

    private Contact cur2Con(Cursor cursor) {
        Contact contact = new Contact(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("num1")));
        contact.setId(cursor.getInt(cursor.getColumnIndex("id")));
        return  contact;
    }


}
