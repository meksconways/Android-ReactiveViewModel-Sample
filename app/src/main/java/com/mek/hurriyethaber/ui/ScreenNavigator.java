package com.mek.hurriyethaber.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface ScreenNavigator {

    void initWithRouter(Router router, Controller rootController);

    boolean pop();

    void clear();


}
