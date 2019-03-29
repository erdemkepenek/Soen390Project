package org.thoughtcrime.securesms.components;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.thoughtcrime.securesms.ApplicationPreferencesActivity;
import org.thoughtcrime.securesms.ConversationListActivity;
import org.thoughtcrime.securesms.InviteActivity;
import org.thoughtcrime.securesms.R;

public class NavigationBarFragment extends Fragment{

    private OnFragmentInteractionListener mListener;

    private BottomNavigationView navigationBarView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private int x=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_navigation_bar, container, false);

        navigationBarView = view.findViewById(R.id.navigation_bar);

        navigationBarView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_bar_chats: {
                        Intent intent = new Intent(getActivity(), ConversationListActivity.class);
                        startActivity(intent);
                        /*startActivity(new Intent(getActivity(), ConversationListActivity.class));*/
                    }
                    break;
                    case R.id.navigation_bar_settings: {
                        Intent intent = new Intent(getActivity(), InviteActivity.class);
                        startActivity(intent);
                        /*startActivity(new Intent(getActivity(), ApplicationPreferencesActivity.class));*/
                    }
                    break;
                }
                return true;
            }
        });

      /*  navigationBarView.setOnNavigationItemSelectedListener(this);*/



        String tag = getActivity().getClass().getSimpleName();

        Log.d("this activity", tag);
        Log.d("ConvListAct", ConversationListActivity.class.getSimpleName());

        if (tag.equals(ConversationListActivity.class.getSimpleName())) {
            navigationBarView.setSelectedItemId(R.id.navigation_bar_chats);
        } else if (tag.equals(ApplicationPreferencesActivity.class.getSimpleName())) {
            navigationBarView.setSelectedItemId(R.id.navigation_bar_settings);
        } else {
            navigationBarView.setSelectedItemId(R.id.navigation_bar_call_logs);
        }

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
