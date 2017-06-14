package hui.zhen.lai.phonemanage.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddContactActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etNum1;
    private Button buAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);    //标题栏

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();   //添加返回按钮
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        etName = (EditText) findViewById(R.id.add_name);
        etNum1 = (EditText) findViewById(R.id.add_num1);
        buAdd = (Button) findViewById(R.id.add_but_confirm);
        buAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String num1 = etNum1.getText().toString();
                ContactsDao dao = new ContactsDao(AddContactActivity.this);
                Boolean insert = dao.insert(new Contact(name, num1));
                if(insert){
                    Toast.makeText(AddContactActivity.this, "添加联系人成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
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

}
