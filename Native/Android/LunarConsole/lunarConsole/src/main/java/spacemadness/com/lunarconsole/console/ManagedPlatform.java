//
//  ManagedPlatform.java
//
//  Lunar Unity Mobile Console
//  https://github.com/SpaceMadness/lunar-unity-console
//
//  Copyright 2015-2021 Alex Lementuev, SpaceMadness.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//


package spacemadness.com.lunarconsole.console;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.unity3d.player.UnityPlayer;

import java.lang.ref.WeakReference;
import java.util.Map;

import spacemadness.com.lunarconsole.core.LunarConsoleException;
import spacemadness.com.lunarconsole.debug.Log;
import spacemadness.com.lunarconsole.utils.UIUtils;

import static spacemadness.com.lunarconsole.debug.Tags.PLUGIN;

public class ManagedPlatform implements Platform {
    private final UnityScriptMessenger scriptMessenger;
    private final View unityView;
    public ManagedPlatform(Activity activity, String target, String method) {
        try {
            unityView = ((ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        }
        catch (Exception e) {
            throw new LunarConsoleException("Can't initialize plugin: can't fetch UnityView from activity");
        }

        scriptMessenger = new UnityScriptMessenger(target, method);
    }

    //region Helpers

    //endregion

    //region Platform

    @Override
    public View getTouchRecipientView() {
        return unityView;
    }

    @Override
    public void sendUnityScriptMessage(String name, Map<String, Object> data) {
        try {
            scriptMessenger.sendMessage(name, data);
        } catch (Exception e) {
            Log.e(PLUGIN, "Error while sending Unity script message: name=%s param=%s", name, data);
        }
    }

    //endregion

    //region Getters/Setters

    //endregion
}
