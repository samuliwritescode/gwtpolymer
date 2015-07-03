package org.vaadin.webcomponents.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.js.JsExport;
import com.google.gwt.core.client.js.JsNamespace;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

@JsNamespace("$wnd.org.vaadin.util")
public class JSUtils {

    @JsExport
    public static void addButton(Widget w, String jsid) {
        RootPanel.get(jsid).add(w);
    }

    @JsExport
    public static String getId() {
        return HTMLPanel.createUniqueId();
    }

    public native static void runNative(JavaScriptObject js) /*-{
                                                             js();
                                                             }-*/;
}
