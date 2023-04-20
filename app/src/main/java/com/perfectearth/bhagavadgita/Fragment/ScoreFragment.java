package com.perfectearth.bhagavadgita.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.perfectearth.bhagavadgita.R;
public class ScoreFragment extends Fragment {

    public ScoreFragment() {
    }

    private TabLayout scoreTabLayout;
    private ViewPager2 scoreViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewScore = inflater.inflate(R.layout.fragment_score, container, false);
        scoreTabLayout = viewScore.findViewById(R.id.tab_layout_leader);
        scoreViewPager = viewScore.findViewById(R.id.view_pager_leader);

        // Set up the ViewPager2 adapter
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), getLifecycle());
        scoreViewPager.setAdapter(adapter);

        // Link the TabLayout to the ViewPager2
        new TabLayoutMediator(scoreTabLayout, scoreViewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Quiz Score");
                    } else if (position == 1) {
                        tab.setText("Exam Score");
                    }
                }).attach();

        return viewScore;
    }

    public static class FragmentAdapter extends FragmentStateAdapter {

        private static final int NUM_TABS = 2;

        public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new QuizScoreFragment();
                case 1:
                    return new ExamScoreFragment();
                default:
                    throw new IllegalArgumentException("Invalid position: " + position);
            }
        }

        @Override
        public int getItemCount() {
            return NUM_TABS;
        }
    }

}
