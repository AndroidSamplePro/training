package proteam.com.bai_5_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TwoFragment extends Fragment implements MainActivity.OnActivityToFragmentInteractionListener{

    private TextView textView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            ((MainActivity) context).setCallBackFromActivity(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        textView = (TextView) view.findViewById(R.id.tvTitle);
        return view;
    }

    @Override
    public void messageFromActivityToFragment(String myString) {
        textView.setText(myString);
    }
}
