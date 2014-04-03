package com.FullSail.javai_project1;

import com.FullSail.javai_project1.R.layout;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity 
{
	int stage = 0;
	String[] sayings;
	String[] correct1 = {"pepsi", "7 billion", "yes", "redwoods"};
	String[] correct2 = {"coke", "star wars", "norse gods"};
	boolean side = false;
	EditText input;
	TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.editText1);
        question = (TextView)findViewById(R.id.textView1);

        if (savedInstanceState == null) 
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) 
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment 
    {

        public PlaceholderFragment() 
        {
        }
    }

    
    public void CompareAnswers(String[] answers, String[] correct)
    {
    	int a = 0;
    	for (int i = 0; i < answers.length; i++)
    	{
    		if (answers[i].equals(correct[i]))
    		{
    			a++;
    		}
    	}
    	question.setText("You answered " + a + " out of " + correct.length + " answers correctly.  Pepsi or Coke?");
    	input.setText("");
    }
    
    public void Clicked(View v)
    {
    	String curAnswer = input.getText().toString();
    	switch (stage)
    	{
    		case 0:
    			if (curAnswer.equals("pepsi"))
    			{
    				side = true;
    				sayings = new String[4];
    				question.setText("How many people live in the world?");
    			}
    			else
    			{
    				side = false;
    				sayings = new String[3];
    				question.setText("Best movie of all-time?");
    			}
    			sayings[0] = curAnswer;
    			input.setText("");
    			stage++;
    			break;
    			
    		case 1:
    			sayings[1] = curAnswer;
    			if (side == true)
    			{
    				question.setText("Are there more chickens in the world than human?");
    			}
    			else
    			{
    				question.setText("Norse gods or Greek gods?");
    			}
    			input.setText("");
    			stage++;
    			break;
    			
    		case 2:
    			sayings[2] = curAnswer;
    			if (side == true)
    			{
    				question.setText("What are the tallest trees?");
    			}
    			else
    			{
    				CompareAnswers(sayings, correct2);
    				break;
    			}
    			input.setText("");
    			stage++;
    			break;
    			
    		case 3:
    			sayings[3] = curAnswer;
    			CompareAnswers(sayings, correct1);
    			input.setText("");
    			stage = 0;
    			break;
    	}
    }
}
