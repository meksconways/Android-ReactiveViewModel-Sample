package com.mek.hurriyethaber.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.mek.hurriyethaber.detail.NewsDetailController;
import com.mek.hurriyethaber.di.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

    private Router router;

    @Inject
    DefaultScreenNavigator(){

    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        if (!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public void push(String id) {
        if (router != null) {
            router.pushController(RouterTransaction.with(NewsDetailController.newInstance(id))
                    .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
        }
    }



    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void clear() {
        router = null;
    }



    @Override
    public void clearStateFromChildRouter(Router childRouter) {
            childRouter.setPopsLastView(true); /* Ensure the last view can be removed while we do this */
            childRouter.popToRoot();
            childRouter.popCurrentController();
            childRouter.setPopsLastView(false);
    }
}
