package edu.itsur.proyectostareas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public class TareaPagerActivity extends
        AppCompatActivity {

    private static final String EXTRA_TAREA_ID =
            "edu.itsur.proyectostareas.tarea_id";

    private ViewPager mViewPager;
    private List<Tarea> mTareas;

    public static Intent newIntent(Context context,
                                   UUID tareaId){
        Intent intent = new Intent(context,
                TareaPagerActivity.class);
        intent.putExtra(EXTRA_TAREA_ID, tareaId);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_pager);

        UUID tareaId = (UUID) getIntent().
                getSerializableExtra(EXTRA_TAREA_ID);

        mViewPager = (ViewPager)
                findViewById(R.id.tarea_view_pager);

        try {
            mTareas = TareaRep.get(this).getTareas();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Tarea tarea = mTareas.get(position);
                return TareaFragment.newInstance(
                        tarea.getId());
            }
            @Override
            public int getCount() {
                return mTareas.size();
            }
        });

        for (int i = 0; i < mTareas.size(); i++) {
            if (mTareas.get(i).getId().equals(tareaId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
