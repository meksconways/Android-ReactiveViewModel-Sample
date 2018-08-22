package com.mek.hurriyethaber.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface TabScreenNav {

    void push(String id);

    void initWithRouter(Router router);

    void clearStateFromChildRouter();


}
