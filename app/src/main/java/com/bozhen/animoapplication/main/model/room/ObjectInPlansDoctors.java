package com.bozhen.animoapplication.main.model.room;

import androidx.room.Embedded;

public class ObjectInPlansDoctors {
    @Embedded
    public ObjectInPlans objectInPlans;
    @Embedded
    public Doctors doctors;

    public ObjectInPlansDoctors(ObjectInPlans objectInPlans, Doctors doctors) {
        this.objectInPlans = objectInPlans;
        this.doctors = doctors;
    }

    public ObjectInPlans getObjectInPlans() {
        return objectInPlans;
    }

    public void setObjectInPlans(ObjectInPlans objectInPlans) {
        this.objectInPlans = objectInPlans;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }
}
