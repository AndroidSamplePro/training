package proteam.com.trainrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> contacts;
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        contacts = Contact.createContactsList(5);
        // Create adapter passing in the sample user data
        adapter = new ContactsAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContacts.setLayoutManager(linearLayoutManager);

//        GridLayoutManager gridLayoutManager =
//                new GridLayoutManager(this, 2);
//        rvContacts.setLayoutManager(gridLayoutManager);

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
//        StaggeredGridLayoutManager gridSpanLayoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        rvContacts.setLayoutManager(gridSpanLayoutManager);

        // onItem click
        rvContacts.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvContacts, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Contact contact = contacts.get(position);
                Toast.makeText(getApplicationContext(), contact.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        findViewById(R.id.btnAddMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add a new contact
                contacts.add(0, new Contact("Barney", true));
                // Notify the adapter that an item was inserted at position 0
                adapter.notifyItemInserted(0);
                //       adapter.notifyDataSetChanged();

//                // record this value before making any changes to the existing list
//                int curSize = adapter.getItemCount();
//
//                // replace this line with wherever you get new records
//                ArrayList<Contact> newItems = Contact.createContactsList(20);
//
//                // update the existing list
//                contacts.addAll(newItems);
//                // curSize should represent the first element that got added
//                // newItems.size() represents the itemCount
//                adapter.notifyItemRangeInserted(curSize, newItems.size());
            }
        });

        rvContacts.setItemAnimator(new DefaultItemAnimator());
    }
}
