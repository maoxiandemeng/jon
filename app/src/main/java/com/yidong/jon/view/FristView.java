package com.yidong.jon.view;

import com.yidong.jon.base.BaseView;
import com.yidong.jon.model.VideoEntity;

import java.util.List;

/**
 * Created by jon on 2016/12/20
 */

public interface FristView extends BaseView {

    void showVideo(List<VideoEntity> list);
}
