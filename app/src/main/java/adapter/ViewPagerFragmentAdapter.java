package adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fragment.LoginFragment;
import fragment.RegUserFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private String[] pageTitles = new String[] {"Register Bruker","Login"};

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new RegUserFragment();

            case 1:
                return new LoginFragment();
        }
        return new LoginFragment();
    }


    @Override
    public int getItemCount() {
        return pageTitles.length;
    }
}
