package org.sam.fortuneteller.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lombok.Data;
import lombok.Setter;
import org.sam.fortuneteller.R;
import org.sam.fortuneteller.data.DataManager;
import org.sam.fortuneteller.exception.ItemNotFoundException;
import org.sam.fortuneteller.model.BallResult;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.Results;

import java.util.List;

import static org.sam.fortuneteller.model.Consts.ARG_RESULTS_NAME;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BallResultFragment extends Fragment {

    private int mColumnCount = Consts.BALL_COUNT;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BallResultFragment() {
    }

    // TODO: Customize parameter initialization
    public static BallResultFragment newInstance(String name) {
        BallResultFragment fragment = new BallResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RESULTS_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ballresult_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            String stringExtra = getActivity().getIntent().getStringExtra(ARG_RESULTS_NAME);
            Results results = getResults();
            recyclerView.setAdapter(new BallResultRecyclerViewAdapter(results, mListener));
        }
        return view;
    }

    private Results getResults() {
        Bundle arguments = getArguments();
        String resultsName = arguments.getString(ARG_RESULTS_NAME);
        Results results;
        if (null == resultsName)
        {
            results = new Results();
        }
        else
        {
            try {
                results = DataManager.getInstance().get(resultsName);
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                results = DataManager.getInstance().newAndSave(resultsName);
            }
        }
        return results;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(BallResult item);
    }
}
