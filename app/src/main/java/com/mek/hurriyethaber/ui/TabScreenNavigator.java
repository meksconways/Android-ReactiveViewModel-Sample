package com.mek.hurriyethaber.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.mek.hurriyethaber.detail.NewsDetailController;
import com.mek.hurriyethaber.di.ActivityScope;
import com.mek.hurriyethaber.di.ScreenScope;

import javax.inject.Inject;

@ActivityScope
public class TabScreenNavigator implements TabScreenNav {


    private Router childRouter;

    @Inject
    TabScreenNavigator(){

    }

    @Override
    public void initWithRouter(Router router) {
        this.childRouter = router;

    }

    @Override
    public void push(String id) {
        if (childRouter != null) {
            childRouter.pushController(RouterTransaction.with(NewsDetailController.newInstance(id))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }


    @Override
    public void clearStateFromChildRouter() {
        childRouter.setPopsLastView(true); /* Ensure the last view can be removed while we do this */
        childRouter.popToRoot();
        childRouter.popCurrentController();
        childRouter.setPopsLastView(false);
    }
}
