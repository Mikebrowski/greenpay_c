package ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fragment.FirstFragment;
import fragment.LesOmBkraft;
import fragment.LesOmBkraftKt;
import fragment.VisBaekraft;

public class ViewPageAdapter extends FragmentStateAdapter
{

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LesOmBkraft();
            case 1:
                return new VisBaekraft();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
