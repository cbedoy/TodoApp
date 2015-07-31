package self.cbedoy.todoapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

import self.cbedoy.todoapp.CacheService;
import self.cbedoy.todoapp.R;
import self.cbedoy.todoapp.artifacts.TodoViewCell;
import self.cbedoy.todoapp.pojos.TODOPojo;


/**
 * Created by Carlos Bedoy on 7/28/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 */

public class MainActivity extends ActionBarActivity {


    private List<TODOPojo> mDataModel;
    private TodoViewCell mViewCell;

    private ListView mListView;

    private CacheService<TODOPojo> mCacheService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCacheService = new CacheService<>();

        mListView = (ListView) findViewById(R.id.list_dodo);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(mListView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);


                startActivityForResult(intent, AddItemActivity.REQUEST_CODE);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {

                mDataModel.remove(index);


                writeItems();


                return true;

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);

                TODOPojo o = mDataModel.get(index);

                intent.putExtra("index", index);
                intent.putExtra("title", o.getTitle());
                intent.putExtra("description", o.getDescription());

                startActivityForResult(intent, EditItemActivity.REQUEST_CODE);

            }
        });

        readItems();


        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            Explode explode = new Explode();
            explode.setDuration(2000);
            getWindow().setExitTransition(explode);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EditItemActivity.REQUEST_CODE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();

            String title = extras.getString("title", "");
            String description = extras.getString("description", "");
            int index = extras.getInt("position", -1);

            if (!(title.equals("") || description.equals("") || index == -1)) {
                TODOPojo todoPojo = mDataModel.get(index);

                todoPojo.setDate(Calendar.getInstance().getTime());
                todoPojo.setTitle(title);
                todoPojo.setDescription(description);

                mDataModel.set(index, todoPojo);

                writeItems();


            }


        } else if (requestCode == AddItemActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            String title = extras.getString("title", "");
            String description = extras.getString("description", "");

            if (!(title.equals("") || description.equals(""))) {

                TODOPojo pojo = new TODOPojo();
                pojo.setDate(Calendar.getInstance().getTime());
                pojo.setTitle(title);
                pojo.setDescription(description);

                mDataModel.add(pojo);

                writeItems();

            }
        }
    }

    private void readItems() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDataModel = mCacheService.readCacheFromSHA("todos");

                mViewCell = new TodoViewCell(mDataModel);

                mListView.setAdapter(mViewCell);

                mViewCell.notifyDataSetChanged();

            }
        });


    }

    private void writeItems() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mCacheService.writeCacheForSHA("todos", mDataModel);

                mViewCell.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            AlertDialog dialog
                    = new AlertDialog.Builder(this)
                    .setTitle("Code Path Prework")
                    .setMessage("Develop by Carlos Bedoy\n\nGithub:\nhttps://github.com/cbedoy\n\n :)")
                    .create();
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
