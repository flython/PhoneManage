package hui.zhen.lai.phonemanage.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hui.zhen.lai.phonemanage.DB.ContactsDao;
import hui.zhen.lai.phonemanage.R;
import hui.zhen.lai.phonemanage.model.Contact;

public class ContactDetilActivity extends AppCompatActivity {

    private EditText etNum1;
    private EditText etName;
    private Button buUpdate;
    private Button buDel;
    private Contact contact = null;
    private ContactsDao dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_contact_detil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);    //标题栏

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();   //添加返回按钮
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etName = (EditText) findViewById(R.id.add_name);
        etNum1 = (EditText) findViewById(R.id.add_num1);
        buUpdate = (Button) findViewById(R.id.add_but_change);
        buDel = (Button) findViewById(R.id.add_but_delete);

        buUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new ContactsDao(ContactDetilActivity.this);
                updateLocalData();
                dao.update(contact);
                dao.close();
                Toast.makeText(ContactDetilActivity.this, "联系人已更改", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new ContactsDao(ContactDetilActivity.this);
                dao.delete(contact.getId());
                dao.close();
                Toast.makeText(ContactDetilActivity.this, "联系人已删除", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        intiData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // 返回按钮
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void intiData(){
        contact = (Contact)getIntent().getSerializableExtra("data");
        etName.setText(contact.getName());
        etNum1.setText(contact.getNum1());
    }

    private void updateLocalData(){
        contact.setName(etName.getText().toString());
        Log.d("233", "updateLocalData: " + etName.getText().toString());
        contact.setNum1(etNum1.getText().toString());
    }

}
