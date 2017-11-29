package com.andrewvychev.railwaytickets.di.components;

import com.andrewvychev.railwaytickets.RailwayApplication;
import com.andrewvychev.railwaytickets.di.modules.MainModule;
import com.andrewvychev.railwaytickets.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {MainModule.class, NetworkModule.class})
@Singleton
public interface MainComponent {

    void inject(RailwayApplication application);

    ScreenSubcomponent.Builder activityComponent();
}

