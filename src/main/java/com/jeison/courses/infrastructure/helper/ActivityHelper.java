package com.jeison.courses.infrastructure.helper;

import com.jeison.courses.api.dto.request.ActivityReq;
import com.jeison.courses.api.dto.response.ActivityResp;
import com.jeison.courses.domain.entities.Activity;

public class ActivityHelper {

public static ActivityResp activityToResp(Activity activity) {
        return ActivityResp.builder()
                .id(activity.getId())
                .activityTitle(activity.getActivityTitle())
                .description(activity.getDescription())
                .dueDate(activity.getDueDate())
                .build();
    }

    public static Activity reqToActivity(ActivityReq activityReq) {
        return Activity.builder()
                .activityTitle(activityReq.getActivityTitle())
                .description(activityReq.getDescription())
                .dueDate(activityReq.getDueDate())
                .build();
    }

}
