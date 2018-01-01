package ca.coffee_team.newcoffeeapp.fragment;

import android.support.v4.app.Fragment;

public abstract class StandardFragment extends Fragment {

    public static final String BACK_BUTTON_STATUS = "ca.coffee_team.newcoffeeapp.fragment.BACK_BUTTON_STATUS";
  //  public OnFragmentResumeListener mResumeListener;

    public abstract String getTitle();

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentResumeListener) {
//            mResumeListener = (OnFragmentResumeListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentResumeListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mResumeListener = null;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(mResumeListener!=null)
//            mResumeListener.setActionBarTitle(getTitle());
//    }
}
