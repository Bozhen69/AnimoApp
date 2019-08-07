package com.bozhen.animoapplication.main.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.model.room.ObjectInPlans;
import com.bozhen.animoapplication.main.presentation.presenter.DetailsObjectInPlanPresenter;
import com.bozhen.animoapplication.main.presentation.view.DetailsObjectInPlanView;
import com.bozhen.animoapplication.main.utils.CoordinateUtils;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class fragmentDetailObjectInPlan extends MvpAppCompatFragment implements DetailsObjectInPlanView {

    private static final String bundle_long_id = "BUNDLE_LONG_ID";

    @InjectPresenter
    DetailsObjectInPlanPresenter detailsObjectInPlanPresenter;

    @ProvidePresenter
    DetailsObjectInPlanPresenter provideDetailsObjectInPlanPresenter(){
        return new DetailsObjectInPlanPresenter(getActivity().getApplication());
    }

    private MapView startMv;
    private MapView endMv;

    private TextView infoTv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("3283f70e-777d-4f09-b8e5-a90b07f2ea10");
        MapKitFactory.initialize(getContext());
        if (getArguments() != null && getArguments().containsKey(bundle_long_id)) {
            detailsObjectInPlanPresenter.showInfo(getArguments().getLong(bundle_long_id));
        }
    }

    public static fragmentDetailObjectInPlan newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(bundle_long_id,id);
        fragmentDetailObjectInPlan fragment = new fragmentDetailObjectInPlan();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_object_plan, container, false);
        // Укажите имя activity вместо map.
        startMv = view.findViewById(R.id.startMv);
        endMv =  view.findViewById(R.id.endMv);
        startMv.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        endMv.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        infoTv = view.findViewById(R.id.time_start_endTv);
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        startMv.onStop();
        endMv.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        startMv.onStart();
        endMv.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void showInformation(ObjectInPlans objectInPlans) {
        String text;
        if(objectInPlans.getPlan_time_start()!=null && !objectInPlans.getPlan_time_start().equals("")) {
            Point point_start = CoordinateUtils.getCoordinateFromString(objectInPlans.getPlan_location_start());
            startMv.getMap().move(
                    new CameraPosition(point_start, 11.0f, 0.0f, 0.0f),
                    new Animation(Animation.Type.SMOOTH, 0),
                    null);

            startMv.getMap().getMapObjects().addPlacemark(point_start);
            text = "Выполнение было начато: " + objectInPlans.getPlan_time_start()+System.lineSeparator();
            if(objectInPlans.getPlan_time_end()!=null && !objectInPlans.getPlan_time_end().equals("")){
                Point point_end = CoordinateUtils.getCoordinateFromString(objectInPlans.getPlan_location_end());
                endMv.getMap().move(
                        new CameraPosition(point_end, 11.0f, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 0),
                        null);
                endMv.getMap().getMapObjects().addPlacemark(point_end);
                text +="Выполнение было закончено: "+ objectInPlans.getPlan_time_end();
            }
            else{
                text += "Выполнение не было закончено";
            }
        }
        else{
            text = "Вы еще не приступали к выполнению данного задания";
        }
        infoTv.setText(text);
    }
}
